package com.example.note.ui.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.local.room.entities.Category
import com.example.note.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    fun addCategory(categoryName: String) {
        viewModelScope.launch {
            try {
                val category = Category(categoryName)
                categoryRepository.insertCategory(category)
            } catch (error: Throwable) {
                Log.d("Add Category Error ", error.message.toString())
            }
        }
    }
}