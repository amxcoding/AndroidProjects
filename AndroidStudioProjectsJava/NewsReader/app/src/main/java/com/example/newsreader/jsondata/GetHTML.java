package com.example.newsreader.jsondata;

import com.example.newsreader.MainActivity;
import com.example.newsreader.misc.ToastMessage;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public final class GetHTML extends GetJSONData {

    public static String[] getHtml() {
        int index = 0;
        String[] results = new String[5];
        Map<String, String> webPages = getNameAndUrl();
        String htmlResult = null;

        try {
            for (String titleKey : webPages.keySet()) {
                downloadTask = new DownloadTask();
                String urlString = webPages.get(titleKey);
                htmlResult = downloadTask.execute(urlString).get();
                results[index] = htmlResult;
                //System.out.println(htmlResult);


                // add to data base
                boolean insertData = MainActivity.getDataBase().addData(titleKey, urlString, htmlResult);
//
//                if (insertData) {
//                    ToastMessage.toastMessage("Data saved");
//                    } else {
//                    ToastMessage.toastMessage("Error, data not saved");
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}
