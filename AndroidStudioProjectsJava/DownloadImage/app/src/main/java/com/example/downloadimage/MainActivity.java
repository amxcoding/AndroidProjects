package com.example.downloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button2);
        downloadTask = new DownloadTask();


    }

    public void download(View view) {
        Bitmap image;

        try {
            image = downloadTask.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();

            imageView.setImageBitmap(image);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {

            URL url;
            HttpURLConnection connection;
            InputStream in;
            Bitmap bitmap;


            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                in = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
                return bitmap;

            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }
    }
}