package com.example.notes.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.notes.models.Note;
import com.example.notes.persistence.NoteDao;

/*
Async good for executing a single task to completion and then being destroyed as soon as done
 */
public class DeleteAsyncTask extends AsyncTask<Note, Void, Void> { //<input, ??, output>

    private static final String TAG = "InsertAsyncTask";
    private NoteDao mNoteDao;

    public DeleteAsyncTask(NoteDao dao) {
        this.mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: tread" + Thread.currentThread().getName());
        mNoteDao.delete(notes);
        return null;
    }

}
