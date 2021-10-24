package com.example.note.data.local.room.dao


import androidx.room.*
import com.example.note.data.local.room.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note ORDER BY createDate DESC")
    fun loadAllNotes(): Flow<List<Note>>
}