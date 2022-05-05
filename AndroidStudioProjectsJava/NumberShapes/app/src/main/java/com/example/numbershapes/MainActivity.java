package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView resultsText;
    EditText inputNumberText;
    Button checkButton;
    TriangularNumber triangularNumber;
    SquareNumber squareNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultsText = (TextView) findViewById(R.id.textResults);
        inputNumberText = (EditText) findViewById(R.id.inputTextNumber);
        checkButton = (Button) findViewById(R.id.buttonCheck);
        triangularNumber = new TriangularNumber();
        squareNumber = new SquareNumber();
    }

    public void onClick(View v) {
        Log.i("Info", "Check pressed");

        if (inputNumberText.getText().toString().isEmpty()) {
            Toast.makeText(this, "No input.", Toast.LENGTH_SHORT).show();
        } else {

            String inputNumberString = inputNumberText.getText().toString();

            int inputNumber = Integer.parseInt(inputNumberString);

            boolean isTriangular = triangularNumber.checkIfTriangular(inputNumber);
            boolean isSquare = squareNumber.checkIfSquare(inputNumber);

            String message = "";

            if (isTriangular && isSquare) {
                message = inputNumberString + " is a square AND a triangular number";
            } else if (isSquare) {
                message = inputNumberString + " is a square number";
            } else if (isTriangular) {
                message = inputNumberString + " is a triangular number";
            } else {
                message = inputNumberString + " is NOT a square or a triangular number";
            }

            resultsText.setText(message);
        }
    }
}