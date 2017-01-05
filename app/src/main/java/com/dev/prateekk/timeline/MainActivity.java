package com.dev.prateekk.timeline;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.dev.prateekk.timeline.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<BookingPriority> bookingPriorityArrayList = new ArrayList<>();
    HashMap<String, SDBookingData> bookingHashMap = new HashMap<>();
    TimelineItemAdapter timelineItemAdapter;
    private ArrayList<TimelineMainItemViewModel> timeLineItemList = new ArrayList<>();
    private TimelineRecyclerView recyclerView;
    private TimelineRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addBookingData();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TimelineViewModel timelineViewModel = new TimelineViewModel(bookingHashMap);
        timelineViewModel.setCurrentBookingDataItem(bookingHashMap.get("222"));

        binding.setTimeline(timelineViewModel);
        binding.setHandlers(new TimelineHandler());

        recyclerView = (TimelineRecyclerView) findViewById(R.id.timeline_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        timelineItemAdapter = new TimelineItemAdapter(bookingHashMap, bookingPriorityArrayList);
        mAdapter = new TimelineRecyclerViewAdapter(timelineItemAdapter);
        recyclerView.setAdapter(mAdapter);

        initTemp();

        timelineItemAdapter.updateList();

        // int currentPosition = timelineItemAdapter.getCurrentIndex() + 2;

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
                    TimelineMainItemViewModel item = timeLineItemList.remove(0);
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
                recyclerView.scrollListToPosition(recyclerView, MainActivity.this.timelineItemAdapter.getCurrentIndex());
            }
        }, 2000);
    }

    private void addBookingData() {

        SDBookingData bookingData1 = new SDBookingData();
        bookingData1.getBookingResponse().setKrn("111");
        bookingData1.setBookingCurrent(false);
        bookingData1.mBookingResponse.setStatus("accepted");
        bookingData1.mBookingResponse.customer_info.name = "Prateek1";
        bookingData1.mBookingResponse.customer_info.phone_no = "7022359793";

        SDBookingData bookingData2 = new SDBookingData();
        bookingData2.getBookingResponse().setKrn("222");
        bookingData2.setBookingCurrent(true);
        bookingData2.mBookingResponse.setStatus("invoice");
        bookingData2.mBookingResponse.customer_info.name = "Prateek2";
        bookingData2.mBookingResponse.customer_info.phone_no = "0987890";

        bookingHashMap.put("111", bookingData1);
        bookingHashMap.put("222", bookingData2);


        bookingPriorityArrayList.add(new BookingPriority("111", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("111", "drop"));
        bookingPriorityArrayList.add(new BookingPriority("222", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("222", "drop"));

        // TimelineItemAdapter.updateList();
//        SDBookingData bookingData3 = new SDBookingData();
//        bookingData3.setBookingCurrent(false);
//        bookingData3.mBookingResponse.setStatus("accepted");
//        bookingData3.mBookingResponse.customer_info.name = "Prateek2";
//
//        SDBookingData bookingData4 = new SDBookingData();
//        bookingData4.setBookingCurrent(false);
//        bookingData4.mBookingResponse.setStatus("payment");
//        bookingData4.mBookingResponse.customer_info.name = "Prateek2";


    }
}