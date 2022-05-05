package com.example.notes.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes.models.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    long[] insertNotes(Note... notes);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes(); // Livedata is a data observation class part of the lifecycle library on android

    /*
    @Query("SELECT * FROM notes WHERE id = :id")
    List<Note> getNotesWithCustomQuery(int id);

    OR

    @Query("SELECT * FROM notes WHERE title LIKE :title")
    List<Note> getNotesWithCustomQuery(String title);

    EXAMPLE: If you want to search for 'Elizabeth' but don't know the exact name you can use in the activity class:
    getNoteWithCustomQuery("eli*")
     */

    @Delete
    int delete(Note... notes); // int will return how many rows were affected by the delete/update

    @Update
    int update(Note... notes);

}
