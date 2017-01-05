package com.dev.prateekk.timeline;

import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineHandler {


    public TimelineHandler() {

    }

    public void onShowDropDown(View view) {
        Toast.makeText(TimelineApp.getApp(), "Drop Down Click", Toast.LENGTH_LONG).show();
    }

    public void onEndTrip(View view) {

    }

    public void onCancelTrip(View view) {
        Toast.makeText(TimelineApp.getApp(), "Cancel Click", Toast.LENGTH_LONG).show();
    }
}