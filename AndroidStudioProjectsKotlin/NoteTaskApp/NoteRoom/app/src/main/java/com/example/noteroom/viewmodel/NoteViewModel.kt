package com.example.noteroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteroom.model.Note
import com.example.noteroom.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
    application: Application,
    private val noteRepository: NoteRepository
): AndroidViewModel(application) {

//    fun addNote(note: Note) = viewModelScope.launch {
//        noteRepository.addNote(note)
//    }
//
//    fun getAllNotes() = noteRepository.getAllNotes()

    // TODO: add others

}