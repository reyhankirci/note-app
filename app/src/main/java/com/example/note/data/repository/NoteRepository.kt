package com.example.note.data.repository

import com.example.note.data.local.room.dao.NoteDao
import com.example.note.data.local.room.entities.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    fun loadAllNotes() : Flow<List<Note>> {
        return noteDao.loadAllNotes()
    }
}