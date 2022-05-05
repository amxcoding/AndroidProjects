package com.example.fadinganimations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageMain;
    ImageView imageSecond;
    boolean bartIsShowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageMain = (ImageView) findViewById(R.id.imageMain);
        imageSecond = (ImageView) findViewById(R.id.imageSecond);
        bartIsShowing = true;

        imageMain.setX(-1000);
        imageMain.animate().translationXBy(1000).rotation(3600).setDuration(5000);
    }

    public void fade(View v) {
        Log.i("Info", "Image tapped");

//        if (imageMain.getAlpha() == 1) {
//            imageMain.animate().alpha(0).setDuration(2000);
//            imageSecond.animate().alpha(1).setDuration(2000);
//        } else {
//            imageMain.animate().alpha(1).setDuration(2000);
//            imageSecond.animate().alpha(0).setDuration(2000);
//        }

        if (bartIsShowing) {
            bartIsShowing = false;
            imageMain.animate().alpha(0).setDuration(2000);
            imageSecond.animate().alpha(1).setDuration(2000);
        } else {
            bartIsShowing = true;
            imageMain.animate().alpha(1).setDuration(2000);
            imageSecond.animate().alpha(0).setDuration(2000);
        }
    }


    public void translate(View v) {
        if (bartIsShowing) {
            bartIsShowing = false;
            imageMain.animate().translationYBy(2000).setDuration(2000);
            imageSecond.animate().translationXBy(-2000).setDuration(2000);
        } else {
            bartIsShowing = true;
            imageMain.animate().translationYBy(-2000).setDuration(2000);
            imageSecond.animate().translationXBy(2000).setDuration(2000);
        }
    }

}