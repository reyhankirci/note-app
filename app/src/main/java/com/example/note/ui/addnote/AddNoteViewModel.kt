package com.example.note.ui.addnote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.note.data.local.room.entities.Category
import com.example.note.data.local.room.entities.Note
import com.example.note.data.repository.CategoryRepository
import com.example.note.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.security.auth.Subject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val categoryRepository: CategoryRepository
): ViewModel() {

    val categorySpinnerData: LiveData<List<Category>> = categoryRepository.getCategoryList().asLiveData()

    fun saveNote(subject: String, categoryName: String) {
        viewModelScope.launch {
            try {
                val note = Note(categoryName, subject, Date())
                noteRepository.insertNote(note)
            } catch (error: Throwable) {
                Log.d("Add Note Erro", error.message.toString())
            }
        }
    }
}