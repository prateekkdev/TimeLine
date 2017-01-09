package com.dev.prateekk.timeline;

import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 27/12/16.
 */

public class TimelineConverterUtil {

    private int currentIndex;
    private ArrayList<TimelineMainItemViewModel> timelineMainItemViewModelList = new ArrayList<>();
    private HashMap<String, SDBookingData> bookingHashMap;
    private ArrayList<BookingPriority> priorityList;

    public TimelineConverterUtil(HashMap<String, SDBookingData> bookingHashMap, ArrayList<BookingPriority> priorityList) {
        this.bookingHashMap = bookingHashMap;
        this.priorityList = priorityList;

        updateList();
    }

    private boolean isItemAlreadyAdded(String krn, ArrayList<TimelineMainItemViewModel> timelineMainItemViewModelList) {

        for (TimelineMainItemViewModel item : timelineMainItemViewModelList) {
            if (item.getId().equalsIgnoreCase(krn)) {
                return true;
            }
        }

        return false;
    }

    private void updateNotCurrent(TimelineMainItemViewModel itemViewModel, String name) {
        itemViewModel.setIsCurrent(false);
        itemViewModel.setTopTitle(name);
        itemViewModel.setWidth(dpToPx(200));
    }

    private void updateCurrent(TimelineMainItemViewModel itemViewModel, String status) {

        // This currentItem would be added later, so size would be its index
        currentIndex = timelineMainItemViewModelList.size();

        itemViewModel.setIsCurrent(true);

        itemViewModel.setTopTitle(status.equalsIgnoreCase("accepted")
                ? "PICKUP" : status.equalsIgnoreCase("driver_reached")
                ? "WAIT FOR" : status.equalsIgnoreCase("invoice")
                ? "BILLING FOR" : status.equalsIgnoreCase("payment")
                ? "DROP" : "");
        itemViewModel.setWidth(dpToPx(300));
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = TimelineApp.getApp().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void newUpdateData() {

        for (BookingPriority priority : priorityList) {
            TimelineMainItemViewModel itemViewModel = new TimelineMainItemViewModel();

            SDBookingData bookingData = bookingHashMap.get(priority.krn);
            String status = bookingData.getStatus();
            boolean isCurrent = bookingData.isBookingCurrent();

            // Set Midtitle
            itemViewModel.setMidTitle(bookingData.getBookingResponse().getCustomer_info().name);

            if (isCurrent) {
                updateCurrent(itemViewModel, status);
            } else {
                updateNotCurrent(itemViewModel, bookingData.getBookingResponse().getCustomer_info().name);
            }

            itemViewModel.setId(bookingData.getKrn());

            if (priority.type.equalsIgnoreCase("pickup")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.green));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_green));
            } else if (priority.type.equalsIgnoreCase("drop")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.red));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_red));
            }

            timelineMainItemViewModelList.add(itemViewModel);

        }
    }

    public void updateList() {

        // TODO Clear list before updating.
        // TODO Really this is required, or we should just modify some aspects.
        timelineMainItemViewModelList.clear();

        // This flag would be used to grey out all data upto current action
        // This would also ensure for completed booking we have only one variant of item(not pickup and drop - as would give same info)
        boolean reachedCurrent = false;
        for (BookingPriority priority : priorityList) {
            TimelineMainItemViewModel itemViewModel = new TimelineMainItemViewModel();

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

            if (!reachedCurrent) {

                // If this krn is already there before current action, means this is completed, so shouldn't be repeated as would not give any extra info.
                if (isItemAlreadyAdded(bookingData.getKrn(), timelineMainItemViewModelList)) {
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

            timelineMainItemViewModelList.add(itemViewModel);
        }
    }

    public ArrayList<TimelineMainItemViewModel> getTimelineMainItemViewModelList() {
        return timelineMainItemViewModelList;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
