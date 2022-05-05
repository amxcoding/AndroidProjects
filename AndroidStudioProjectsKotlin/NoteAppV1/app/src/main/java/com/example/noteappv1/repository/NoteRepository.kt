package com.example.noteappv1.repository

import com.example.noteappv1.database.NoteTaskDatabase
import com.example.noteappv1.model.Note

class NoteRepository(
    private val database: NoteTaskDatabase
) {

    suspend fun addNote(note: Note) = database.getNoteDao().addNote(note)

    fun getAllNotes() = database.getNoteDao().getAllNotes()

    //TODO update, delete and search notes
}