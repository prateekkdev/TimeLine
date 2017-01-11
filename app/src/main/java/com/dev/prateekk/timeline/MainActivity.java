package com.dev.prateekk.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        addBookingData();

        timelineFragment = (TimelineFragment) getSupportFragmentManager().findFragmentById(R.id.timeline_container);

        timelineFragment.updateData(bookingHashMap, bookingPriorityArrayList);
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

    private void addBookingData() {

        SDBookingData bookingData1 = new SDBookingData();
        bookingData1.getBookingResponse().setKrn("111");
        bookingData1.setBookingCurrent(true);
        bookingData1.mBookingResponse.setStatus("accepted");
        bookingData1.mBookingResponse.customer_info.name = "Prateek1";
        bookingData1.mBookingResponse.customer_info.phone_no = "7022359793";

        SDBookingData bookingData2 = new SDBookingData();
        bookingData2.getBookingResponse().setKrn("222");
        bookingData2.setBookingCurrent(false);
        bookingData2.mBookingResponse.setStatus("accepted");
        bookingData2.mBookingResponse.customer_info.name = "Prateek2";
        bookingData2.mBookingResponse.customer_info.phone_no = "0987890";

        bookingHashMap.put("111", bookingData1);
        bookingHashMap.put("222", bookingData2);

        bookingPriorityArrayList.add(new BookingPriority("111", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("222", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("111", "drop"));
        bookingPriorityArrayList.add(new BookingPriority("222", "drop"));
    }

}