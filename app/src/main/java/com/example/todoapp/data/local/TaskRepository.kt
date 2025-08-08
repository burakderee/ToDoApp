package com.example.todoapp.data

import com.example.todoapp.data.remote.TaskApiService
import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.data.remote.TaskDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val apiService: TaskApiService,
    private val taskDao: TaskDao
) {
    suspend fun getAndSaveTodos(): List<TaskDto> {
        val remoteTasks = apiService.getAllTodos()
        return remoteTasks
    }
}