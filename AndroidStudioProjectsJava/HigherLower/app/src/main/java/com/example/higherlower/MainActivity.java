package com.example.higherlower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.InCallService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button guessButton;
    private TextView randomNumberText;
    private EditText guessedNumberText;
    private Random random;
    private static final int MAX = 20;
    private int hiddenNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.guessButton = (Button) findViewById(R.id.guessButton);
        this.guessedNumberText = (EditText) findViewById(R.id.editTextNumber);
        this.randomNumberText = (TextView) findViewById(R.id.randomNumberText);
        this.random = new Random();
        this.hiddenNumber = nextRandomInt(MAX);

        String randomNr = String.valueOf(hiddenNumber);
        randomNumberText.setText(randomNr);
    }

    private int nextRandomInt(int max) {
        int min = 1;

        return this.random.nextInt(max - min + 1) + min;
    }

    @Override
    public void onClick(View v) {
        int guessedNr = Integer.parseInt(guessedNumberText.getText().toString());
        Log.i("Info", "Guess pressed");

        if (guessedNr > hiddenNumber) {
            Toast.makeText(this, "Lower!", Toast.LENGTH_LONG).show();
        } else if (guessedNr <  hiddenNumber) {
            Toast.makeText(this, "Higher!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, IfCorrect.class);
            startActivity(intent);
        }
    }
}