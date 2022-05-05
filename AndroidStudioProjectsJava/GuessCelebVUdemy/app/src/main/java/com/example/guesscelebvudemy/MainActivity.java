package com.example.guesscelebvudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    GetData getData;
    DownloadTask downloadTask;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        getData = new GetData();

        setLayout();

    }

    private void setLayout() {
        downloadTask = new DownloadTask();
        Bitmap bitmap = null;
        String url = getData.getImageUrls().get(0);

        try {
            bitmap = downloadTask.execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(bitmap);

    }

}