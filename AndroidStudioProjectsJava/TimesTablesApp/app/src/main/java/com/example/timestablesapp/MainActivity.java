package com.example.timestablesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar timesTableSeekBar;
    ListView timesTableListView;
    TextView timesTableTextView;

    ArrayList<Integer> arrayList;
    ArrayAdapter<Integer> adapter;

    static final int MAX_TABLE = 20;
    static final int MIN_TABLE = 1;
    static final int STARTING_POSITION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timesTableSeekBar = (SeekBar) findViewById(R.id.seekBar);
        timesTableListView = (ListView) findViewById(R.id.listView);
        timesTableTextView = (TextView) findViewById(R.id.textViewCurrentTable);

        setArrayList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList); // connect arraylist to adapter
        timesTableListView.setAdapter(adapter); // connect adapter to list view
        timesTableSeekBar.setProgress(STARTING_POSITION);
        timesTableTextView.setText("Current table: " + STARTING_POSITION);

        setSeekBar();
    }

    private void setSeekBar() {
        timesTableSeekBar.setMax(MAX_TABLE);

        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < MIN_TABLE) {
                    updateArrayList(MIN_TABLE);
                    timesTableSeekBar.setProgress(MIN_TABLE);
                } else {
                    updateArrayList(progress);
                    setTextView(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setArrayList() {
        this.arrayList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            this.arrayList.add(i);
        }
    }

    private void updateArrayList(int position) {
        setArrayList();
        ArrayList<Integer> currentArraylist  = this.arrayList;

        for (int i = 0; i <= currentArraylist.size() - 1; i++) {
            int updated = currentArraylist.get(i) * position;
            currentArraylist.set(i, updated);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList); // connect arraylist to adapter
        timesTableListView.setAdapter(adapter); // connect adapter to list view

    }

    private void setTextView(int position) {
        String message = "Current table: " + position;
        timesTableTextView.setText(message);

    }


}