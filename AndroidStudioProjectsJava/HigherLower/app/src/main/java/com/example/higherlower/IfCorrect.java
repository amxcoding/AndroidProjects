package com.example.higherlower;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class IfCorrect extends AppCompatActivity {

    Button playAgain;
    Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_if_correct);

        playAgain = (Button) findViewById(R.id.playAgainButton);
        quit = (Button) findViewById(R.id.quitButton);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playAgainButton:
                Log.i("Info", "Play again pressed");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.quitButton:
                Log.i("Info", "Quit pressed");
                this.finishAffinity();
                break;
            default:
                break;
        }
    }

}