package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;

/**
 * Created by prateek.kesarwani on 04/01/17.
 */

public class TimelineViewModel extends BaseObservable {

    private boolean shouldShowTimeline;

    private boolean shouldShowCancel;

    private boolean shouldShowMobile;

    private boolean shouldShowEndTrip;

    private String mobileNo;

    public boolean shouldShowTimeline() {
        return shouldShowTimeline;
    }

    public void setShouldShowTimeline(boolean shouldShowTimeline) {
        this.shouldShowTimeline = shouldShowTimeline;
    }

    public boolean shouldShowCancel() {
        return shouldShowCancel;
    }

    public void setShouldShowCancel(boolean shouldShowCancel) {
        this.shouldShowCancel = shouldShowCancel;
    }

    public boolean shouldShowMobile() {
        return shouldShowMobile;
    }

    public void setShouldShowMobile(boolean shouldShowMobile) {
        this.shouldShowMobile = shouldShowMobile;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public boolean shouldShowEndTrip() {
        return shouldShowEndTrip;
    }

    public void setShouldShowEndTrip(boolean shouldShowEndTrip) {
        this.shouldShowEndTrip = shouldShowEndTrip;
    }
}