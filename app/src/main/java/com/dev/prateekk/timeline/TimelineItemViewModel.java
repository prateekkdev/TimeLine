package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;

/**
 * Created by prateek.kesarwani on 12/01/17.
 */

public class TimelineItemViewModel extends BaseObservable implements TimelineContract.ViewModel {

    protected TimelineContract.View timelineView;
    private int itemPosition;

    public TimelineItemViewModel(TimelineContract.View timelineView, int itemPosition) {
        this.timelineView = timelineView;
        this.itemPosition = itemPosition;
    }

    public void onItemClick(android.view.View view, int itemPosition) {
        // Toast.makeText(TimelineApp.getApp(), "Scrolled - Id: " + bookingId, Toast.LENGTH_LONG).show();
        timelineView.onItemClick(itemPosition);
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

}
