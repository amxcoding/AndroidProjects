package com.example.alertdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;


public class Alert {


    public static void alert() {

        new AlertDialog.Builder(AppContext.getContext())
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Choose a language")
                .setMessage("What language do you like to use?")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveAndSetLanguage("English");

                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveAndSetLanguage("Spanish");

                    }
                })
                .show();
    }

    private static void saveAndSetLanguage(String language) {
        SavePreferences.saveData("language", language);
        MainActivity.getTextView().setText(language);
    }
}
