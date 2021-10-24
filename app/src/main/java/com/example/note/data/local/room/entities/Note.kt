package com.example.note.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class Note(
    val categoryName: String,
    val subject: String,
    val createDate: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}