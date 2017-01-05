package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 04/01/17.
 */

public class TimelineViewModel extends BaseObservable {

    private HashMap<String, SDBookingData> bookingHashMap;

    private SDBookingData selectedBookingDataItem;

    public TimelineViewModel(HashMap<String, SDBookingData> bookingHashMap) {
        this.bookingHashMap = bookingHashMap;
    }

    @Bindable
    public SDBookingData getSelectedBookingDataItem() {
        return selectedBookingDataItem;
    }

    public void setSelectedBookingDataItem(SDBookingData selectedBookingDataItem) {
        this.selectedBookingDataItem = selectedBookingDataItem;
        // notifyPropertyChanged(BR.selectedBookingDataItem);
        notifyChange();
    }

    public boolean getShowTimeline() {

        if (bookingHashMap != null && bookingHashMap.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean getShowCancel() {

        /**
         * Currently we aren't maintaining completed state in hash map, rather hash map is emptied after stop trip.
         */
        if (selectedBookingDataItem == null || ((selectedBookingDataItem.getStatus().equalsIgnoreCase("payment")
                || selectedBookingDataItem.getStatus().equalsIgnoreCase("completed")))) {
            return false;
        }
        return true;
    }

    public boolean getShowMobile() {
        if (selectedBookingDataItem == null || selectedBookingDataItem.getStatus().equalsIgnoreCase("payment")) {
            return false;
        }
        return true;
    }

    public String getMobileNo() {
        if (selectedBookingDataItem != null) {
            return selectedBookingDataItem.getBookingResponse().customer_info.phone_no;
        }
        return "";

    }

    public boolean getShowEndTrip() {
        if (selectedBookingDataItem != null && selectedBookingDataItem.getStatus().equalsIgnoreCase("payment")) {
            return true;
        } else {
            return false;
        }
    }

    public String getBookingId() {
        if (selectedBookingDataItem != null) {
            return selectedBookingDataItem.getKrn();
        }
        return "";
    }

    public boolean getDropDownSelected() {
        return selectedBookingDataItem == null ? false : true;
    }
}