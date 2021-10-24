package com.example.note.data.local.room.dao

import androidx.room.*
import com.example.note.data.local.room.entities.Category
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM category")
    fun loadAllCategories(): Flow<List<Category>>
}