package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button0, button1, button2, button3;
    DownloadTask downloadTask;
    GetData getData;
    TextView textView;

    private List<String> chosenOnes;
    private String[] nameOptions;
    private Random random;
    private int indexNameChosen;
    private int counterTotal;
    private int counterCorrect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        chosenOnes = new LinkedList<>();
        getData = new GetData();
        random = new Random();
        nameOptions = new String[4];

        counterTotal = 0;
        counterCorrect = 0;

        updateCounter();
        play();
    }


    private void play() {
        getNames();
        setLayout();
    }

    public void restart(View view) {
        counterTotal = 0;
        counterCorrect = 0;
        chosenOnes = new LinkedList<>();

        updateCounter();
        play();
    }


    private boolean checkIfAlreadyChosen(String name) {
        for (int i = 0; i <= chosenOnes.size() - 1; i++) {
            if (chosenOnes.get(i).equals(name)) {
                return true;
            }

        }
        return false;
    }

    private void getNames() {
        LinkedList<String> names = new LinkedList<>(getData.getNames());
        int randomPositionCorrectName = random.nextInt(4);
        indexNameChosen = chooseRandomName(getData.getNames());
        int indexRandomName;

        while (checkIfAlreadyChosen(getData.getNames().get(indexNameChosen))) {
            indexNameChosen = chooseRandomName(getData.getNames());
        }

        for (int i = 0; i <= nameOptions.length - 1; i++) {
            if (i == randomPositionCorrectName) {
                nameOptions[i] = getData.getNames().get(indexNameChosen);
                chosenOnes.add(getData.getNames().get(indexNameChosen));

                try {
                    names.remove(indexNameChosen);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                indexRandomName = chooseRandomName(names);
                nameOptions[i] = names.get(indexRandomName);

                try {
                    names.remove(indexRandomName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(names.size() + "|" + getData.getNames().size());

    }


    private ReturnTwoObjects<Boolean, String> checkIfCorrect(String name) {
        boolean status;
        String nameCorrect;

        nameCorrect = getData.getNames().get(indexNameChosen);

        if (name.equals(nameCorrect)) {
            status = true;
        } else {
            status = false;
        }
        return new ReturnTwoObjects<>(status, nameCorrect);
    }


    private int chooseRandomName(List<String> list) {
        return random.nextInt(list.size());
    }


    private void setLayout() {
        downloadTask = new DownloadTask();
        Bitmap image;
        String url = getData.getImageUrls().get(indexNameChosen);

        try {
            image = downloadTask.execute(url).get();

            imageView.setImageBitmap(image);

        } catch (Exception e) {
            e.printStackTrace();
        }

        button0.setText(nameOptions[0]);
        button1.setText(nameOptions[1]);
        button2.setText(nameOptions[2]);
        button3.setText(nameOptions[3]);

    }


    private void updateCounter() {
        String message = String.format("%02d/%02d", counterCorrect, counterTotal);

        textView.setText(message);
    }


    public void onClick(View view) {
        Button buttonPressed;

        buttonPressed = (Button) view;
        String nameOnButton = buttonPressed.getText().toString();

        if (checkIfCorrect(nameOnButton).getObject1()) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            counterCorrect++;
            play();
        } else {
            String nameIs = checkIfCorrect(nameOnButton).getObject2();
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }

        counterTotal++;
        updateCounter();

    }
}