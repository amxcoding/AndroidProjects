package com.example.noteroom.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteroom.repository.NoteRepository

class NoteViewModelProviderFactory(
    val application: Application,
    private val noteRepository: NoteRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(application, noteRepository) as T
    }

}