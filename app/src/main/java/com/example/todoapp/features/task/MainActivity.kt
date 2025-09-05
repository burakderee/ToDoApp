package com.example.todoapp.features.task

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.todoapp.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.todoapp.data.local.TaskEntity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddTask: FloatingActionButton

    private fun showEditDialog(task: TaskEntity) {
        val dialog = EditTaskDialogFragment.newInstance(task)
        dialog.show(supportFragmentManager, "EditTaskDialog")
    }

    fun updateTask(task: TaskEntity) {
        viewModel.updateTask(task)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewTasks)
        fabAddTask = findViewById(R.id.fabAddTask)

        taskAdapter = TaskAdapter(
            onCheckedChanged = { updatedTask ->
                viewModel.updateTask(updatedTask)
            },
            onDeleteClicked = { task ->
                AlertDialog.Builder(this)
                    .setTitle("Görevi Kaldır")
                    .setMessage("Görevi kaldırmak istediğine emin misin?")
                    .setPositiveButton("Evet") { dialog, which ->
                        viewModel.deleteTask(task)

                        Toast.makeText(this, "Görev başarıyla kaldırıldı.", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Hayır") { dialog, which ->
                        Toast.makeText(this, "Silme işlemi iptal edildi.", Toast.LENGTH_SHORT).show()
                    }
                    .show()
            },
            onEditClicked = { task ->
                EditTaskDialogFragment.newInstance(task).show(supportFragmentManager, "EditTaskDialog")
                //showEditDialog(task)
            }
        )

        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.tasks.observe(this) { tasks: List<TaskEntity> ->
            taskAdapter.submitList(tasks)
        }

        fabAddTask.setOnClickListener {
            AddTaskDialogFragment().show(supportFragmentManager, "AddTaskDialog")

        }
    }
}
