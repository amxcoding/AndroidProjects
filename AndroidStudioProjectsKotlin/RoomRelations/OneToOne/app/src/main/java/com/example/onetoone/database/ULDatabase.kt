package com.example.onetoone.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onetoone.dao.ULDao
import com.example.onetoone.entities.Library
import com.example.onetoone.entities.User


@Database(
    entities = [
        User::class,
        Library::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ULDatabase: RoomDatabase() {

    abstract val ulDao: ULDao

    companion object {
        @Volatile
        private var INSTANCE: ULDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                ULDatabase::class.java,
                "ul_db"
            ).build().also {
                INSTANCE = it
            }
        }


    }


}