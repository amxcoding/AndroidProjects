package com.example.catswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int pictureNr = 2;

    public void clickFunction(View view) {
        Button clickMe = (Button) findViewById(R.id.button2);
        ImageView imageToChange = (ImageView) findViewById(R.id.imageView);
        TextView logName = (TextView) findViewById(R.id.textName);

        Log.i("name", logName.getText().toString());
        Toast.makeText(this, "Hi " + logName.getText().toString(),
                Toast.LENGTH_LONG).show();

        switch (pictureNr) {
            case 1:
                imageToChange.setImageResource(R.drawable.cat1);
                pictureNr = 2;
                break;
            case 2:
                imageToChange.setImageResource(R.drawable.cat2);
                pictureNr = 3;
                break;
            case 3:
                imageToChange.setImageResource(R.drawable.cat3);
                pictureNr = 1;
                break;
            default:
                Toast.makeText(this, "Miauw", Toast.LENGTH_SHORT).show();
                break;
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}