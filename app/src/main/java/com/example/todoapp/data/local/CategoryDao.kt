package com.example.todoapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import androidx.room.Delete

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Query("SELECT * FROM categories ORDER BY categoryName ASC")
    fun getAllCategories(): LiveData<List<CategoryEntity>>

    @Query("DELETE FROM categories WHERE categoryName = :categoryName")
    suspend fun deleteCategory(categoryName: String)
}