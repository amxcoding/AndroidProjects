package com.example.textfields;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ChangeImage;
    Button ChangeButton;

    public void clickFunction(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Log.i("Info", "Log in pressed");
        Log.i("name", nameEditText.getText().toString());
        Log.i("password", passwordEditText.getText().toString());

        // this--> where the app is displayed, the app
        String text = String.format("Hi %s", nameEditText.getText().toString());
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

        ChangeButton = (Button) findViewById(R.id.button);
        ChangeImage = (ImageView) findViewById(R.id.imageView3);

        ChangeImage.setImageResource(R.drawable.pic1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ChangeButton = (Button) findViewById(R.id.button);
//        ChangeImage = (ImageView) findViewById(R.id.imageView3);
//        ChangeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChangeImage.setImageResource(R.drawable.pic2);
//            }
//        });
    }
}
