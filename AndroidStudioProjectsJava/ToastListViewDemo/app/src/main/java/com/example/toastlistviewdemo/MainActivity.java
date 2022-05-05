package com.example.toastlistviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    ListView myListview;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListview = (ListView) findViewById(R.id.myListView);
        arrayList = new ArrayList<String>(asList("Nick", "Sarah", "John", "Fido"));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList); // connect arraylist to adapter + layout
        myListview.setAdapter(adapter); //connect adapter with arrayList to ListView

        onLoad();
    }


    public void onLoad() {
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pressedName = arrayList.get(position);
                Toast.makeText(MainActivity.this, pressedName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}