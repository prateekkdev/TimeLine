package com.dev.prateekk.timeline;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by prateek.kesarwani on 16/12/16.
 */

public class TimelineRecyclerView extends RecyclerView {

    private float itemWidth;
    private float padding;
    private float extraItemWidth;
    private float totalPixelMovement;

    public TimelineRecyclerView(Context context) {
        super(context);
        initRecyclerView(context);
    }

    public TimelineRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initRecyclerView(context);
    }

    public TimelineRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initRecyclerView(context);
    }

    private void initRecyclerView(Context context) {
        this.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                synchronized (this) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        calculatePositionAndScroll(recyclerView);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalPixelMovement += dx;
            }
        });

        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        itemWidth = getResources().getDimension(R.dimen.item_width);
        padding = (size.x - itemWidth) / 2;
        extraItemWidth = getResources().getDimension(R.dimen.extra_item_width);

        totalPixelMovement = 0;
    }

    public void calculatePositionAndScroll(RecyclerView recyclerView) {
        int expectedPosition = Math.round((totalPixelMovement + padding - extraItemWidth) / itemWidth);
        // Special cases for the padding items
        if (expectedPosition == -1) {
            expectedPosition = 0;
        } else if (expectedPosition >= recyclerView.getAdapter().getItemCount() - 2) {
            expectedPosition--;
        }
        scrollListToPosition(recyclerView, expectedPosition);
    }

    private void scrollListToPosition(RecyclerView recyclerView, int expectedPosition) {

        float targetScrollPos = expectedPosition * itemWidth + extraItemWidth - padding;

        if (expectedPosition == recyclerView.getAdapter().getItemCount() - 3) {

        } else {
            float pixelToMoveBack = targetScrollPos - totalPixelMovement;
            if (pixelToMoveBack != 0) {
                recyclerView.smoothScrollBy((int) pixelToMoveBack, 0);
            }
        }

    }
}