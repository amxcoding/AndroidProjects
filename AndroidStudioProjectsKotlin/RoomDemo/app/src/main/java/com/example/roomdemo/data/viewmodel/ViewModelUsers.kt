package com.example.roomdemo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.data.repository.RepositoryUsers
import com.example.roomdemo.data.model.user.User
import com.example.roomdemo.data.model.user.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * The viewmodel's role is to provide data to the UI and survive configuration changes. A viewmodel acts as a communication
 * center between the repository and the ui.
 */
class ViewModelUsers(
    application: Application
) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repositoryUsers: RepositoryUsers

    /*
    init is first activated when ViewModel is called
     */
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repositoryUsers = RepositoryUsers(userDao)
        readAllData = repositoryUsers.readAllData
    }

    /*
    Always launch database actions from the background thread
    viewModelScope is a kotlin coroutines object
    others are GlobalScope
    code is runned in the background thread
     */
    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUsers .addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUsers.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUsers.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUsers.deleteAllUsers()
        }
    }

}