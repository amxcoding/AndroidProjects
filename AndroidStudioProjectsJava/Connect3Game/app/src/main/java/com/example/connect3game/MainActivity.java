package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView winnerText;
    Button playAgain;
    GridLayout gridLayout;

    int activePlayer;
    boolean gameActive;
    int[] gameState;

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        winnerText = (TextView) findViewById(R.id.textViewWinner);
        playAgain = (Button) findViewById(R.id.buttonPlayAgain);

        activePlayer = 1;
        gameActive = true;
        gameState = new int[9];

    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int getTag = Integer.parseInt(counter.getTag().toString());

        if (gameActive) {
            if (gameState[getTag] == 0) {
                counter.setTranslationY(-1500);
                gameState[getTag] = activePlayer;
                if (activePlayer == 1) {
                    activePlayer = 2;
                    counter.setImageResource(R.drawable.yellow);
                } else {
                    activePlayer = 1;
                    counter.setImageResource(R.drawable.red);
                }
                counter.animate().translationYBy(1500).setDuration(300);
                int winner = checkIfWon();
                setWinnerText(winner);
            } else {
                Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show();
        }
    }

    private int checkIfWon() {

        for (int[] winningPositions : winningPositions) {
            if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                    gameState[winningPositions[0]] != 0) {
                return gameState[winningPositions[0]];
            }
        }
        return -1;
    }

    private void setWinnerText(int winner) {
        String player;
        if (winner != -1) {
            winnerText.setVisibility(View.VISIBLE);
            playAgain.setVisibility(View.VISIBLE);

            if (winner == 1) {
                player = "yellow";
            } else {
                player = "red";
            }

            String message = "Player " + player + " won the game!";
            winnerText.setText(message);
            gameActive = false;
        }
    }

    public void playAgain(View view) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        gameState = new int[9];
        activePlayer = 1;
        gameActive = true;
        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
    }

}