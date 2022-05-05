package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(); // with a handler we need a runnable to run some code
        //timerI();
        timerII();

    }

    private void timerI() {
        /**
         * Do something till the end of time
         */

        runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "10 secs have past", Toast.LENGTH_SHORT).show();
                handler.postDelayed(this, 10000);

            }
        };

        handler.post(runnable);
    }


    private void timerII(){
        /**
         * Count down timer
         */

        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Seconds left:", String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "No more count down", Toast.LENGTH_SHORT).show();

            }
        };

        countDownTimer.start();

    }
}


