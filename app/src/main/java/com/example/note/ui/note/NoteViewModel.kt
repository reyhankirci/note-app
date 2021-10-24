package com.example.note.ui.note

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.note.data.local.room.entities.Note
import com.example.note.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    val notesLiveData: LiveData<List<Note>> = noteRepository.loadAllNotes().asLiveData()

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.deleteNote(note)
            } catch (error: Throwable) {
                Log.d("Delete Note Error", error.message.toString())
            }
        }
    }
}