
package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.local.CategoryDao
import com.example.todoapp.data.local.CategoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

    val allCategories: LiveData<List<CategoryEntity>> = categoryDao.getAllCategories()

    suspend fun insertCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    suspend fun deleteCategory(categoryName: String) {
        categoryDao.deleteCategory(categoryName)
    }
}