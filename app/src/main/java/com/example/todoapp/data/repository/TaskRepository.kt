package com.example.todoapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todoapp.data.SharedPreferencesManager
import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.data.local.TaskEntity
import com.example.todoapp.data.remote.TaskApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val taskApiService: TaskApiService,
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    fun getAllTasks(category: String): LiveData<List<TaskEntity>> {
        return taskDao.getAllTasks(category)
    }
    fun getAllTasks(): LiveData<List<TaskEntity>> {
        val userId = sharedPreferencesManager.getUserId() ?: ""
        return taskDao.getAllTasks(userId)
    }

    suspend fun getAndSaveTodos() {
        try {
            val todos = taskApiService.getTodos()
            val userId = sharedPreferencesManager.getUserId() ?: ""

            todos.forEach { todo ->
                val taskEntity = TaskEntity(
                    title = todo.title,
                    isCompleted = todo.completed,
                    userId = userId
                )
                taskDao.insertTask(taskEntity)
            }
            Log.d("TaskRepository", "API'den ${todos.size} adet görev çekildi.")
        } catch (e: Exception) {
            Log.e("TaskRepository", "API'den veri çekerken hata oluştu: ${e.message}")
        }
    }

    suspend fun addTask(task: TaskEntity) {
        val userId = sharedPreferencesManager.getUserId() ?: ""
        taskDao.insertTask(task.copy(userId = userId))
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }
}