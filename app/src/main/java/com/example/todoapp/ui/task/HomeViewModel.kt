package com.example.todoapp.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.CategoryEntity
import com.example.todoapp.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val allCategories = categoryRepository.allCategories
    fun addCategory(categoryName: String) {
        viewModelScope.launch {
            categoryRepository.insertCategory(CategoryEntity(categoryName = categoryName))
        }
}}