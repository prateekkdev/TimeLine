package com.dev.prateekk.timeline;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TimelineItemModel> timeLineItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TimelineAdapter mAdapter;

    private float itemWidth;
    private float padding;
    private float firstItemWidth;
    private float allPixels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TimelineAdapter(timeLineItemList);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        itemWidth = dpToPx(300);
        padding = (size.x - itemWidth) / 2;
        firstItemWidth = dpToPx(300);

        allPixels = 0;


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
                allPixels += dx;
            }
        });

        recyclerView.setAdapter(mAdapter);

        initTemp();

        prepareBookingData();


//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//
//        int totalVisibleItems = mLayoutManager.findLastVisibleItemPosition() - mLayoutManager.findFirstVisibleItemPosition();
//        int centeredItemPosition = totalVisibleItems / 2;
//        recyclerView.smoothScrollToPosition(2);
//        recyclerView.setScrollY(centeredItemPosition);
//
//        final LinearSnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
//
//        recyclerView.setOnFlingListener(snapHelper);
//
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        })
//        );


    }

    private void initTemp() {
        Button button = (Button) this.findViewById(R.id.button_temp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimelineItemModel item = timeLineItemList.remove(0);
                // item.setMidTitle("Changed");
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    private void prepareBookingData() {

        SDBookingData bookingData1 = new SDBookingData();
        bookingData1.setBookingCurrent(true);
        bookingData1.mBookingResponse.setStatus("accepted");
        bookingData1.mBookingResponse.customer_info.name = "Prateek1";

//        SDBookingData bookingData2 = new SDBookingData();
//        bookingData2.setBookingCurrent(false);
//        bookingData2.mBookingResponse.setStatus("payment");
//        bookingData2.mBookingResponse.customer_info.name = "Prateek2";
//
//        SDBookingData bookingData3 = new SDBookingData();
//        bookingData3.setBookingCurrent(false);
//        bookingData3.mBookingResponse.setStatus("invoice");
//        bookingData3.mBookingResponse.customer_info.name = "Prateek3";
//
        timeLineItemList.add(new TimelineItemModel(bookingData1));
//        timeLineItemList.add(new TimelineItemModel(bookingData2));
//        timeLineItemList.add(new TimelineItemModel(bookingData3));

    }

    private void calculatePositionAndScroll(RecyclerView recyclerView) {
        int expectedPosition = Math.round((allPixels + padding - firstItemWidth) / itemWidth);
        // Special cases for the padding items
        if (expectedPosition == -1) {
            expectedPosition = 0;
        } else if (expectedPosition >= recyclerView.getAdapter().getItemCount() - 2) {
            expectedPosition--;
        }
        scrollListToPosition(recyclerView, expectedPosition);
    }

    private void scrollListToPosition(RecyclerView recyclerView, int expectedPosition) {
        float targetScrollPos = expectedPosition * itemWidth + firstItemWidth - padding;
        float missingPx = targetScrollPos - allPixels;
        if (missingPx != 0) {
            recyclerView.smoothScrollBy((int) missingPx, 0);
        }
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}