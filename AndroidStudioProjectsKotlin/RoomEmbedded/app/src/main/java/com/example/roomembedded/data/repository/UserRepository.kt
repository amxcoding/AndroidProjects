package com.example.roomembedded.data.repository

import androidx.lifecycle.LiveData
import com.example.roomembedded.data.model.user.User
import com.example.roomembedded.data.model.user.UserDao

class UserRepository(
    private val userDao: UserDao
) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }


}