package com.example.noteroom.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteroom.model.Note

@Dao
interface NoteDao {

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addNote(note: Note)
//
//    @Query("SELECT * FROM note_table ORDER BY id ASC")
//    fun getALlNotes(): LiveData<List<Note>>

    // TODO: update, delete, search



}