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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    ArrayList<BookingPriority> bookingPriorityArrayList = new ArrayList<>();
    HashMap<String, SDBookingData> bookingHashMap = new HashMap<>();
    TimelineConverterUtil timelineConverterUtil;
    TimelineViewModel timelineViewModel;
    private ArrayList<TimelineMainItemViewModel> timeLineItemList = new ArrayList<>();
    private TimelineRecyclerView recyclerView;
    private TimelineRecyclerViewAdapter mAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addBookingData();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        timelineViewModel = new TimelineViewModel(bookingHashMap);
        // timelineViewModel.setSelectedDropDown(bookingHashMap.get("222"));

        binding.setTimeline(timelineViewModel);
        binding.setHandlers(new TimelineHandler());

        recyclerView = (TimelineRecyclerView) findViewById(R.id.timeline_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        timelineConverterUtil = new TimelineConverterUtil(bookingHashMap, bookingPriorityArrayList);
        mAdapter = new TimelineRecyclerViewAdapter(timelineConverterUtil);
        recyclerView.setAdapter(mAdapter);


        // TODO Could stuff like this be done using RxJava
        recyclerView.setRecyclerViewInterface(new TimelineRecyclerViewInterface() {
            @Override
            public void onScrolled() {

                // TODO Think of a better way
                // If drop down open, need to close
                if (timelineViewModel.getDropDownSelected()) {
                    timelineViewModel.setSelectedDropDown(null);
                }
            }
        });

        initTemp();

        // timelineConverterUtil.newUpdateData();
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
                recyclerView.scrollListToPosition(recyclerView, MainActivity.this.timelineConverterUtil.getCurrentIndex());
            }
        }, 2000);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTimelineDropDownEvent(TimelineDropDownEvent event) {

        String bookingId = event.getBookingId();
        SDBookingData bookingData = bookingHashMap.get(bookingId);

        // If drop down already selected, unselect it(null) or else select it(populate with bookingData)
        if (timelineViewModel.getDropDownSelected()) {
            timelineViewModel.setSelectedDropDown(null);
        } else {
            timelineViewModel.setSelectedDropDown(bookingData);
        }
    }
}