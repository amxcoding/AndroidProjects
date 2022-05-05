package com.example.newsreader.misc;

import android.widget.Toast;

public class ToastMessage {

    public static void toastMessage(String message) {
        Toast.makeText(AppContext.getContext(), message, Toast.LENGTH_SHORT).show();
        System.out.println(message);
    }
}
