package com.dev.prateekk.timeline;

import android.app.Application;
import android.content.Context;

/**
 * Created by prateek.kesarwani on 19/12/16.
 */

public class TimelineApp extends Application {

    private static Context context;

    public static Context getApp() {
        return context;
    }

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
    }
}
