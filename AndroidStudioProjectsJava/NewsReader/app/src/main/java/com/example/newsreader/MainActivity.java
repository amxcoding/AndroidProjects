package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.newsreader.customadapter.CustomAdapter;
import com.example.newsreader.database.DataBase;
import com.example.newsreader.jsondata.GetHTML;
import com.example.newsreader.jsondata.GetJSONData;
import com.example.newsreader.misc.AppContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private static List<WebPages> webPagesList;
    private CustomAdapter customAdapter;
    private Intent intent;
    private static DataBase dataBase;

    public static List<WebPages> getWebPagesList() {
        return webPagesList;
    }

    public static DataBase getDataBase() {
        return dataBase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set context
        AppContext.setContext(this);

        listView = findViewById(R.id.listView);
        intent = new Intent(getApplicationContext(), DisplayWebPage.class);


         // Set list, listView and adapter
        webPagesList = new ArrayList<>();
        customAdapter = new CustomAdapter(getApplicationContext(), webPagesList);
        listView.setAdapter(customAdapter);

        // initiate database
        dataBase = new DataBase(this);


         // Get site title and url
         // And add them to webPagesList

//        Map<String, String> webPages = GetJSONData.getNameAndUrl();
//        String[] htmlString = GetHTML.getHtml();
//        addToList(webPages, htmlString);

        getData();
        listViewOnClick();
    }

    private void getData() {
        GetHTML.getHtml();
        Cursor cursor = dataBase.getData();

        int titleIndex = cursor.getColumnIndex(DataBase.getCOL1());
        int urlIndex = cursor.getColumnIndex(DataBase.getCOL2());
        int htmlIndex = cursor.getColumnIndex(DataBase.getCOL3());
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            webPagesList.add(new WebPages(cursor.getString(titleIndex), cursor.getString(urlIndex), cursor.getString(htmlIndex)));
        }

        customAdapter.notifyDataSetChanged();
    }


//    private void addToList(Map<String, String> webPages, String[] htmlString) {
//        int index = 0;
//        for (String title : webPages.keySet()) {
//            String url = webPages.get(title);
//            String html = htmlString[index];
//            webPagesList.add(new WebPages(title, url, html));
//        }
//
//        customAdapter.notifyDataSetChanged();
//    }

    private void listViewOnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }




}