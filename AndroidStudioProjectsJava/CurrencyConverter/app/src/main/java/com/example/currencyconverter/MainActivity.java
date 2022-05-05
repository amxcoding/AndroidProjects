package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox convertToDollar;
    private CheckBox convertToEuro;
    private Button nextButton;

    static boolean dollarStatus;
    static boolean euroStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convertToEuro = (CheckBox) findViewById(R.id.checkBoxEuro);
        convertToDollar = (CheckBox) findViewById(R.id.checkBoxDollar);
        nextButton = (Button) findViewById(R.id.calculateButton);


        /**
        Eerst werkte het onderste wel en nu opeens niet meer?!
         */
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("Info", "Next pressed.");
//
//                if (convertToDollar.isChecked() && convertToEuro.isChecked()) {
//                    dollarStatus = true;
//                    euroStatus = true;
//                    openConverter();
//                } else if (convertToDollar.isChecked() && !convertToEuro.isChecked()) {
//                    dollarStatus = true;
//                    euroStatus = false;
//                    openConverter();
//                } else if (!convertToDollar.isChecked() && convertToEuro.isChecked()) {
//                    dollarStatus = false;
//                    euroStatus = true;
//                    openConverter();
//                } else {
//                    showMessage();
//                }
//
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        Log.i("Info", "Next pressed.");

        if (convertToDollar.isChecked() && convertToEuro.isChecked()) {
            dollarStatus = true;
            euroStatus = true;
            openConverter();
        } else if (convertToDollar.isChecked() && !convertToEuro.isChecked()) {
            dollarStatus = true;
            euroStatus = false;
            openConverter();
        } else if (!convertToDollar.isChecked() && convertToEuro.isChecked()) {
            dollarStatus = false;
            euroStatus = true;
            openConverter();
        } else {
            showMessage();
        }
    }

    private void openConverter() {
        Intent intent = new Intent(this, ComputationActivity.class);
        startActivity(intent);

    }

    private void showMessage() {
        Toast.makeText(this, "Choose option(s)", Toast.LENGTH_LONG).show();
    }
}