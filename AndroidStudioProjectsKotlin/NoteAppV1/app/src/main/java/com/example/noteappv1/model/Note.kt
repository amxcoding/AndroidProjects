package com.example.noteappv1.model

import androidx.room.Entity
import androidx.room.PrimaryKey


// TODO make parcelable for updating note
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val noteTitle: String,
    val noteBody: String,
    val noteDate: String
) {

    /*
    TODO: add reminders , add the today date on create note
     */

}