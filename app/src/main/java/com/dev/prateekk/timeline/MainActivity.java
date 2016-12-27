package com.dev.prateekk.timeline;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TimelineItemViewModel> timeLineItemList = new ArrayList<>();
    private TimelineRecyclerView recyclerView;
    private TimelineRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (TimelineRecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TimelineRecyclerViewAdapter(timeLineItemList);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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

    @Override
    protected void onResume() {
        super.onResume();
        // final TimelineRecyclerView items = (TimelineRecyclerView) findViewById(R.id.recycler_view);

        ViewTreeObserver vto = recyclerView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                recyclerView.calculatePositionAndScroll(recyclerView);

                // Initial Transition
                doInitialTransitionOfFullLength();
            }
        });
    }

    private void initTemp() {
        Button button = (Button) this.findViewById(R.id.button_temp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timeLineItemList.size() > 0) {
                    TimelineItemViewModel item = timeLineItemList.remove(0);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void doInitialTransitionOfFullLength() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.scrollListToPosition(recyclerView, 4);
            }
        }, 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.scrollListToPosition(recyclerView, 0);
            }
        }, 2000);
    }

    private void prepareBookingData() {

        SDBookingData bookingData1 = new SDBookingData();
        bookingData1.setBookingCurrent(true);
        bookingData1.mBookingResponse.setStatus("accepted");
        bookingData1.mBookingResponse.customer_info.name = "Prateek1";

        SDBookingData bookingData2 = new SDBookingData();
        bookingData2.setBookingCurrent(false);
        bookingData2.mBookingResponse.setStatus("payment");
        bookingData2.mBookingResponse.customer_info.name = "Prateek1";

        SDBookingData bookingData3 = new SDBookingData();
        bookingData3.setBookingCurrent(false);
        bookingData3.mBookingResponse.setStatus("accepted");
        bookingData3.mBookingResponse.customer_info.name = "Prateek2";

        SDBookingData bookingData4 = new SDBookingData();
        bookingData4.setBookingCurrent(false);
        bookingData4.mBookingResponse.setStatus("payment");
        bookingData4.mBookingResponse.customer_info.name = "Prateek2";

        timeLineItemList.add(new TimelineItemViewModel(bookingData1));
        timeLineItemList.add(new TimelineItemViewModel(bookingData2));
        timeLineItemList.add(new TimelineItemViewModel(bookingData3));
        timeLineItemList.add(new TimelineItemViewModel(bookingData4));

    }
}