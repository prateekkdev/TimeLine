package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;

/**
 * Created by prateek.kesarwani on 12/01/17.
 */

public class TimelineItemViewModel extends BaseObservable implements TimelineContract.ViewModel {

    protected TimelineContract.View timelineView;
    private int itemPosition;
    private boolean isSelected;

    public TimelineItemViewModel(TimelineContract.View timelineView, int itemPosition) {
        this.timelineView = timelineView;
        this.itemPosition = itemPosition;
    }

    public void onItemSelected(android.view.View view) {
        timelineView.onItemSelected(itemPosition);
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
