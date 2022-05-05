package com.example.alertdemo;

import android.app.Application;
import android.content.Context;

public class AppContext extends Application {

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
