package com.example.alertdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class SavePreferences extends MainActivity {

    private static final SharedPreferences sharedPreferences = AppContext.getContext().getSharedPreferences("com.example.alertdemo", Context.MODE_PRIVATE);

    public static void saveData(String dataKey, String dataValue) {
        try {
            sharedPreferences.edit().putString(dataKey, dataValue).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getData(String dataKey) {
        try {
            return sharedPreferences.getString(dataKey, "Error");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void clearData() {
        sharedPreferences.edit().clear().apply();
    }

}
