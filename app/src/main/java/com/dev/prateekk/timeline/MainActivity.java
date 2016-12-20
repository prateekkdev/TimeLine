package com.dev.prateekk.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TimelineItemModel> timeLineItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TimelineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TimelineAdapter(timeLineItemList);

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
        final TimelineRecyclerView items = (TimelineRecyclerView) findViewById(R.id.recycler_view);

        ViewTreeObserver vto = items.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                items.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                items.calculatePositionAndScroll(items);
            }
        });
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

        SDBookingData bookingData2 = new SDBookingData();
        bookingData2.setBookingCurrent(false);
        bookingData2.mBookingResponse.setStatus("payment");
        bookingData2.mBookingResponse.customer_info.name = "Prateek2";

        SDBookingData bookingData3 = new SDBookingData();
        bookingData3.setBookingCurrent(false);
        bookingData3.mBookingResponse.setStatus("invoice");
        bookingData3.mBookingResponse.customer_info.name = "Prateek3";

        timeLineItemList.add(new TimelineItemModel(bookingData1));
        timeLineItemList.add(new TimelineItemModel(bookingData2));
        timeLineItemList.add(new TimelineItemModel(bookingData3));

    }
}