package com.example.onetoone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.onetoone.database.ULDatabase
import com.example.onetoone.entities.Library
import com.example.onetoone.entities.User
import com.example.onetoone.entities.relations.UserAndLibrary
import com.example.onetoone.repository.ULRepository
import kotlinx.coroutines.launch

class ULViewModel(
    application: Application,
    private val ulRepository: ULRepository
) : AndroidViewModel(application) {

    fun insertOneUser(user: User) = viewModelScope.launch {
        ulRepository.insertOneUser(user)
    }

    fun insertOneLibrary(library: Library) = viewModelScope.launch {
        ulRepository.insertOneLibrary(library)
    }

    fun insertManyUsers(userList: List<User>) = viewModelScope.launch {
        ulRepository.insertManyUsers(userList)
    }

    fun insertManyLibraries(libraryList: List<Library>) = viewModelScope.launch {
        ulRepository.insertManyLibraries(libraryList)
    }

    fun getUsersAndLibraries(userName: String) = ulRepository.getUsersAndLibraries(userName)


}