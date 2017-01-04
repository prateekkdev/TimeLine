package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;

import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 04/01/17.
 */

public class TimelineViewModel extends BaseObservable {

    private HashMap<String, SDBookingData> bookingHashMap;

    private SDBookingData currentBookingDataItem;

    private boolean shouldShowTimeline;

    private boolean shouldShowCancel;

    private boolean shouldShowMobile;

    private boolean shouldShowEndTrip;

    private String mobileNo;

    public TimelineViewModel(HashMap<String, SDBookingData> bookingHashMap) {
        this.bookingHashMap = bookingHashMap;
    }

    public SDBookingData getCurrentBookingDataItem() {
        return currentBookingDataItem;
    }

    public void setCurrentBookingDataItem(SDBookingData currentBookingDataItem) {
        this.currentBookingDataItem = currentBookingDataItem;
    }

    public boolean getShowTimeline() {

        if (bookingHashMap != null && bookingHashMap.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean getShowCancel() {

        /**
         * Currently we aren't maintaining completed state in hash map, rather hash map is emptied after stop trip.
         */
        if (currentBookingDataItem != null && (currentBookingDataItem.getStatus().equalsIgnoreCase("payment")
                || currentBookingDataItem.getStatus().equalsIgnoreCase("completed"))) {
            return false;
        } else {
            return true;
        }
    }

    public boolean getShowMobile() {
        if (currentBookingDataItem != null && currentBookingDataItem.getStatus().equalsIgnoreCase("payment")) {
            return false;
        } else {
            return true;
        }
    }

    public String getMobileNo() {
        return currentBookingDataItem.getBookingResponse().customer_info.phone_no;
    }

    public boolean getShowEndTrip() {
        if (currentBookingDataItem != null && currentBookingDataItem.getStatus().equalsIgnoreCase("payment")) {
            return true;
        } else {
            return false;
        }
    }
}