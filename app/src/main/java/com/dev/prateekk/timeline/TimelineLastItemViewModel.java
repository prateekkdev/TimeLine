package com.dev.prateekk.timeline;

import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 05/01/17.
 */

public class TimelineLastItemViewModel extends TimelineItemViewModel {

    public TimelineLastItemViewModel(TimelineContract.View timelineView, int itemPostion) {
        super(timelineView, itemPostion);
    }

    public boolean getEnabled() {

        // Need some mechanism to check for enable/disable of NoMoreBookings
        return true;
    }

    public void onNoMoreBooking(View view) {
        if (isSelected()) {
            Toast.makeText(TimelineApp.getApp(), "No More Bookings Click", Toast.LENGTH_LONG).show();
        } else {
            timelineView.onItemClick(this.getItemPosition());
        }

        // TODO Directly make calls from here or NOT
    }
}