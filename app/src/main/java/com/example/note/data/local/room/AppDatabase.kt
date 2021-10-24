package com.example.note.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.note.data.local.room.dao.CategoryDao
import com.example.note.data.local.room.dao.NoteDao
import com.example.note.data.local.room.entities.Category
import com.example.note.data.local.room.entities.Note
import com.example.note.data.local.room.utils.DataConverter

@Database(
    version = 1,
    entities = [Note::class, Category::class],
    exportSchema = true
)
@TypeConverters(DataConverter:: class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
}