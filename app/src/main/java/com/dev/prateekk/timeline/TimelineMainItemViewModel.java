package com.dev.prateekk.timeline;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineMainItemViewModel extends TimelineItemViewModel {

    private String topTitle;
    private int topColor;

    private String midTitle;

    private int width;

    private String id;

    private boolean isCurrent;

    private Drawable midImgDrawable;

    public TimelineMainItemViewModel(TimelineContract.View timelineView, int itemPostion) {
        super(timelineView, itemPostion);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Drawable getMidImgId() {
        return midImgDrawable;
    }

    public void setMidImgId(Drawable midImgId) {
        this.midImgDrawable = midImgId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public int getTopColor() {
        return topColor;
    }

    public void setTopColor(int topColor) {
        this.topColor = topColor;
    }

    public boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public void onShowDropDown(View view, String bookingId) {

        // Drop down would be shown only if selected, else item would be selected.

        // TODO Need to enforce this logic for all actions
        // So, every action should only be available when this item is selected,
        // Else this action won't be taken and this item is made selected(And scrolled thereafter).

        if (isSelected()) {
            timelineView.onShowDropDown(bookingId);
        } else {
            timelineView.onItemClick(this.getItemPosition());
        }
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if (this.id.equalsIgnoreCase(((TimelineMainItemViewModel) obj).id)) {
            return true;
        }
        return false;
    }
    */
}