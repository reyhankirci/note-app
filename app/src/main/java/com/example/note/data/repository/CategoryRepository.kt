package com.example.note.data.repository

import com.example.note.data.local.room.dao.CategoryDao
import com.example.note.data.local.room.entities.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

    suspend fun insertCategory(category : Category) {
        categoryDao.insert(category)
    }

    suspend fun updateCategory(category: Category) {
        categoryDao.update(category)
    }

    suspend fun deleteCategory(category: Category) {
        categoryDao.delete(category)
    }

    fun getCategoryList(): Flow<List<Category>> {
        return categoryDao.loadAllCategories()
    }
}