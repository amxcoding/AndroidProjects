package com.example.newsreader.misc;

import android.content.Context;

public final class AppContext {

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
