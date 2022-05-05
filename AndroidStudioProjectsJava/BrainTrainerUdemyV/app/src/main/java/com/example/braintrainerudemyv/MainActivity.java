 package com.example.braintrainerudemyv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

 public class MainActivity extends AppCompatActivity {

     Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.button);
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);


    }
}