package com.dev.prateekk.timeline;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.prateekk.timeline.databinding.TimelineBinding;
import com.dev.prateekk.timeline.redundent.BookingPriority;
import com.dev.prateekk.timeline.redundent.SDBookingData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 10/01/17.
 */

public class TimelineFragment extends Fragment implements TimelineContract.View {

    private TimelineBinding timelineBinding;
    private TimelineRecyclerViewAdapter mAdapter;

    private HashMap<String, SDBookingData> bookingHashMap = new HashMap<>();
    private ArrayList<BookingPriority> bookingPriorityArrayList = new ArrayList<>();

    public TimelineFragment() {

    }

    // TODO newInstance should be used along with Bundle
    public TimelineFragment(HashMap<String, SDBookingData> bookingHashMap, ArrayList<BookingPriority> bookingPriorityArrayList) {
        this.bookingHashMap = bookingHashMap;
        this.bookingPriorityArrayList = bookingPriorityArrayList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater mInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = mInflater.inflate(R.layout.timeline, null);

        timelineBinding = TimelineBinding.bind(view);

        timelineBinding.setTimelineViewModel(new TimelineViewModel(this, bookingHashMap));

        mAdapter = new TimelineRecyclerViewAdapter(this, bookingHashMap, bookingPriorityArrayList);
        timelineBinding.timelineRecyclerView.setAdapter(mAdapter);
        timelineBinding.timelineRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        timelineBinding.timelineRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // TODO Could stuff like this be done using RxJava
        timelineBinding.timelineRecyclerView.setRecyclerViewInterface(new TimelineRecyclerViewInterface() {
            @Override
            public void onScrolled(int index) {

                // TODO This would render adapter non-testable(Even updateList - Need to used interfacing here as well)
                mAdapter.updateSelected(index);

                // TODO Think of a better way
                // If drop down open, need to close
                if (timelineBinding.getTimelineViewModel().getDropDownSelected()) {
                    timelineBinding.getTimelineViewModel().setSelectedDropDown(null);
                }
            }
        });

        return view;
    }

    public void updateData(HashMap<String, SDBookingData> bookingHashMap, ArrayList<BookingPriority> bookingPriorityArrayList) {
        this.bookingHashMap = bookingHashMap;
        this.bookingPriorityArrayList = bookingPriorityArrayList;
        mAdapter.updateData(bookingHashMap, bookingPriorityArrayList);
        // timelineBinding.timelineRecyclerView.invalidate();
        // timelineBinding.timelineRecyclerView.getAdapter().notifyDataSetChanged();
        // timelineBinding.timelineRecyclerView.invalidate();
        // mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        doInitialTransitionOfFullLength();
    }

    /*
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
    */

    public void scrollToCurrentIndex() {
        timelineBinding.timelineRecyclerView.scrollListToPosition(timelineBinding.timelineRecyclerView.getAdapter().getCurrentIndex());
    }

    private void doInitialTransitionOfFullLength() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timelineBinding.timelineRecyclerView.scrollListToPosition(4);
            }
        }, 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timelineBinding.timelineRecyclerView.scrollListToPosition(timelineBinding.timelineRecyclerView.getAdapter().getCurrentIndex());
            }
        }, 2000);
    }

    @Override
    public void onShowDropDown(String bookingId) {
        SDBookingData bookingData = bookingHashMap.get(bookingId);

        // If drop down already selected, unselect it(null) or else select it(populate with bookingData)
        if (timelineBinding.getTimelineViewModel().getDropDownSelected()) {
            timelineBinding.getTimelineViewModel().setSelectedDropDown(null);
        } else {
            timelineBinding.getTimelineViewModel().setSelectedDropDown(bookingData);
        }
    }

    @Override
    public void onItemSelected(int itemPostion) {
        scrollToCurrentIndex();
    }
}