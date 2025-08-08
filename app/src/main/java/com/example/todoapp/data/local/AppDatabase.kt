package com.example.todoapp.data.local
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.local.TaskDao
import com.example.todoapp.data.local.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}