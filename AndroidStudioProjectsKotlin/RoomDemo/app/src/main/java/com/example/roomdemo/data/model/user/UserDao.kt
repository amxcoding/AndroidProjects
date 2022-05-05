package com.example.roomdemo.data.model.user

import androidx.lifecycle.LiveData
import androidx.room.*

/*
Dao needs to be an interface --> DAO = data access object
This interface will contain all the methods used for accessing the data
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // If new exact same user ignore that
    suspend fun addUser(user: User) // suspend added because coroutines used

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>> // Livedata keeps an eye on the list of users

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()


}