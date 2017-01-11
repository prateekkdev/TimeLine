package com.dev.prateekk.timeline;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dev.prateekk.timeline.databinding.TimelineBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 10/01/17.
 */

public class Timeline extends RelativeLayout implements TimelineContract.View {

    private TimelineBinding timelineBinding;
    private HashMap<String, SDBookingData> bookingHashMap = new HashMap<>();
    private ArrayList<BookingPriority> bookingPriorityArrayList = new ArrayList<>();
    private ArrayList<TimelineMainItemViewModel> timeLineItemList = new ArrayList<>();

    public Timeline(Context context, AttributeSet attrs) {
        this(context);
    }

    public Timeline(Context context) {
        super(context);

        // EventBus.getDefault().register(this);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // TimelineBinding binding = TimelineBinding.inflate(inflater);

        timelineBinding = DataBindingUtil.inflate(mInflater, R.layout.timeline, null, false);
        timelineBinding.getRoot().setLayoutParams(new ViewGroup.MarginLayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        timelineBinding.timelineRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        timelineBinding.timelineRecyclerView.setItemAnimator(new DefaultItemAnimator());

        timelineBinding.timelineRecyclerView.setAdapter(new TimelineRecyclerViewAdapter(bookingHashMap, bookingPriorityArrayList));

        addBookingData();

        timelineBinding.setTimeline(new TimelineViewModel(this, bookingHashMap));

        // initTemp();

        // TODO Could stuff like this be done using RxJava
        timelineBinding.timelineRecyclerView.setRecyclerViewInterface(new TimelineRecyclerViewInterface() {
            @Override
            public void onScrolled() {

                // TODO Think of a better way
                // If drop down open, need to close
                if (timelineBinding.getTimeline().getDropDownSelected()) {
                    timelineBinding.getTimeline().setSelectedDropDown(null);
                }
            }
        });

        this.addView(timelineBinding.getRoot());
    }

    @Override

    // Called everytime layout gets invalidated
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    @Override

    // Called initially
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        doInitialTransitionOfFullLength();

    }

    private void initTemp() {
        Button button = (Button) this.findViewById(R.id.button_temp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timeLineItemList.size() > 0) {
                    timeLineItemList.remove(0);
                    timelineBinding.timelineRecyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        });

    }

    private void doInitialTransitionOfFullLength() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timelineBinding.timelineRecyclerView.scrollListToPosition(timelineBinding.timelineRecyclerView, 4);
            }
        }, 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timelineBinding.timelineRecyclerView.scrollListToPosition(timelineBinding.timelineRecyclerView, timelineBinding.timelineRecyclerView.getAdapter().getCurrentIndex());
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

    @Override
    public void onShowDropDown(String bookingId) {
        SDBookingData bookingData = bookingHashMap.get(bookingId);

        // If drop down already selected, unselect it(null) or else select it(populate with bookingData)
        if (timelineBinding.getTimeline().getDropDownSelected()) {
            timelineBinding.getTimeline().setSelectedDropDown(null);
        } else {
            timelineBinding.getTimeline().setSelectedDropDown(bookingData);
        }
    }
}