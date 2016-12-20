package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineItemModel extends BaseObservable {

    private String topTitle;
    private int topColor;

    private String midTitle;

    private int width;

    private SDBookingData bookingData;

    public TimelineItemModel() {
    }

    public TimelineItemModel(SDBookingData bookingData) {
        this.bookingData = bookingData;

        // This name, get from utility which validates and returns proper name to display.
        String name = bookingData.getBookingResponse().getCustomer_info().name;

        this.topTitle = bookingData.isBookingCurrent() ? bookingData.getStatus().equalsIgnoreCase("accepted") ? "PICKUP"
                : bookingData.getStatus().equalsIgnoreCase("driver_reached") ? "WAIT FOR"
                : bookingData.getStatus().equalsIgnoreCase("invoice") ? "BILLING FOR"
                : bookingData.getStatus().equalsIgnoreCase("payment") ? "DROP"
                : ""
                : name;


        this.midTitle = name;

        this.topColor = bookingData.isBookingCurrent ? bookingData.getStatus().equalsIgnoreCase("accepted") ? TimelineApp.getApp().getResources().getColor(R.color.green)
                : bookingData.getStatus().equalsIgnoreCase("payment") ? TimelineApp.getApp().getResources().getColor(R.color.red)
                : TimelineApp.getApp().getResources().getColor(R.color.grey)
                : TimelineApp.getApp().getResources().getColor(R.color.grey);

        if (this.bookingData.isBookingCurrent()) {
            width = dpToPx(300);
        } else {
            width = dpToPx(200);
        }
    }

    public SDBookingData getBookingData() {
        return bookingData;
    }

    public void setBookingData(SDBookingData bookingData) {
        this.bookingData = bookingData;
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

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = TimelineApp.getApp().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}