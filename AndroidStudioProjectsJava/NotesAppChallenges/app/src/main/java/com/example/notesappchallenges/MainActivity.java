package com.example.notesappchallenges;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<Note> noteList;
    private static CustomAdapter arrayAdapter;

    private ListView listView;
    private Intent intent;
    private FloatingActionButton floatingActionButtonInfo;
    private FloatingActionButton floatingActionButtonNew;
    public static List<Note> getNoteList() {
        return noteList;
    }

    public static CustomAdapter getArrayAdapter() {
        return arrayAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppContext.setContext(this);

        //Data.clearData();
        listView = findViewById(R.id.listView);
        intent = new Intent(getApplicationContext(), NoteUI.class);
        floatingActionButtonInfo = findViewById(R.id.info);
        private TextView textViewNewNote;

        private static int deletePosition;
        private boolean isFABOpen = false;

        floatingActionButtonNew = findViewById(R.id.newNote);
        textViewNewNote = findViewById(R.id.textViewNewNote);

        initializeList();

        arrayAdapter = new CustomAdapter(getApplicationContext(), noteList);
        listView.setAdapter(arrayAdapter);

        interactNote();
        floatButtonOnClick();
        newNoteOnClick();
    }

    public void interactNote() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Alert.alertDelete();
                deletePosition = position;
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateListView();
    }

    private void updateListView() {
        for (int position = 0; position <= noteList.size() - 1; position++) {
            String titleKey = "title" + position;
            String noteKey = "note" + position;

            String title = (Data.getData(titleKey).equals("Error") || Data.getData(titleKey).isEmpty()) ? "Enter title..." : Data.getData(titleKey);
            String note = (Data.getData(noteKey).equals("Error") || Data.getData(noteKey).isEmpty()) ? "" : Data.getData(noteKey);

            noteList.get(position).setNoteName(title);
            noteList.get(position).setNoteText(note);
        }

        arrayAdapter.notifyDataSetChanged();
    }

    private void initializeList() {
        int noteListSize = (Data.getData("noteListSize").equals("Error")) ? 1 : Integer.parseInt(Data.getData("noteListSize"));
        noteList = new ArrayList<>();
        System.out.println(noteListSize);

        for (int position = 0; position <= noteListSize - 1; position++) {
            noteList.add(position, new Note("Example", "Example note"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.addNewNote) {
            intent.putExtra("position", -1);

            startActivity(intent);
            return true;
        }
        return false;
    }

    public static int getNotePosition(Note note) {
        for (int position = 0; position <= noteList.size() - 1; position++) {
            Note checkedNote = noteList.get(position);
            if (note.equals(checkedNote)) {
                return position;
            }
        }
        return -1;
    }

    public static void deleteNote() {
        String deleteTitle = noteList.get(deletePosition).getNoteName();
        noteList.remove(deletePosition);

        int noteListSize = noteList.size();
        Data.clearData();
        Data.saveData("noteListSize", String.valueOf(noteListSize));

        for (int position = 0; position <= noteList.size() - 1; position++) {
            String title = noteList.get(position).getNoteName();
            String note = noteList.get(position).getNoteText();

            NoteUI.saveNote(position, title, note);
        }
        Toast.makeText(AppContext.getContext(), deleteTitle + " deleted", Toast.LENGTH_SHORT).show();
        arrayAdapter.notifyDataSetChanged();
    }

    private void floatButtonOnClick() {
        floatingActionButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
    }

    private void newNoteOnClick() {
        floatingActionButtonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("position", -1);
                startActivity(intent);
                closeFABMenu();
            }
        });
    }

    private void showFABMenu(){
        isFABOpen=true;
        floatingActionButtonNew.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        textViewNewNote.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        textViewNewNote.setVisibility(View.VISIBLE);
    }

    private void closeFABMenu(){
        isFABOpen=false;
        floatingActionButtonNew.animate().translationY(0);
        textViewNewNote.animate().translationY(0);
        textViewNewNote.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if(!isFABOpen){
            super.onBackPressed();
        }else{
            closeFABMenu();
        }
    }
}
