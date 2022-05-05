package com.example.stayactive.database.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.stayactive.model.User;

import io.reactivex.Completable;

/*
Interface class because room will generate the necessary code for it.
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Completable insertUser(User user);

    @Update
    Completable updateUser(User user);

    @Delete
    Completable deleteUser(User user);


}
