package com.example.roomdemo.data.repository

import androidx.lifecycle.LiveData
import com.example.roomdemo.data.model.user.User
import com.example.roomdemo.data.model.user.UserDao

/**
 * A repository class abstracts access to multiple data sources.
 *
 */

class RepositoryUsers(
    private val userDao: UserDao
) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    /*
    Suspend keyword for kotlin coroutines
     */
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}