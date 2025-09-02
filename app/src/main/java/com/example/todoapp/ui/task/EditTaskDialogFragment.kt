package com.example.todoapp.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.todoapp.R
import com.example.todoapp.data.local.TaskEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTaskDialogFragment : DialogFragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.editTextTaskTitle)
        val saveButton = view.findViewById<Button>(R.id.buttonSaveTask)

        val taskToEdit = arguments?.getParcelable<TaskEntity>("task")

        if (taskToEdit != null) {
            editText.setText(taskToEdit.title)

            saveButton.setOnClickListener {
                val newTitle = editText.text.toString()
                if (newTitle.isNotEmpty()) {
                    val updatedTask = taskToEdit.copy(title = newTitle)
                    viewModel.updateTask(updatedTask)
                    dismiss()
                }
            }
        }
    }

    companion object {
        fun newInstance(task: TaskEntity) = EditTaskDialogFragment().apply {
            arguments = bundleOf("task" to task)
        }
    }
}
