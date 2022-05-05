package com.example.alertdemo;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static TextView textView;

    public static TextView getTextView() {
        return textView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewLanguage);

        AppContext.setContext(this);
        askLanguage(false);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void askLanguage(boolean setLanguage) {
        String language = SavePreferences.getData("language");

        if (setLanguage || language.equals("Error") || language.isEmpty()) {
            Alert.alert();
        } else {
            textView.setText(language);
        }
    }


    /*
    to create the menu
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /*
    when you choose an option from the menu
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.setlanguage) {
            askLanguage(true);
            return true;
        }
        return false;
    }



}