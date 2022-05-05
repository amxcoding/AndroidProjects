package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        shared preferences can be shared between apps
        or can be kept private

        you can save basic types in shared preferences: String/ int etc.
         */
        sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", "Piet").apply(); // first string is the string name and second is the value, apply() saves the value
        System.out.println(sharedPreferences.contains("username"));
        String username = sharedPreferences.getString("username", "Not found"); // 2nd value is the default in case we didnt find anything

        Log.i("Username", username);

        /*
        Note: after commenting out line 26 we still could retrieve the username because it is now saved
         */

        saveArray();
    }


    private void saveArray() {
        ArrayList<String> friends = new ArrayList<>(asList("Fido", "Sarah", "Jones"));

//        try {
//            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
//            System.out.println(ObjectSerializer.serialize(friends));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            ArrayList<String> newFriends = new ArrayList<>();
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends",  ObjectSerializer.serialize(new ArrayList<String>())));
            System.out.println(newFriends.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }






    }
}