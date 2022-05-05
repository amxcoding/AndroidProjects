package com.example.multipleactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> names;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        names = new ArrayList<String>(asList("name 1", "name 2", "name 3", "name 4"));

        //connect arraylist to adapter
        listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, names);

        //connect adapter to arraylist
        listView.setAdapter(listAdapter);

        nextActivity();

    }


    public void nextActivity() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pressedName = names.get(position);
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("name", pressedName);

                startActivity(intent);
            }
        });
    }
}