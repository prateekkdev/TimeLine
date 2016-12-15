package com.dev.prateekk.timeline;

import android.view.View;
import android.widget.Toast;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineHandler {

    public TimelineHandler() {
        System.out.print(true);
    }

    public void onClickInfo(View view) {
        Toast.makeText(view.getContext(), "View Clicked", Toast.LENGTH_SHORT).show();
    }
}