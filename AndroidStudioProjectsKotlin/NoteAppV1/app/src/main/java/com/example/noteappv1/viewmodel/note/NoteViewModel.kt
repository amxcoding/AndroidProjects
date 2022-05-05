package com.example.noteappv1.viewmodel.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappv1.model.Note
import com.example.noteappv1.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    application: Application,
    private val noteRepository: NoteRepository
): AndroidViewModel(application) {

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.addNote(note)
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    //TODO update, delete and search notes


}