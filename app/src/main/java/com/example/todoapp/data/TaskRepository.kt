package com.example.todoapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.data.local.TaskEntity
import com.example.todoapp.data.remote.TaskApiService
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val taskApiService: TaskApiService
) {
    fun getAllTasks(): LiveData<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun getAndSaveTodos() {
        try {
            val todos = taskApiService.getTodos()
            todos.forEach { todo ->
                taskDao.insertTask(TaskEntity(title = todo.title, isCompleted = todo.completed))
            }
            Log.d("TaskRepository", "API'den ${todos.size} adet görev çekildi.")
        } catch (e: Exception) {
            Log.e("TaskRepository", "API'den veri çekerken hata oluştu: ${e.message}")
        }
    }

    suspend fun addTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }
}