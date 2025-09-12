package com.example.todoapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.local.TaskEntity
import com.example.todoapp.ui.task.AddTaskDialogFragment
import com.example.todoapp.ui.task.EditTaskDialogFragment
import com.example.todoapp.ui.task.SwipeActionCallback
import com.example.todoapp.ui.task.TaskAdapter
import com.example.todoapp.ui.task.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddTask: FloatingActionButton

    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        category = intent.getStringExtra("CATEGORY_KEY")
        setupUI()
        setupObservers()

    }

    private fun setupUI() {
        recyclerView = findViewById(R.id.recyclerViewTasks)
        fabAddTask = findViewById(R.id.fabAddTask)

        taskAdapter = TaskAdapter(
            onDeleteClicked = { task ->
                showDeleteConfirmationDialog(task)
            },
            onCheckedChanged = { task, isChecked ->
                viewModel.updateTask(task.copy(isCompleted = isChecked))
            },
            onEditClicked = { task ->
                showEditDialog(task)
            }
        )

        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }

        val swipeHandler = SwipeActionCallback(
            this,
            onSwipeLeft = { viewHolder ->
                val position = viewHolder.adapterPosition
                val taskToDelete = taskAdapter.currentList[position]
                viewModel.deleteTask(taskToDelete)
                Toast.makeText(this, "Görev başarıyla kaldırıldı.", Toast.LENGTH_SHORT).show()
            },
            onSwipeRight = { viewHolder ->
                val position = viewHolder.adapterPosition
                val taskToEdit = taskAdapter.currentList[position]
                showEditDialog(taskToEdit)
            }
        )
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupObservers() {
        viewModel.setCategory(category ?: "")

        viewModel.tasks.observe(this) { tasks: List<TaskEntity> ->
            taskAdapter.submitList(tasks)
        }
    }

    private fun showDeleteConfirmationDialog(task: TaskEntity) {
        AlertDialog.Builder(this)
            .setTitle("Görevi Kaldır")
            .setMessage("Görevi kaldırmak istediğine emin misin?")
            .setPositiveButton("Evet") { _, _ ->
                viewModel.deleteTask(task)
                Toast.makeText(this, "Görev başarıyla kaldırıldı.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Hayır") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, "Silme işlemi iptal edildi.", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun showEditDialog(task: TaskEntity) {
        EditTaskDialogFragment.Companion.newInstance(task).show(supportFragmentManager, "EditTaskDialog")
    }

    private fun showAddTaskDialog() {
        AddTaskDialogFragment().show(supportFragmentManager, "AddTaskDialog")
    }
}