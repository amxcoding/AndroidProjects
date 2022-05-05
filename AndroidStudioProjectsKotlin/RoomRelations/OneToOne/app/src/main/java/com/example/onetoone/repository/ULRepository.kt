package com.example.onetoone.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onetoone.dao.ULDao
import com.example.onetoone.database.ULDatabase
import com.example.onetoone.entities.Library
import com.example.onetoone.entities.User
import com.example.onetoone.entities.relations.UserAndLibrary

class ULRepository(
    private val ulDatabase: ULDatabase
) {

    suspend fun insertOneUser(user: User) = ulDatabase.ulDao.insertOneUser(user)

    suspend fun insertOneLibrary(library: Library) = ulDatabase.ulDao.insertOneLibrary(library)

    suspend fun insertManyUsers(userList: List<User>) = ulDatabase.ulDao.insertManyUsers(userList)

    suspend fun insertManyLibraries(libraryList: List<Library>) = ulDatabase.ulDao.insertManyLibraries(libraryList)

    fun getUsersAndLibraries(userName: String): List<UserAndLibrary> = ulDatabase.ulDao.getUsersAndLibraries(userName)

}