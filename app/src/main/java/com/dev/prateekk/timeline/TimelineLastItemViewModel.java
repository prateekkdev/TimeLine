package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 05/01/17.
 */

public class TimelineLastItemViewModel extends BaseObservable {

    public boolean getEnabled() {

        // Need some mechanism to check for enable/disable of NoMoreBookings
        return true;
    }

    public void onNoMoreBooking(View view) {
        Toast.makeText(TimelineApp.getApp(), "No More Bookings Click", Toast.LENGTH_LONG).show();

        // Directly make calls from here
    }
}