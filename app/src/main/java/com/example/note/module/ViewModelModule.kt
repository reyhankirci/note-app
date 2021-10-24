package com.example.note.module

import com.example.note.data.local.room.dao.CategoryDao
import com.example.note.data.local.room.dao.NoteDao
import com.example.note.data.repository.CategoryRepository
import com.example.note.data.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepository(categoryDao)
    }

    @Provides
    @ViewModelScoped
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }
}