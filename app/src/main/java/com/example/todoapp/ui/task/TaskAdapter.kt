package com.example.todoapp.ui.task

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.local.TaskEntity
import com.google.android.material.card.MaterialCardView

class TaskAdapter(
    private val onCheckedChanged: (TaskEntity) -> Unit,
    private val onDeleteClicked: (TaskEntity) -> Unit,
    private val onEditClicked: (TaskEntity) -> Unit
) : ListAdapter<TaskEntity, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.title_text_view)
        private val completedCheckBox: CheckBox = view.findViewById(R.id.completed_checkbox)
        private val deleteButton: TextView = view.findViewById(R.id.delete_button)
        private val editButton: TextView = view.findViewById(R.id.edit_button_button)

        fun bind(task: TaskEntity) {
            titleTextView.text = task.title

            completedCheckBox.setOnCheckedChangeListener(null)

            completedCheckBox.isChecked = task.isCompleted

            completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                val updatedTask = task.copy(isCompleted = isChecked)
                onCheckedChanged(updatedTask)
            }

            deleteButton.setOnClickListener {
                onDeleteClicked(task)
            }
            editButton.setOnClickListener {
                onEditClicked(task)
            }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<TaskEntity>() {
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity) = oldItem == newItem
    }
}
