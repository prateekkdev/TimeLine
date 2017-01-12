package com.dev.prateekk.timeline;

import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineHandler {


    public TimelineHandler() {

    }

    public void onEndTrip(View view, String bookingId) {
        Toast.makeText(TimelineApp.getApp(), "End Trip Click - Id: " + bookingId, Toast.LENGTH_LONG).show();

        // Directly make calls from here
    }

    public void onCancelTrip(View view, String bookingId) {
        Toast.makeText(TimelineApp.getApp(), "Cancel Click - Id: " + bookingId, Toast.LENGTH_LONG).show();

        // Directly make calls from here
    }

    public void onNoMoreBooking(View view) {
        Toast.makeText(TimelineApp.getApp(), "No More Bookings Click", Toast.LENGTH_LONG).show();

        // Directly make calls from here
    }
}