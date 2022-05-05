package com.example.notesappchallenges;

import android.content.Context;

public class AppContext {

    private static Context mContext;

    private AppContext() {
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        AppContext.mContext = mContext;
    }
}
