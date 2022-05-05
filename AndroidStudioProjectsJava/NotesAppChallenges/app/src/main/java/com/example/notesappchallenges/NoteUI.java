package com.example.notesappchallenges;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.HashSet;

public class NoteUI extends AppCompatActivity {

    private EditText titleNoteUI;
    private EditText noteNoteUI;

    private int position;
    private String title;
    private String noteText;
    private Note note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleNoteUI = findViewById(R.id.ediTextTitle);
        noteNoteUI = findViewById(R.id.editTextNote);
        Intent intent = getIntent();

        position = intent.getIntExtra("position", 0);
        title = "Enter title...";
        noteText = "Enter note...";

        getNote();
    }

    private void getNote() {
        if (position >= 0) {
            note = MainActivity.getNoteList().get(position);

            title = (note.getNoteName() != null) ? note.getNoteName() : "Enter title...";
            noteText = (note.getNoteText() != null) ? note.getNoteText() : "Enter note...";

            titleNoteUI.setText(title);
            noteNoteUI.setText(noteText);
        } else {
            note = new Note("", "");
            MainActivity.getNoteList().add(note);
            position = MainActivity.getNotePosition(note);
            Data.saveData("noteListSize", String.valueOf(MainActivity.getNoteList().size()));
            System.out.println(MainActivity.getNoteList().size());
        }

        writeNote();
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            title = (titleNoteUI.getText() != null) ? String.valueOf(titleNoteUI.getText()) : "Enter title...";
            noteText = (noteNoteUI.getText() != null) ? String.valueOf(noteNoteUI.getText()) : "Enter note...";

            note.setNoteName(title);
            note.setNoteText(noteText);
            saveNote(position, title, noteText);
            MainActivity.getArrayAdapter().notifyDataSetChanged();
        }
    };

    public static void saveNote(int position, String valueTitle, String valueNote) {
        String titleKey = "title" + position;
        String noteKey = "note" + position;

        Data.saveData(titleKey, valueTitle);
        Data.saveData(noteKey, valueNote);
    }


    private void writeNote() {
        titleNoteUI.addTextChangedListener(textWatcher);
        noteNoteUI.addTextChangedListener(textWatcher);
    }

}