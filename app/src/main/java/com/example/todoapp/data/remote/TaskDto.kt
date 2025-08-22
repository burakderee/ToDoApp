package com.example.todoapp.data.remote

data class TaskDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)