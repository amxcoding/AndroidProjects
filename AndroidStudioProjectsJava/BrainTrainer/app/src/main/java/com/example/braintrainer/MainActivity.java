package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBarDifficulty;
    TextView textViewDifficulty;

    private static final int SEEKBAR_MAX = 2;
    private static final int SEEKBAR_START = 0;
    private static int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarDifficulty = findViewById(R.id.seekBarDifficulty);
        textViewDifficulty = findViewById(R.id.textViewDifficulty);

        setDifficulty();
    }

    public void go(View view) {
        Log.i("Difficulty is", String.valueOf(difficulty));
        Intent intent = new Intent(this, GamePage.class);
        startActivity(intent);
    }

    private void setDifficulty() {
        seekBarDifficulty.setMax(SEEKBAR_MAX);
        setDifficultyText(SEEKBAR_START);

        seekBarDifficulty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setDifficultyText(progress);
                difficulty = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void setDifficultyText(int value) {
        String message = "Easy"; // default value is 1

        if (value == 1) {
            message = "Medium";
        } else if (value == 2) {
            message = "Hard";
        }

        textViewDifficulty.setText(message);

    }

    public static int getDifficulty() {
        return difficulty;
    }
}