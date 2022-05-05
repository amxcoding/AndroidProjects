package com.example.noteroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO make parcelize
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteBody: String,
    val noteDate: String //TODO change type to datetime?
) {
}