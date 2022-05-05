package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView winnerText;
    Button playAgain;

    GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

    ImageView imageView00;
    ImageView imageView01;
    ImageView imageView02;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView20;
    ImageView imageView21;
    ImageView imageView22;

    boolean yellowTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winnerText = (TextView) findViewById(R.id.textViewWinner);
        playAgain = (Button) findViewById(R.id.buttonPlayAgain);

        imageView00 = (ImageView) findViewById(R.id.imageViewGrid00);
//        imageView01 = (ImageView) findViewById(R.id.imageViewGrid01);
//        imageView02 = (ImageView) findViewById(R.id.imageViewGrid02);
//        imageView10 = (ImageView) findViewById(R.id.imageViewGrid10);
//        imageView11 = (ImageView) findViewById(R.id.imageViewGrid11);
//        imageView12 = (ImageView) findViewById(R.id.imageViewGrid12);
//        imageView20 = (ImageView) findViewById(R.id.imageViewGrid20);
//        imageView21 = (ImageView) findViewById(R.id.imageViewGrid21);
//        imageView22 = (ImageView) findViewById(R.id.imageViewGrid22);
//
//        imageView00.setY(-1000);
//        imageView01.setY(-1500);
//        imageView02.setY(-2000);
//        imageView10.setY(-1000);
//        imageView11.setY(-1500);
//        imageView12.setY(-2000);
//        imageView20.setY(-1000);
//        imageView21.setY(-1500);
//        imageView22.setY(-2000);

        yellowTurn = true;

    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {

        gridLayout.


    }

    private void play(int id, int  translateY) {
        ImageView imageView = (ImageView) findViewById(id);
        if (yellowTurn) {
            yellowTurn = false;
            imageView.setImageResource(R.drawable.yellow);
        } else {
            yellowTurn = true;
            imageView.setImageResource(R.drawable.red);
        }
        imageView.animate().translationYBy(translateY).setDuration(2000);

    }

    private void checkIfWon() {

    }



}
