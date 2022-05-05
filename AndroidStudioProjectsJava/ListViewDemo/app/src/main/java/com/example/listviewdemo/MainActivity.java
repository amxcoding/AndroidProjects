package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.myListView);
        arrayList = new ArrayList<>();

        arrayList.add("Nick");
        arrayList.add("Sarah");
        arrayList.add("John");
        arrayList.add("Fido");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList); // set arrayAdapter to arrayList and how to show it -> simple list

        myListView.setAdapter(arrayAdapter); // connecting adapter to listView

        onClick();

    }

    public void onClick() {
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("pressed", arrayList.get(position));
                view.setVisibility(View.GONE);

            }
        });
    }


}