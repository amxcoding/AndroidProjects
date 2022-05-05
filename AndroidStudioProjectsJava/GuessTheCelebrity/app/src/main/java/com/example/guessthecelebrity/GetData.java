package com.example.guessthecelebrity;

import android.os.AsyncTask;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetData extends AsyncTask<String, Void, String> {
    private List<String> names;
    private List<String> imageUrls;
    private String result;

    public GetData() {
        names = new ArrayList<>();
        imageUrls = new ArrayList<>();

        getData();
        splitString();
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result;
        StringBuilder stringBuilder;
        URL url;
        HttpURLConnection urlConnection;

        try {
            stringBuilder = new StringBuilder();
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
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

    private void getData() {

        try {
            result = execute("https://www.imdb.com/list/ls052283250/").get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void splitString() {
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile("src=\"(.*?).jpg\"");
        matcher = pattern.matcher(result);

        while (matcher.find()) {
            imageUrls.add(matcher.group(1) + ".jpg");
        }

        pattern = Pattern.compile("<img alt=\"(.*?)\"");
        matcher = pattern.matcher(result);

        while (matcher.find()) {
            names.add(matcher.group(1));
        }
    }



}
