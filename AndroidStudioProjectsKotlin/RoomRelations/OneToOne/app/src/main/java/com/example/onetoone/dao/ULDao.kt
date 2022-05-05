package com.example.onetoone.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onetoone.entities.Library
import com.example.onetoone.entities.User
import com.example.onetoone.entities.relations.UserAndLibrary


@Dao
interface ULDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneLibrary(library: Library)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManyUsers(userList: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManyLibraries(libraryList: List<Library>)


    @Query("SELECT * FROM User WHERE name = :userName")
    fun getUsersAndLibraries(userName: String): List<UserAndLibrary>

}