package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineItemModel extends BaseObservable {

    private String bookingId;

    private String topTitle;
    private int topColor;

    private String midTitle;
    private Drawable midCircleDrawable;
    private boolean isCurrent;

    public TimelineItemModel() {
    }

    public TimelineItemModel(String topTitle, String midTitle, int color, boolean isCurrent, Drawable midCircleDrawable) {
        this.topTitle = topTitle;
        this.midTitle = midTitle;
        this.topColor = color;
        this.isCurrent = isCurrent;
        this.midCircleDrawable = midCircleDrawable;
    }

    public TimelineItemModel(String topTitle, String midTitle) {
        this.topTitle = topTitle;
        this.midTitle = midTitle;
    }

    public Drawable getMidCircleDrawable() {
        return midCircleDrawable;
    }

    public void setMidCircleDrawableint(Drawable midCircleDrawable) {
        this.midCircleDrawable = midCircleDrawable;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public int getMidImgVisibility() {
        if (!isCurrent) {
            return View.VISIBLE;
        } else {
            return View.GONE;
        }
    }

    public int getMidTextVisibility() {
        if (isCurrent) {
            return View.VISIBLE;
        } else {
            return View.GONE;
        }

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

    public int getTopColor() {
        return topColor;
    }

    public void setTopColor(int topColor) {
        this.topColor = topColor;
    }
}