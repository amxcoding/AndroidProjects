package com.example.newsreader.jsondata;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String result;
        StringBuilder stringBuilder;

        URL url;
        HttpURLConnection urlConnection;
        InputStream inputStream;
        InputStreamReader inputStreamReader;

        try {
            stringBuilder = new StringBuilder();
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStream.read();

            while (data != - 1) {
                char current = (char) data;
                stringBuilder.append(current);

                data = inputStream.read();
            }

            result = stringBuilder.toString();
            return result;
        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }
    }
}
