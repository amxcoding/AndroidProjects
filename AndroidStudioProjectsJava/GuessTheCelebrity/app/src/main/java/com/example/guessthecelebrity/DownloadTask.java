package com.example.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, Bitmap> {
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
