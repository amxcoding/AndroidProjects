package com.example.eggtimerudemyv;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView countDownTextView;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    Button startButton;

    private static final int SEEKBAR_MAX = 600;
    private static final int SEEKBAR_START_POSITION = 30;

    private int startTime;
    private boolean counterIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        countDownTextView = findViewById(R.id.countDownTextView);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
        startButton = findViewById(R.id.startButton);
        counterIsActive = false;

        setSeekBar();
    }

    private void setSeekBar() {
        timerSeekBar.setMax(SEEKBAR_MAX);
        timerSeekBar.setProgress(SEEKBAR_START_POSITION);
        updateTimer(SEEKBAR_START_POSITION);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void updateTimer(int timeInSeconds) {
        String message;
        int minutes = timeInSeconds / 60; // rounds it down to int
        int seconds = timeInSeconds - (60 * minutes);

        message = String.format("%02d:%02d", minutes, seconds);

        countDownTextView.setText(message);

    }


    public void start(View view) {
        Log.i("Info", "Start pressed.");
        String startButtonText = "Stop";
        startTime = timerSeekBar.getProgress();

        if (counterIsActive) {
            resetTimer();

        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText(startButtonText);

            countDownTimer = new CountDownTimer((startTime * 1000), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    mediaPlayer.start();
                    resetTimer();

                }
            };
            countDownTimer.start();

        }
    }

    private void resetTimer() {
        String startButtonText = "Start";
        counterIsActive = false;
        countDownTimer.cancel();
        timerSeekBar.setProgress(startTime);
        timerSeekBar.setEnabled(true);
        updateTimer(startTime);
        startButton.setText(startButtonText);

    }




}