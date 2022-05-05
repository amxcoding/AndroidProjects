package com.example.noteappv1.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.noteappv1.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = IGNORE)
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    //TODO: update, insert, search note
}