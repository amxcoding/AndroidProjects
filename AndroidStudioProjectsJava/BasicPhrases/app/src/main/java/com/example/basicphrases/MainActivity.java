package com.example.basicphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer; // to get the audio file
    Button buttonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View v) {
        int viewID = v.getId();

        switch (viewID) {
            case R.id.button1:
                mediaPlayer = MediaPlayer.create(this, R.raw.doyouspeakenglish);
                mediaPlayer.start();
                break;
            case R.id.button2:
                mediaPlayer = MediaPlayer.create(this, R.raw.hello);
                mediaPlayer.start();
                break;
            case R.id.button3:
                mediaPlayer = MediaPlayer.create(this, R.raw.ilivein);
                mediaPlayer.start();
                break;
            case R.id.button4:
                mediaPlayer = MediaPlayer.create(this, R.raw.please);
                mediaPlayer.start();
                break;
            case R.id.button5:
                mediaPlayer = MediaPlayer.create(this, R.raw.goodevening);
                mediaPlayer.start();
                break;
            case R.id.button6:
                mediaPlayer = MediaPlayer.create(this, R.raw.howareyou);
                mediaPlayer.start();
                break;
            case R.id.button7:
                mediaPlayer = MediaPlayer.create(this, R.raw.mynameis);
                mediaPlayer.start();
                break;
            case R.id.button8:
                mediaPlayer = MediaPlayer.create(this, R.raw.welcome);
                mediaPlayer.start();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void playPhrase(View view) {
        buttonPressed = (Button) view;
        String nameOfFile = buttonPressed.getTag().toString();

        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(nameOfFile, "raw", getPackageName()));
        mediaPlayer.start();

    }

}