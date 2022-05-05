package com.example.onetoone.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onetoone.repository.ULRepository

class ULViewModelProviderFactory(
    val application: Application,
    private val ulRepository: ULRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ULViewModel(application, ulRepository) as T
    }
}