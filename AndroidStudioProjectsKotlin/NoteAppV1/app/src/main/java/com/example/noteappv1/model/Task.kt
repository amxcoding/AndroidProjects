package com.example.noteappv1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO implement ROOM + Parcelize
@Entity
class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val taskDiscription: String,
    val reminderDate: String
) {
    /*
    TODO: reminderDate should be of type Date
     */
}