package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ComputationActivity extends AppCompatActivity {

    private Button calculate;
    private EditText poundText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.computation);

        calculate = (Button) findViewById(R.id.calculateButton);
        poundText = (EditText) findViewById(R.id.editTextNumber);
        resultText = (TextView) findViewById(R.id.textView);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Info", "Calculate pressed");
                compute();

            }
        });
    }


    public void compute() {
        try {
            boolean computeEuro = MainActivity.euroStatus;
            boolean computeDollar = MainActivity.dollarStatus;

            double pound = Double.parseDouble(poundText.getText().toString());
            double dollar = roundOff(1.39 * pound);
            double euro = roundOff(1.17 * pound);

            String message = null;

            if (computeDollar && computeEuro) {
                message = "Dollar: " + dollar + "/ Euro: " + euro;
            } else if (computeDollar) {
                message = "Dollar: " + dollar;
            } else if (computeEuro){
                message = "Euro: " + euro;
            }
            resultText.setText(message);
        } catch (Exception e) {
            Toast.makeText(this, "No input", Toast.LENGTH_SHORT).show();
        }
    }

    private double roundOff(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}