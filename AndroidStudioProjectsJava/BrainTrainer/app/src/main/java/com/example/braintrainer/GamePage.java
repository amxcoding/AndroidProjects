package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GamePage extends AppCompatActivity {

    CountDownTimer countDownTimerI;
    CountDownTimer countDownTimerII;
    Questions questions;
    Random random;
    TextView textViewPressed;
    Button playAgain;

    TextView textViewTimer;
    TextView textViewCounter;
    TextView textViewCheck;
    TextView textViewCheck2;
    TextView textViewQuestion;

    TextView textViewAnswer1;
    TextView textViewAnswer2;
    TextView textViewAnswer3;
    TextView textViewAnswer4;

    private static final int TIME_PER_QUESTION = 25; //seconds
    private static final int MAX_QUESTIONS = 10;
    private int difficulty;
    private int result;
    private int[] solutions;
    private int counter;
    private int questionAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        textViewTimer = findViewById(R.id.textViewTimer);
        textViewCounter = findViewById(R.id.textViewCounter);
        textViewCheck = findViewById(R.id.textViewCheck);
        textViewCheck2 = findViewById(R.id.textViewCheck2);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewAnswer1 = findViewById(R.id.textViewAnswer1);
        textViewAnswer2 = findViewById(R.id.textViewAnswer2);
        textViewAnswer3 = findViewById(R.id.textViewAnswer3);
        textViewAnswer4 = findViewById(R.id.textViewAnswer4);
        playAgain = findViewById(R.id.buttonPlayAgain);

        questions = new Questions();
        solutions = new int[4];
        random = new Random();

        initialize();
        runGame();

    }



    private void initialize() {
        counter = 0;
        questionAmount = 0;
        difficulty = MainActivity.getDifficulty();
    }

    private void runGame() {

        if (!done()) {
            updateCounter();
            updateQuestion();
            updateSolutionTexts();
            runTimer(false);
        }
    }

    private void checkSolution(boolean correct) {
        String messageCheck;


        if (correct) {
            messageCheck = "Correct :)";
            textViewCheck.setTextColor(Color.GREEN);
            counter++;
        } else {
            messageCheck = "Wrong :(";
            textViewCheck.setTextColor(Color.RED);
        }

        questionAmount++;
        updateCounter();

        countDownTimerII = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewCheck.setVisibility(View.VISIBLE);
                textViewCheck.setText(messageCheck);

            }

            @Override
            public void onFinish() {
                textViewCheck.setVisibility(View.INVISIBLE);

            }
        };

        countDownTimerII.start();
        runTimer(true);
        runGame();

    }

    private boolean done() {
        String message;

        message = "Done!";

        if (questionAmount == MAX_QUESTIONS) {
            countDownTimerII = new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    textViewCheck2.setVisibility(View.VISIBLE);
                    textViewCheck2.setText(message);

                }
            }.start();

            playAgain.setVisibility(View.VISIBLE);
            textViewAnswer1.setEnabled(false);
            textViewAnswer2.setEnabled(false);
            textViewAnswer3.setEnabled(false);
            textViewAnswer4.setEnabled(false);


            return true;
        }

        return false;
    }

    public void playAgain(View view) {
//        countDownTimerII.cancel();
//        initialize();
//        runGame();
//        textViewCheck2.setVisibility(View.INVISIBLE);
//        textViewCheck.setVisibility(View.INVISIBLE);
//        playAgain.setVisibility(View.INVISIBLE);
//        textViewAnswer1.setEnabled(true);
//        textViewAnswer2.setEnabled(true);
//        textViewAnswer3.setEnabled(true);
//        textViewAnswer4.setEnabled(true);

        finish();
    }

    @SuppressLint("DefaultLocale")
    public void onClick(View view) {
        String resultText;

        textViewPressed = (TextView) view;
        resultText = String.valueOf(textViewPressed.getText());

        if (resultText.equals(String.valueOf(result))) {
            checkSolution(true);
        } else {
            checkSolution(false);

        }

    }

    private void updateQuestion() {
        String question;
        questions.question(difficulty);
        question = questions.getReturnTwoValues().getValueTwo();
        result = questions.getReturnTwoValues().getValueOne();
        Log.i("Result is", String.valueOf(result));
        Log.i("Question is", question);

        textViewQuestion.setText(question);
    }

    private void updateSolutionTexts() {
        updateSolutions();

        textViewAnswer1.setText(String.valueOf(solutions[0]));
        textViewAnswer2.setText(String.valueOf(solutions[1]));
        textViewAnswer3.setText(String.valueOf(solutions[2]));
        textViewAnswer4.setText(String.valueOf(solutions[3]));
    }

    private void updateSolutions() {
        int realSolutionOnNr = random.nextInt(4);
        int randomSolution;

        for (int i = 0; i <= solutions.length - 1; i++) {
            if (realSolutionOnNr == i) {
                solutions[i] = result;
            } else {
                randomSolution = randomSolutions();
                while (randomSolution == result) {
                    randomSolution = randomSolutions();
                }
                solutions[i] = randomSolution;
            }
            checkDub(solutions, solutions[i], i);
        }
    }

    private int randomSolutions() {
        int randomBound, randomBoundMax, randomBoundMin, randomSolution;

        if (result < 0) {
            randomBound = -result;
            randomBoundMax = randomBound + 5;
            randomBoundMin = randomBound - 5;
            randomSolution = - (random.nextInt(randomBoundMax - randomBoundMin + 1) + randomBoundMin);
        } else {
            randomBound = result;
            randomBoundMax = randomBound + 5;
            randomBoundMin = randomBound - 5;
            randomSolution = random.nextInt(randomBoundMax - randomBoundMin + 1) + randomBoundMin;
        }

        return randomSolution;
    }

    private int[] checkDub(int[] array, int checkFor, int excludeIndex) {
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i] == checkFor && i != excludeIndex) {
                array[i] = randomSolutions();
            }
        }

        return array;
    }

    @SuppressLint("DefaultLocale")
    private void updateCounter() {
        String messageCounter;
        messageCounter = String.format("%02d/%02d", counter, questionAmount);

        textViewCounter.setText(messageCounter);
    }

    private void runTimer(boolean cancel) {
        if (!cancel) {
            countDownTimerI = new CountDownTimer(TIME_PER_QUESTION * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    questionAmount++;
                    runGame();
                }
            };
            countDownTimerI.start();
        } else {
            countDownTimerI.cancel();
        }
    }

    @SuppressLint("DefaultLocale")
    private void updateTimer(int timeInSeconds) {
        String message;

        message = String.format("%02ds", timeInSeconds);
        textViewTimer.setText(message);
    }


}