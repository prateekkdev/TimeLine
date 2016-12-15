package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineItemModel extends BaseObservable {

    private String bookingId;

    private String topTitle;
    private Color topColor;

    private String midTitle;
    private Drawable midCircle;

    public TimelineItemModel() {
    }

    public TimelineItemModel(String topTitle, String midTitle, Color color) {
        this.topTitle = topTitle;
        this.midTitle = midTitle;
        // this.topColor = color;
    }


    public TimelineItemModel(String topTitle, String midTitle) {
        this.topTitle = topTitle;
        this.midTitle = midTitle;
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

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void onClickInfo(View view) {
        Toast.makeText(view.getContext(), "View Clicked", Toast.LENGTH_SHORT).show();
    }

    public Color getTopColor() {
        return topColor;
    }

    public void setTopColor(Color topColor) {
        this.topColor = topColor;
    }

    public Drawable getMidCircle() {
        return midCircle;
    }

    public void setMidCircle(Drawable midCircle) {
        this.midCircle = midCircle;
    }
}