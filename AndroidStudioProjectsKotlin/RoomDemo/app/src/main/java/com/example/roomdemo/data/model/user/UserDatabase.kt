package com.example.roomdemo.data.model.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * An object which is created only once and used everywhere, a Singleton Pattern is used. A Singleton Pattern is a design pattern
 * that restricts the instantiation of the class to only one instance.
 *
 * To use the Singleton Pattern we need to make a singleton class. A singleton class is defined in such a way that only one instance of
 * the class can be created and used everywhere. It is used where we need only one instance of the class like NetworkService, DatabaseService etc.
 * It is done to reduce the resources needed to create the same object over and over.
 *
 * Properties of a singleton class:
 * 1. Only one instance: The singleton class has only one instance and this is done by providing an instance of the class within the class.
 * The outer classes and subclasses should be prevented to create the instance.
 * 2. Globally accessible:
 * The instance of the singleton class should be globally accessible so that each class can use it.
 *
 * Rules for making a singleton class:
 * 1. A private constructor
 * 2. A static reference of its class
 * 3. One static method
 * 4. Globally accessible object reference
 * 5. Consistency across multiple threads --> use annotation @volatile
 *
 *
 */

/*
UserDatabase servers as the main access point for the connection to the database
Note needs to be abstract and extend RoomDatabase()
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao // abstract fun that returns the user DAO

    /*
    Singleton:
    Always use one instance of the room database.
    Multiple instances take a toll on the performance.
     */
    companion object {
        @Volatile // changes to this field are made visible to other threads
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            /**
             * protection from concurrent execution by multiple threads
             */
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

    /*
    We always use one instance of the room database, multiple instances take a toll on the performance
     */
}