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
import com.example.todoapp.ui.task.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryDialogFragment : DialogFragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.editTextCategoryName)
        val saveButton = view.findViewById<Button>(R.id.buttonSaveCategory)

        saveButton.setOnClickListener {
            val categoryName = editText.text.toString()
            if (categoryName.isNotEmpty()) {
                viewModel.addCategory(categoryName)
                Toast.makeText(requireContext(), "Kategori başarıyla eklendi!", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Kategori adı boş olamaz.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}