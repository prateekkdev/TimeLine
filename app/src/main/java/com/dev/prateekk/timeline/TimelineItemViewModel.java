package com.dev.prateekk.timeline;

import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineItemViewModel extends BaseObservable {

    private String topTitle;
    private int topColor;

    private String midTitle;

    private int width;

    private String id;

    private boolean isCurrent;

    private Drawable midImgDrawable;

    private boolean shouldShowCancel;

    private String mMobileNo;

    public TimelineItemViewModel() {
    }

    public boolean shouldShowCancel() {
        return shouldShowCancel;
    }

    public void setShouldShowCancel(boolean shouldShowCancel) {
        this.shouldShowCancel = shouldShowCancel;
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

    public void onClickInfo(View view) {
        Toast.makeText(view.getContext(), "View Clicked", Toast.LENGTH_SHORT).show();
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

    public String getMobileNo() {
        return mMobileNo;
    }

    public void setMobileNo(String mMobileNo) {
        this.mMobileNo = mMobileNo;
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if (this.id.equalsIgnoreCase(((TimelineItemViewModel) obj).id)) {
            return true;
        }
        return false;
    }
    */
}