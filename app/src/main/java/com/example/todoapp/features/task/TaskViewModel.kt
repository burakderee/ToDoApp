package com.example.todoapp.features.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.TaskEntity
import com.example.todoapp.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val tasks = taskRepository.getAllTasks()

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }
    }


    /*init {
        viewModelScope.launch {
            taskRepository.getAndSaveTodos()
        }
    }*/

    fun addTask(title: String) {
        viewModelScope.launch {
            val task = TaskEntity(title = title)
            taskRepository.addTask(task)
        }
    }
    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}