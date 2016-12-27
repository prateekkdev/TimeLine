package com.dev.prateekk.timeline;

import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 27/12/16.
 */

public class TimelineAdapter {

    public static void updateList(ArrayList<TimelineItemViewModel> timelineItemViewModelList, HashMap<String, SDBookingData> bookingHashMap, ArrayList<BookingPriority> priorityList) {

        // This flag would be used to grey out all data upto current action
        // This would also ensure for completed booking we have only one variant of item(not pickup and drop - as would give same info)
        boolean reachedCurrent = false;
        for (BookingPriority priority : priorityList) {
            TimelineItemViewModel itemViewModel = new TimelineItemViewModel();

            SDBookingData bookingData = bookingHashMap.get(priority.krn);

            String status = bookingData.getStatus();
            boolean isCurrent = bookingData.isBookingCurrent();

            itemViewModel.setMidTitle(bookingData.getBookingResponse().getCustomer_info().name);

            if (isCurrent) {

                reachedCurrent = true;

                if ((status.equalsIgnoreCase("accepted") && priority.type.equalsIgnoreCase("drop")) ||
                        (status.equalsIgnoreCase("payment") && priority.type.equalsIgnoreCase("pickup")) ||
                        (status.equalsIgnoreCase("invoice") && priority.type.equalsIgnoreCase("drop")) ||
                        (status.equalsIgnoreCase("driver_reached") && priority.type.equalsIgnoreCase("drop"))) {
                    // This is not current action, this item is for pickup already done or drop pending.
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

            if (!reachedCurrent) {

                // If this krn is already there before current action, means this is completed, so shouldn't be repeated as would not give any extra info.
                if (isItemAlreadyAdded(bookingData.getKrn(), timelineItemViewModelList)) {
                    continue;
                }

                // TODO Not using this, as one time we have to add item
                // If this is completed, means this item is already there in list so shouldn't be repeated as would not give any extra info.
                // We would have to start persisting completed state. Currently after completion, booking gets removed.
//                if (bookingData.getStatus().equalsIgnoreCase("completed")) {
//                    continue;
//                }

                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.grey));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_grey));
            } else if (priority.type.equalsIgnoreCase("pickup")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.green));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_green));
            } else if (priority.type.equalsIgnoreCase("drop")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.red));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_red));
            }

            itemViewModel.setId(bookingData.getKrn());

            timelineItemViewModelList.add(itemViewModel);
        }
    }

    public static boolean isItemAlreadyAdded(String krn, ArrayList<TimelineItemViewModel> timelineItemViewModelList) {

        for (TimelineItemViewModel item : timelineItemViewModelList) {
            if (item.getId().equalsIgnoreCase(krn)) {
                return true;
            }
        }

        return false;
    }

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

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = TimelineApp.getApp().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
