package com.example.sounddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer; // setup for playing sound effect
    SeekBar volumeSeekBar;
    SeekBar musicSeekBar;
    AudioManager audioManager; // to control the volume

    int maxVolume;
    int currentVolume;

    int maxMusic;

    Timer timer; // to update the seekBar continue as the piece of sound is being played we need a Timer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.demo1);
        volumeSeekBar = (SeekBar) findViewById(R.id.volumeSeekBar);
        musicSeekBar = (SeekBar) findViewById(R.id.musicSeekBar);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        maxMusic = mediaPlayer.getDuration();
        musicSeekBar.setMax(maxMusic);

        volumeSeekBar.setMax(maxVolume); // set max of seekbar to max volume
        volumeSeekBar.setProgress(currentVolume);

        timer = new Timer();

        setVolume();
        setMusic();

    }

    public void play(View v) {
        mediaPlayer.start();
        updateMusicSeekBarContinually();
    }

    public void pause(View v) {
        mediaPlayer.pause();
    }

    public void setVolume() {
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { // when user starts touching

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { // when user stops touching seekbar

            }
        });
    }


    public void setMusic() {
        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();

            }
        });
    }

    //TODO:
    // cancel timer when music stops

    private void updateMusicSeekBarContinually() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                musicSeekBar.setProgress(mediaPlayer.getCurrentPosition());

            }
        }, 0, 1000); // 3 inputs task, start, and update every 1000 millisecond
        // the smaller the smoother but the more resources it will use

    }

}
