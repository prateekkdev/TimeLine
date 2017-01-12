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

    private TimelineRecyclerViewInterface recyclerViewInterface;

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

    public TimelineRecyclerViewInterface getRecyclerViewInterface() {
        return recyclerViewInterface;
    }

    public void setRecyclerViewInterface(TimelineRecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
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
        extraItemWidth = getResources().getDimension(R.dimen.first_item_width);

        totalPixelMovement = 0;
    }

    public void calculatePositionAndScroll(RecyclerView recyclerView) {
        int expectedPosition = Math.round((totalPixelMovement + padding - extraItemWidth) / itemWidth);
        // Special cases for the padding items
        if (expectedPosition == -1) {
            expectedPosition = 0;
        }

        scrollListToPosition(recyclerView, expectedPosition);
    }


    /**
     * Here 0 is the first element(So, 0 is First Item added(not the dummy one))
     * Size of recyclerView is the last element
     * Suppose size=4, ie 0, 1, 2, 3, 4. Here 0 is first bookign item and 4 is 'No more Bookings'
     *
     * @param recyclerView
     * @param expectedPosition
     */
    public void scrollListToPosition(RecyclerView recyclerView, int expectedPosition) {

        float targetScrollPos = expectedPosition * itemWidth + extraItemWidth - padding;

        float pixelToMoveBack = targetScrollPos - totalPixelMovement;
        if (pixelToMoveBack != 0) {
            recyclerView.smoothScrollBy((int) pixelToMoveBack, 0);
        }

        if (recyclerViewInterface != null) {
            recyclerViewInterface.onScrolled(expectedPosition);
        }

    }

    @Override
    public TimelineRecyclerViewAdapter getAdapter() {
        return (TimelineRecyclerViewAdapter) super.getAdapter();
    }
}