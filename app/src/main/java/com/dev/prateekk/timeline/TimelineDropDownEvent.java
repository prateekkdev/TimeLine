package com.dev.prateekk.timeline;

import com.dev.prateekk.timeline.redundent.TimelineEvent;

/**
 * Created by prateek.kesarwani on 05/01/17.
 */

public class TimelineDropDownEvent extends TimelineEvent {

    private String bookingId;

    public TimelineDropDownEvent(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }
}
