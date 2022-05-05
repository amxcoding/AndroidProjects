package com.example.downloadjsondata;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
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
        InputStreamReader reader;

        try {
            stringBuilder = new StringBuilder();
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
            reader = new InputStreamReader(inputStream);
            int data = reader.read();

            while (data != -1) {
                char current = (char) data;
                stringBuilder.append(current);

                data = reader.read();
            }

            result = stringBuilder.toString();

            return result;

        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

//        getWeather(s);
//        getTemp(s);

        //getTopStoriesIDs(s);
        //getNameAndUrl(s);


    }


    private void getWeather(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            String weatherInfo = jsonObject.getString("weather");
            System.out.println(weatherInfo);

            JSONArray jsonArray = new JSONArray(weatherInfo);

            for (int i = 0; i <= jsonArray.length() - 1; i++) {
                JSONObject jsonPart = jsonArray.getJSONObject(i);

                Log.i("main", jsonPart.getString("main"));
                Log.i("description", jsonPart.getString("description"));
//                System.out.println(jsonPart.getString("main"));
//                System.out.println(jsonPart.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTemp(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            String temperature = "[" + jsonObject.getString("main") + "]";
            System.out.println(temperature);

            JSONArray jsonArray = new JSONArray(temperature);

            for (int i = 0; i <= jsonArray.length() - 1; i++) {
                JSONObject jsonPart = jsonArray.getJSONObject(i);

                Log.i("temp", jsonPart.getString("temp"));
                //Log.i("description", jsonPart.getString("description"));
//                System.out.println(jsonPart.getString("main"));
//                System.out.println(jsonPart.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTopStoriesIDs(String s) {
        try {
            JSONArray jsonArray = new JSONArray(s);

            for (int i = 0; i <= jsonArray.length() - 1; i++) {


                System.out.println(jsonArray.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getNameAndUrl(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            String title = jsonObject.getString("title");
            String url = jsonObject.getString("url");
            System.out.println(title + ", " + url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
