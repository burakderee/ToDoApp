package com.example.todoapp.ui.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.todoapp.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.example.todoapp.data.local.TaskEntity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddTask: FloatingActionButton

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
                viewModel.deleteTask(task)
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
