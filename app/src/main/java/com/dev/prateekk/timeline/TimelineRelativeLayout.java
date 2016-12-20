package com.dev.prateekk.timeline;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by prateek.kesarwani on 19/12/16.
 */

public class TimelineRelativeLayout extends RelativeLayout {

    public TimelineRelativeLayout(Context context) {
        super(context);
    }

    public TimelineRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimelineRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindingAdapter("android:layout_width")
    public void setLayoutWidth(View view, int width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }
}