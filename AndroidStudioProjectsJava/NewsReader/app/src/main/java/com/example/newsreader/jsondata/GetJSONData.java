package com.example.newsreader.jsondata;

import com.example.newsreader.database.DataBase;
import com.example.newsreader.misc.AppContext;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetJSONData {
    protected static DownloadTask downloadTask;

    private static String geTopStoriesIDs() {
        downloadTask = new DownloadTask();
        String result = null;
        String url = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";

        try {
            result = downloadTask.execute(url).get();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String geTopStoryJSON(int id) {
        downloadTask = new DownloadTask();
        String result = null;
        String url = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json?print=pretty";

        try {
            result = downloadTask.execute(url).get();
            //System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @org.jetbrains.annotations.NotNull
    private static int[] getTopFiveIDs() {
        int[] topFive = new int[5];
        try {
            JSONArray jsonArray = new JSONArray(geTopStoriesIDs());

            for (int i = 0; i <= jsonArray.length() - 1; i++) {
                if (i == 5) {
                    break;
                } else {
                    topFive[i] = jsonArray.getInt(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topFive;
    }

    public static Map<String, String> getNameAndUrl() {
        final int[] topFive = getTopFiveIDs();
        final Map<String, String> webPages = new HashMap<>();

        try {
            for (int i = 0; i <= topFive.length - 1; i++) {
                String result = geTopStoryJSON(topFive[i]);
                JSONObject jsonObject = new JSONObject(result);

                String title = jsonObject.getString("title");
                String url = jsonObject.getString("url");
                webPages.put(title, url);
                //System.out.println(title + ", " + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webPages;
    }


}
