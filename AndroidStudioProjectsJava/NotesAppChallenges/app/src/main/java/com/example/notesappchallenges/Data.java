package com.example.notesappchallenges;

import android.content.Context;
import android.content.SharedPreferences;

public class Data {

    private static final SharedPreferences sharedPreferences = AppContext.getContext().getSharedPreferences("com.example.notesappchallenges", Context.MODE_PRIVATE);

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

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

    public static void removeData(String dataKey) {
        try {
            sharedPreferences.edit().remove(dataKey).commit();
            System.out.println(getData(dataKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
