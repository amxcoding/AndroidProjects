package com.example.noteappv1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteappv1.model.Note
import com.example.noteappv1.model.Task


//TODO add Task::class
@Database(entities = [Note::class], version = 1)
abstract class NoteTaskDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
    abstract fun getTaskDao(): TaskDao

    // static object
    companion object {
        @Volatile
        private var instance: NoteTaskDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteTaskDatabase::class.java,
            "note_db"
        ).build()

    }


}
