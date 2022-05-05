package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView playButtonImageView;
    ImageView restartButtonImageView;
    TextView editTextTime;
    SeekBar setTimerSeekbar;

    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayerI;
    MediaPlayer mediaPlayerII;
    AudioManager audioManager; // to control volume
    Handler handler;
    Runnable runnable;

    private static final int SEEKBAR_MIN = 0;
    private static final int SEEKBAR_MAX = 3600; // 60 min
    private static final int SEEKBAR_START_POSITION = SEEKBAR_MIN;

    private int startTime;
    private int currentTime;
    private int saveTimer;

    private boolean play = false;
    private boolean firstStart = false;
    private boolean pausePressed = false;
    private boolean visible = true;
    private boolean stop = false;

    private float currentTranslationX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButtonImageView = findViewById(R.id.playPauseImageView);
        restartButtonImageView = findViewById(R.id.restartTimerImageView);
        editTextTime = findViewById(R.id.editTextTime);
        setTimerSeekbar = findViewById(R.id.timerSeekBar);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mediaPlayerII = MediaPlayer.create(this, R.raw.alarm);
        handler = new Handler();

        currentTranslationX = playButtonImageView.getTranslationX();

        startValueTimer();
        setTimer();


    }

    private void startValueTimer() {
        setTimerSeekbar.setProgress(SEEKBAR_START_POSITION); // stands automatically at 0

        updateTextTimerV2(SEEKBAR_START_POSITION);
    }

    private void updateTextTimer(int timeInSeconds) {
        String message;

        int positionInSeconds = timeInSeconds;
        int positionInMinutes = 0;

        while (positionInSeconds >= 60) {
            positionInMinutes++;
            positionInSeconds -= 60;
        }

        if (positionInMinutes >= 10) {
            message = positionInMinutes + ":";
        } else {
            message = "0" + positionInMinutes + ":";
        }

        if (positionInSeconds >= 10) {
            message += positionInSeconds;
        } else {
            message += "0" + positionInSeconds;
        }

        editTextTime.setText(message);
    }

    @SuppressLint("DefaultLocale")
    private void updateTextTimerV2(int timeInSeconds) {
        String message;

        int positionInSeconds = timeInSeconds;
        int positionInMinutes = 0;

        while (positionInSeconds >= 60) {
            positionInMinutes++;
            positionInSeconds -= 60;
        }

        message = String.format("%02d:%02d", positionInMinutes, positionInSeconds);

        editTextTime.setText(message);
    }

    private void setTimer() {
        setTimerSeekbar.setMax(SEEKBAR_MAX);

        setTimerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // update text timer from seekbar
                updateTextTimerV2(progress);
                startTime = progress;
                Log.i("Seekbar value", String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void play(View view) {
        if (!firstStart) {
            firstStart = true;
            currentTime = startTime;
        } else {
            currentTime = saveTimer;
        }

        if (currentTime == 0) {
            Toast.makeText(this, "Set timer", Toast.LENGTH_SHORT).show();
            firstStart = false;
        } else {
            if (!play) {
                play = true;
                setTimerSeekbar.setEnabled(false);
                playButtonImageView.setImageResource(R.drawable.pause);
                handler.removeCallbacksAndMessages(null);
                if (!visible) {
                    editTextTime.setVisibility(View.VISIBLE);
                }

                if (pausePressed) {
                    playButtonImageView.animate().translationXBy(currentTranslationX);
                    restartButtonImageView.setVisibility(View.INVISIBLE);
                    pausePressed = false;
                }

                countDownTimer = new CountDownTimer((currentTime * 1000), 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        saveTimer = currentTime;
                        updateTextTimerV2(currentTime);
                        currentTime--;
                    }

                    @Override
                    public void onFinish() {
                        play = false;
                        firstStart = false;
                        updateTextTimerV2(currentTime);
                        playButtonImageView.setImageResource(R.drawable.play);
                        setTimerSeekbar.setEnabled(true);

                        updateTextTimerV2(startTime); // shows last start time
                        setTimerSeekbar.setProgress(startTime); // set progress to last chosen time

                        mediaPlayerII.start(); // alarm sound


                    }
                };
                countDownTimer.start();

            } else {
                play = false;
                pausePressed = true;
                playButtonImageView.setImageResource(R.drawable.play);
                playButtonImageView.animate().translationXBy(-currentTranslationX);
                restartButtonImageView.setVisibility(View.VISIBLE);
                animateTimerText();

                countDownTimer.cancel();

            }
        }
    }


    public void restart(View view) {
        play = false;
        firstStart = false;
        pausePressed = false;
        playButtonImageView.setImageResource(R.drawable.play);
        playButtonImageView.animate().translationXBy(currentTranslationX);
        restartButtonImageView.setVisibility(View.INVISIBLE);
        setTimerSeekbar.setEnabled(true);
        handler.removeCallbacksAndMessages(null);
        if (!visible) {
            editTextTime.setVisibility(View.VISIBLE);
        }

        updateTextTimerV2(startTime); // shows last start time
        setTimerSeekbar.setProgress(startTime); // set progress to last chosen time

    }


    private void animateTimerText() {

        runnable = new Runnable() {
            @Override
            public void run() {

                if (visible) {
                    editTextTime.setVisibility(View.INVISIBLE);
                    visible = false;
                } else {
                    editTextTime.setVisibility(View.VISIBLE);
                    visible = true;
                }

                handler.postDelayed(this, 350);
            }
        };
        handler.post(runnable);
    }


}