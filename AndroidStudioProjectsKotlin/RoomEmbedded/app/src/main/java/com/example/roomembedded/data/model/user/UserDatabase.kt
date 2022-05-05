package com.example.roomembedded.data.model.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    //Singleton
    companion object { // static
        @Volatile
        private var INSTANCE: UserDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) { // anonymous class
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_table ADD COLUMN streetName TEXT NOT NULL DEFAULT 'empty'")
                database.execSQL("ALTER TABLE user_table ADD COLUMN houseNumber INTEGER NOT NULL DEFAULT 0")
            }

        }

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            // protection from concurrent execution by multiple threads
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database",
                ).addMigrations(MIGRATION_1_2).allowMainThreadQueries().fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}