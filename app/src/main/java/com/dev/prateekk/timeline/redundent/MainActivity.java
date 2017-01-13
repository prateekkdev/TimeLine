package com.dev.prateekk.timeline.redundent;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.dev.prateekk.timeline.R;
import com.dev.prateekk.timeline.TimelineFragment;
import com.dev.prateekk.timeline.TimelineTest;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TimelineFragment timelineFragment;
    private ArrayList<BookingPriority> bookingPriorityArrayList = new ArrayList<>();
    private HashMap<String, SDBookingData> bookingHashMap = new HashMap<>();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timelineFragment = (TimelineFragment) getSupportFragmentManager().findFragmentById(R.id.timeline_container);

        TimelineTest.execute(timelineFragment);

        // addBookingData();

        // addBookingDataDelayed(5);

        // timelineFragment.updateData(bookingHashMap, bookingPriorityArrayList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // final TimelineRecyclerView items = (TimelineRecyclerView) findViewById(R.id.recycler_view);

        /*
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
        */
    }

    private void addBookingDataDelayed(int sec) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addBooking();
                timelineFragment.updateData(bookingHashMap, bookingPriorityArrayList);

                timelineFragment.scrollToCurrentIndex();
            }
        }, sec * 1000);
    }

    private void addBooking() {
        // First remove previous data
        bookingHashMap.clear();
        bookingPriorityArrayList.clear();

        SDBookingData bookingData1 = new SDBookingData();
        bookingData1.getBookingResponse().setKrn("111");
        bookingData1.setBookingCurrent(false);
        bookingData1.mBookingResponse.setStatus("completed");
        bookingData1.mBookingResponse.customer_info.name = "Prateek1";
        bookingData1.mBookingResponse.customer_info.phone_no = "7022359793";

        SDBookingData bookingData2 = new SDBookingData();
        bookingData2.getBookingResponse().setKrn("222");
        bookingData2.setBookingCurrent(true);
        bookingData2.mBookingResponse.setStatus("payment");
        bookingData2.mBookingResponse.customer_info.name = "Prateek2";
        bookingData2.mBookingResponse.customer_info.phone_no = "0987890";

        bookingHashMap.put("111", bookingData1);
        bookingHashMap.put("222", bookingData2);

        bookingPriorityArrayList.add(new BookingPriority("111", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("111", "drop"));
        bookingPriorityArrayList.add(new BookingPriority("222", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("222", "drop"));
    }

}