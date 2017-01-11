package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.dev.prateekk.timeline.redundent.SDBookingData;

import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 04/01/17.
 */

public class TimelineViewModel extends BaseObservable implements TimelineContract.ViewModel {

    private TimelineContract.View timelineView;
    private HashMap<String, SDBookingData> bookingHashMap;
    private SDBookingData selectedDropDown;

    public TimelineViewModel(TimelineContract.View view, HashMap<String, SDBookingData> bookingHashMap) {
        this.timelineView = view;
        this.bookingHashMap = bookingHashMap;
    }

    @Bindable
    public SDBookingData getSelectedDropDown() {
        return selectedDropDown;
    }

    public void setSelectedDropDown(SDBookingData selectedDropDown) {
        this.selectedDropDown = selectedDropDown;
        // notifyPropertyChanged(BR.selectedDropDown);
        notifyChange();
    }

    // TODO Check this
    public boolean getShowTimeline() {
        return true;
//        if (bookingHashMap != null && bookingHashMap.size() > 0) {
//            return true;
//        }
//        return false;
    }

    public boolean getShowCancel() {

        /**
         * Currently we aren't maintaining completed state in hash map, rather hash map is emptied after stop trip.
         */
        if (selectedDropDown == null || ((selectedDropDown.getStatus().equalsIgnoreCase("payment")
                || selectedDropDown.getStatus().equalsIgnoreCase("completed")))) {
            return false;
        }
        return true;
    }

    public boolean getShowMobile() {
        if (selectedDropDown == null || selectedDropDown.getStatus().equalsIgnoreCase("payment")) {
            return false;
        }
        return true;
    }

    public String getMobileNo() {
        if (selectedDropDown != null) {
            return selectedDropDown.getBookingResponse().customer_info.phone_no;
        }
        return "";
    }

    public boolean getShowEndTrip() {
        if (selectedDropDown != null && selectedDropDown.getStatus().equalsIgnoreCase("payment")) {
            return true;
        } else {
            return false;
        }
    }

    public String getBookingId() {
        if (selectedDropDown != null) {
            return selectedDropDown.getKrn();
        }
        return "";
    }

    public boolean getDropDownSelected() {
        return selectedDropDown == null ? false : true;
    }
}