package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static SharedPreferences sharedPreferences;

    private ListView listView;
    private ArrayAdapter arrayAdapter;

    private static List<String> addressesList;
    private static List<Double> latitudeList;
    private static List<Double> longitudeList;

    private static boolean pressed = false;
    private static double latitudeDouble;
    private static double longitudeDouble;


    public static List<String> getAddressesList() {
        return addressesList;
    }

    public static List<Double> getLatitudeList() {
        return latitudeList;
    }

    public static List<Double> getLongitudeList() {
        return longitudeList;
    }

    public static boolean isPressed() {
        return pressed;
    }

    public static double getLatitudeDouble() {
        return latitudeDouble;
    }

    public static double getLongitudeDouble() {
        return longitudeDouble;
    }

    public static void setPressed(boolean pressed) {
        MainActivity.pressed = pressed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.memorableplaces", Context.MODE_PRIVATE);

        listView = findViewById(R.id.listView);

        try {
            ArrayList<String> checkList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("addresses", ObjectSerializer.serialize(new ArrayList<String>())));
            if (checkList == null) {
                addressesList = new ArrayList<>();
                latitudeList = new ArrayList<>();
                longitudeList = new ArrayList<>();
            } else {
                getArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        // connect arrayList to adapter +  connect listView to adapter
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, addressesList);
        listView.setAdapter(arrayAdapter);

        goToLocationAddress();

    }

    @Override
    protected void onStart() {
        super.onStart();
        arrayAdapter.notifyDataSetChanged();
    }

    public void search(View view) {
        Intent intent = new Intent(getApplicationContext(), FindOnMap.class);

        startActivity(intent);
    }

    private void goToLocationAddress() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pressed = true;
                latitudeDouble = latitudeList.get(position);
                longitudeDouble = longitudeList.get(position);

                Intent intent = new Intent(getApplicationContext(), FindOnMap.class);

                startActivity(intent);
            }
        });
    }

    public static void saveArray() {

        try {
            sharedPreferences.edit().putString("addresses", ObjectSerializer.serialize((Serializable) addressesList)).apply();
            sharedPreferences.edit().putString("latitudes", ObjectSerializer.serialize((Serializable) latitudeList)).apply();
            sharedPreferences.edit().putString("longitudes", ObjectSerializer.serialize((Serializable) longitudeList)).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getArray() {

        try {
            addressesList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("addresses", ObjectSerializer.serialize(new ArrayList<String>())));
            latitudeList = (ArrayList<Double>) ObjectSerializer.deserialize(sharedPreferences.getString("latitudes", ObjectSerializer.serialize(new ArrayList<String>())));
            longitudeList = (ArrayList<Double>) ObjectSerializer.deserialize(sharedPreferences.getString("longitudes", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //saveArray();

        Toast.makeText(this, "onStop called", Toast.LENGTH_SHORT).show();
    }
}
