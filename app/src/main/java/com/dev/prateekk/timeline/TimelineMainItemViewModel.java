package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineMainItemViewModel extends BaseObservable implements TimelineContract.ViewModel {

    private TimelineContract.View timelineView;

    private String topTitle;
    private int topColor;

    private String midTitle;

    private int width;

    private String id;

    private boolean isCurrent;

    private Drawable midImgDrawable;
    private boolean isSelected;

    public TimelineMainItemViewModel(TimelineContract.View view) {
        this.timelineView = view;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Drawable getMidImgId() {
        return midImgDrawable;
    }

    public void setMidImgId(Drawable midImgId) {
        this.midImgDrawable = midImgId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle;
    }

    public String getMidTitle() {
        return midTitle;
    }

    public void setMidTitle(String midTitle) {
        this.midTitle = midTitle;
    }

    public void onClickInfo(View view) {
        Toast.makeText(view.getContext(), "View Clicked", Toast.LENGTH_SHORT).show();
    }

    public int getTopColor() {
        return topColor;
    }

    public void setTopColor(int topColor) {
        this.topColor = topColor;
    }

    public boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public void onShowDropDown(View view, String bookingId) {
        Toast.makeText(TimelineApp.getApp(), "Drop Down Click - Id: " + bookingId, Toast.LENGTH_LONG).show();

        timelineView.onShowDropDown(bookingId);
    }



    /*
    @Override
    public boolean equals(Object obj) {
        if (this.id.equalsIgnoreCase(((TimelineMainItemViewModel) obj).id)) {
            return true;
        }
        return false;
    }
    */
}