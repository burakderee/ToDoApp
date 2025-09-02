package com.example.todoapp.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.todoapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskDialogFragment : DialogFragment() {

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

        saveButton.setOnClickListener {
            val taskTitle = editText.text.toString()
            if (taskTitle.isNotEmpty()) {
                viewModel.addTask(taskTitle)
                Toast.makeText(requireContext(), "Görev başarıyla eklendi!", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
    }
}