package com.example.hidedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void onClick(View view) {
        Button buttonPressed = (Button) view;
        String buttonTag = buttonPressed.getTag().toString();

        textView.setVisibility(Integer.parseInt(buttonTag));


    }
}