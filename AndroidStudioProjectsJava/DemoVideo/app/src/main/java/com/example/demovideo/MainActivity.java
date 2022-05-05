package com.example.demovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView demoVideo;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoVideo = (VideoView) findViewById(R.id.videoView);
        mediaController = new MediaController(this);

        demoVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.demovideo);

        mediaController.setAnchorView(demoVideo); // link to videoView
        demoVideo.setMediaController(mediaController); // control videoView
        demoVideo.start();
    }



}