package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notes.adapters.NotesRecyclerAdapter;
import com.example.notes.models.Note;
import com.example.notes.persistence.NoteRepository;
import com.example.notes.util.VerticalSpacingItemDecorator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity implements
        NotesRecyclerAdapter.OnNoteListener,
        View.OnClickListener
{

    private static final String TAG = "NotesListActivity";

    //Ui components
    private RecyclerView mRecyclerView;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    //vars
    private ArrayList<Note> mNotes;
    private NotesRecyclerAdapter mNotesRecyclerAdapter;
    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        mRecyclerView = findViewById(R.id.recyclerview);
        toolbar =  findViewById(R.id.toolbar);
        floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(this);

        mNoteRepository = new NoteRepository(this);
        mNotes = new ArrayList<>();

        initRecyclerView();
        retrieveNotes();
//        insertFakeNotes();
        setToolbar();

        Log.d(TAG, "onCreate: tread" + Thread.currentThread().getName());

    }

    /*
    Getting data through livedata happens on the background tread
     */
    private void retrieveNotes() {
        mNoteRepository.retrieveNoteTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) { // if anything is changed on the database it wil requery it remove all and add all.
                if (mNotes.size() > 0) {
                    mNotes.clear();
                }

                if (notes != null) {
                    mNotes.addAll(notes);
                }
                mNotesRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); // because we are using a linear layout/ grid layout --> use grid layout manager
        mRecyclerView.setLayoutManager(layoutManager);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this); // refers to this interface onNoteListener
        VerticalSpacingItemDecorator verticalSpacingItemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(verticalSpacingItemDecorator);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    private void insertFakeNotes() {
        for (int i = 0; i < 5; i++) {
            Note note = new Note("title #" + i, "content #" + i, "Dec 2019");
            mNotes.add(note);
        }

        Log.d(TAG, "insertFakeNotes: " + mNotes.size());
        mNotesRecyclerAdapter.notifyDataSetChanged();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        setTitle("Notes");
    }

    /**
     * Inside here you navigate to the new activity
     * @param position
     */
    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, NoteActivity.class);
        Log.d(TAG, "onNoteClick: oNoteClick clicked " + position);


        /*
        Don't attack very large datasets as extra example arrayList, small list < 50 doable.
        More will give memory error
         */
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);

    }

    private void deleteNote(Note note) {
        mNotes.remove(note);
        mNotesRecyclerAdapter.notifyDataSetChanged();

        mNoteRepository.deleteNote(note);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) { // concatenate to also swipe to the right | ...
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNotes.get(viewHolder.getAdapterPosition()));

        }
    };
}