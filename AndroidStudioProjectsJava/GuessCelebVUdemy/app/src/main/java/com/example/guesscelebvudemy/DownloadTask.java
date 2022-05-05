package com.example.guesscelebvudemy;

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
        HttpURLConnection urlConnection;
        Bitmap image;
        InputStream in;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = urlConnection.getInputStream();
            image = BitmapFactory.decodeStream(in);

            return image;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
