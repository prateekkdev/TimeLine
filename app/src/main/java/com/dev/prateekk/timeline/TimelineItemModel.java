package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineItemModel extends BaseObservable {

    @Bindable
    private String topTitle;

    @Bindable
    private String midTitle;

    public TimelineItemModel() {
    }

    public TimelineItemModel(String topTitle, String midTitle) {
        this.topTitle = topTitle;
        this.midTitle = midTitle;
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
}

