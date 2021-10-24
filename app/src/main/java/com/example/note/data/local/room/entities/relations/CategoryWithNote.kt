package com.example.note.data.local.room.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.note.data.local.room.entities.Category
import com.example.note.data.local.room.entities.Note


data class CategoryWithNote (
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "name",
        entityColumn = "categoryName"
    )
    val notes: List<Note>
)