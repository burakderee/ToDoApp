package com.example.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.local.CategoryDao
import com.example.todoapp.data.local.CategoryEntity
import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.data.local.TaskEntity
import com.example.todoapp.data.local.UserDao
import com.example.todoapp.data.local.UserEntity

@Database(entities = [TaskEntity::class, UserEntity::class, CategoryEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
}