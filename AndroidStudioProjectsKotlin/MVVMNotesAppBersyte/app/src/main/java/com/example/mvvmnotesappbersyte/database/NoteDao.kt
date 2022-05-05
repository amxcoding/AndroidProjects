package com.example.mvvmnotesappbersyte.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmnotesappbersyte.model.Note


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE :query OR noteBody Like :query")
    fun searchNote(query: String?): LiveData<List<Note>>


}