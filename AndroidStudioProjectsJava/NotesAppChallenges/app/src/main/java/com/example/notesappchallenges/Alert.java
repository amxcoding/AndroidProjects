package com.example.notesappchallenges;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class Alert {

    public static void alertDelete() {

        new AlertDialog.Builder(AppContext.getContext())
                .setIcon(android.R.drawable.stat_sys_warning)
                .setTitle("Delete item?")
                .setMessage("Do you want to delete the note?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.deleteNote();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .show();
    }

    public static void alertSave() {

    }
}
