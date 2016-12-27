package com.dev.prateekk.timeline;

import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by prateek.kesarwani on 27/12/16.
 */

public class TimelineAdapter {

    public static final int GREY = -1;
    public static final int GREEN = 0;
    public static final int RED = 1;

    public static void updateNotCurrent(TimelineItemViewModel itemViewModel, String name) {
        itemViewModel.setIsCurrent(false);
        itemViewModel.setTopTitle(name);
        itemViewModel.setWidth(dpToPx(200));
    }

    public static void updateCurrent(TimelineItemViewModel itemViewModel, String status) {
        itemViewModel.setIsCurrent(true);

        itemViewModel.setTopTitle(status.equalsIgnoreCase("accepted")
                ? "PICKUP" : status.equalsIgnoreCase("driver_reached")
                ? "WAIT FOR" : status.equalsIgnoreCase("invoice")
                ? "BILLING FOR" : status.equalsIgnoreCase("payment")
                ? "DROP" : "");
        itemViewModel.setWidth(dpToPx(300));

    }

    public static void updateList(List<TimelineItemViewModel> timelineItemViewModelList, HashMap<String, SDBookingData> bookingHashMap, ArrayList<BookingPriority> priorityList) {

        for (BookingPriority priority : priorityList) {
            TimelineItemViewModel itemViewModel = new TimelineItemViewModel();

            SDBookingData bookingData = bookingHashMap.get(priority.krn);

            String status = bookingData.getStatus();

            itemViewModel.setMidTitle(bookingData.getBookingResponse().getCustomer_info().name);

            if (bookingData.isBookingCurrent()) {
                if ((status.equalsIgnoreCase("accepted") && priority.type.equalsIgnoreCase("drop")) ||
                        (status.equalsIgnoreCase("payment") && priority.type.equalsIgnoreCase("pickup")) ||
                        (status.equalsIgnoreCase("invoice") && priority.type.equalsIgnoreCase("drop")) ||
                        (status.equalsIgnoreCase("driver_reached") && priority.type.equalsIgnoreCase("drop"))) {
                    // This is not current action, this item is for pickup already done or drop pending
                    updateNotCurrent(itemViewModel, bookingData.getBookingResponse().getCustomer_info().name);
                } else {
                    updateCurrent(itemViewModel, status);
                }
            } else {
                updateNotCurrent(itemViewModel, bookingData.getBookingResponse().getCustomer_info().name);
            }

            if (timelineItemViewModelList == null) {
                timelineItemViewModelList = new ArrayList<>();
            }

            if (priority.type.equalsIgnoreCase("pickup")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.green));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_green));
            } else if (priority.type.equalsIgnoreCase("drop")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.red));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_red));
            }

            timelineItemViewModelList.add(itemViewModel);
        }
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = TimelineApp.getApp().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void calc(TimelineItemViewModel itemViewModel, SDBookingData bookingData) {

        // This name, get from utility which validates and returns proper name to display.
        String name = bookingData.getBookingResponse().getCustomer_info().name;

        itemViewModel.setTopTitle(bookingData.isBookingCurrent() ? bookingData.getStatus().equalsIgnoreCase("accepted") ? "PICKUP"
                : bookingData.getStatus().equalsIgnoreCase("driver_reached") ? "WAIT FOR"
                : bookingData.getStatus().equalsIgnoreCase("invoice") ? "BILLING FOR"
                : bookingData.getStatus().equalsIgnoreCase("payment") ? "DROP"
                : ""
                : name);

        itemViewModel.setMidTitle(name);

        itemViewModel.setTopColor(bookingData.isBookingCurrent ? bookingData.getStatus().equalsIgnoreCase("accepted") ? TimelineApp.getApp().getResources().getColor(R.color.green)
                : bookingData.getStatus().equalsIgnoreCase("payment") ? TimelineApp.getApp().getResources().getColor(R.color.red)
                : TimelineApp.getApp().getResources().getColor(R.color.grey)
                : TimelineApp.getApp().getResources().getColor(R.color.grey));

        if (bookingData.isBookingCurrent()) {
            itemViewModel.setWidth(dpToPx(300));
        } else {
            itemViewModel.setWidth(dpToPx(200));
        }

    }
}
