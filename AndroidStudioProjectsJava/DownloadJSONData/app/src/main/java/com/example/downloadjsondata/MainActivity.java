package com.example.downloadjsondata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DownloadTask downloadTask;
    private GetJSONData getJSONData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getJSONData = new GetJSONData();

        getJSONData = new GetJSONData();
        Map<String, String> webPages = getJSONData.getNameAndUrl();
        for (String title : webPages.keySet()) {
            String url = webPages.get(title);
            System.out.println(title + ", " + url);
        }


    }

    private void getData() {
        downloadTask = new DownloadTask();

        String result = null;
        String url = "https://api.openweathermap.org/data/2.5/weather?q=tokyo&appid=472b068ec8d4b42703b6fc25fc0e2bca";
        url = "https://hacker-news.firebaseio.com/v0/item/27842933.json?print=pretty";

        try {

            result = downloadTask.execute(url).get();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Log.i("Result", result);
    }
}