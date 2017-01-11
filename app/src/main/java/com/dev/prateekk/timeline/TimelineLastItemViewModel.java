package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;

/**
 * Created by prateek.kesarwani on 05/01/17.
 */

public class TimelineLastItemViewModel extends BaseObservable {


    public boolean getEnabled() {

        // Need some mechanism to check for enable/disable of NoMoreBookings
        return true;
    }
}