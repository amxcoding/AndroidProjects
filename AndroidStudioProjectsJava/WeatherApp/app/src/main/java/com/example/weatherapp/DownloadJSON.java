package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadJSON extends AsyncTask<String, Void, String> {

    private String message;

    public DownloadJSON() {
        this.message = "";
    }

    @Override
    protected String doInBackground(String... strings) {
        String result;
        StringBuilder stringBuilder;
        int data;

        URL url;
        HttpURLConnection urlConnection;
        InputStream inputStream;
        BufferedReader reader;

        LocationManager locationManager;

        try {
            stringBuilder = new StringBuilder();
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            data = reader.read();

            while (data != -1) {
                char current = (char) data;
                stringBuilder.append(current);

                data = reader.read();
            }

            result = stringBuilder.toString();
            System.out.println(result);
            return result;

        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        getWeather(s);
        getTemperature(s);
        updateTextView();
    }


    @SuppressLint("SetTextI18n")
    private void getWeather(String s) {

        try {
            JSONObject jsonObject = new JSONObject(s);

            String weatherInfo = jsonObject.getString("weather");
            System.out.println(weatherInfo);

            JSONArray jsonArray = new JSONArray(weatherInfo);

            for (int i = 0; i <= jsonArray.length() - 1; i++) {
                JSONObject jsonPart = jsonArray.getJSONObject(i);

                String main = jsonPart.getString("main");
                String description = jsonPart.getString("description");
                Log.i("main", main);
                Log.i("description", description);

                if (!main.equals("") && !description.equals("")) {
                    message += main + ", " + description + " \n";
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getTemperature(String s) {

        try {
            JSONObject jsonObject = new JSONObject(s);

            String temperatureMain = "[" + jsonObject.getString("main") + "]";
            System.out.println(temperatureMain);

            JSONArray jsonArray = new JSONArray(temperatureMain);

            for (int i = 0; i <= jsonArray.length() - 1; i++) {
                JSONObject jsonPart = jsonArray.getJSONObject(i);

                String temperature = convertToCelcius(jsonPart.getString("temp"));
                Log.i("temp", temperature);

                if (!temperature.equals("")) {
                    message += temperature + " C ";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("DefaultLocale")
    private String convertToCelcius(String temperatureInK) {

        double tempInK = Double.parseDouble(temperatureInK);
        double tempInC = tempInK - 273.15;
        tempInC = roundOf(tempInC);

        return String.format("%.1f", tempInC);
    }

    private double roundOf(double in) {
        double out = (in * 100.0) / 100.0;

        return out;
    }

    public void updateTextView() {

        if (message.equals("")) {
            Toast.makeText(MainActivity.MainContextActivity.getActivity(), "Can't find city", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.textView.setText(message);
        }
    }
}
