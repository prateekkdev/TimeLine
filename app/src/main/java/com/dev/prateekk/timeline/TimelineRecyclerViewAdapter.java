package com.dev.prateekk.timeline;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.dev.prateekk.timeline.databinding.ItemTimelineLastBinding;
import com.dev.prateekk.timeline.databinding.ItemTimelineMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.BindingHolder> {

    private static final int VIEW_TYPE_FIRST = 1;
    private static final int VIEW_TYPE_MAIN = 2;
    private static final int VIEW_TYPE_LAST = 3;

    private int currentIndex;
    private ArrayList<TimelineMainItemViewModel> timelineMainItemViewModelList = new ArrayList<>();
    private HashMap<String, SDBookingData> bookingHashMap;
    private ArrayList<BookingPriority> priorityList;


    public TimelineRecyclerViewAdapter(HashMap<String, SDBookingData> bookingHashMap, ArrayList<BookingPriority> priorityList) {
        this.bookingHashMap = bookingHashMap;
        this.priorityList = priorityList;
        updateList();
    }

    @BindingAdapter("android:layout_width")
    public static void setRelativeLayoutWidth(View view, int width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("pname")
    public static void setThis(TextView textView, String pname) {
        textView.setText(pname);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_FIRST;
        } else if (position == getItemCount() - 1) {
            return VIEW_TYPE_LAST;
        }
        return VIEW_TYPE_MAIN;
    }

    @Override
    public TimelineRecyclerViewAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int type) {

        View v;

        if (type == VIEW_TYPE_FIRST) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline_first, parent, false);
        } else if (type == VIEW_TYPE_LAST) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline_last, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_timeline_main, parent, false);
        }

        BindingHolder holder = new BindingHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {

        if (getItemViewType(position) == VIEW_TYPE_LAST) {

            holder.getBinding().setVariable(BR.timelineitem, new TimelineLastItemViewModel());

            ((ItemTimelineLastBinding) holder.getBinding()).setHandlers(new TimelineHandler());

        } else if (getItemViewType(position) == VIEW_TYPE_MAIN) {

            // As 0th position is for padding
            position = position - 1;

            final TimelineMainItemViewModel item = timelineMainItemViewModelList.get(position);
            holder.getBinding().setVariable(BR.timelineitem, item);

            ((ItemTimelineMainBinding) holder.getBinding()).setHandlers(new TimelineHandler());
        }

        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return timelineMainItemViewModelList.size() + 2;
    }

    private boolean isItemAlreadyAdded(String krn, ArrayList<TimelineMainItemViewModel> timelineMainItemViewModelList) {

        for (TimelineMainItemViewModel item : timelineMainItemViewModelList) {
            if (item.getId().equalsIgnoreCase(krn)) {
                return true;
            }
        }

        return false;
    }

    private void updateNotCurrent(TimelineMainItemViewModel itemViewModel, String name) {
        itemViewModel.setIsCurrent(false);
        itemViewModel.setTopTitle(name);
        // TODO - Width isn't dynamic currently - Get 80% of device width pixels
        // itemViewModel.setWidth(dpToPx(50));
    }

    private void updateCurrent(TimelineMainItemViewModel itemViewModel, String status) {

        // This currentItem would be added later, so size would be its index
        currentIndex = timelineMainItemViewModelList.size();

        itemViewModel.setIsCurrent(true);

        itemViewModel.setTopTitle(status.equalsIgnoreCase("accepted")
                ? "PICKUP" : status.equalsIgnoreCase("driver_reached")
                ? "WAIT FOR" : status.equalsIgnoreCase("invoice")
                ? "BILLING FOR" : status.equalsIgnoreCase("payment")
                ? "DROP" : "");

        // TODO - Width isn't dynamic currently - Get 80% of device width pixels
        float widthToSet = getPixelsFromPercentScreen(80);
        // itemViewModel.setWidth(dpToPx(50));
    }

    private int getPixelsFromPercentScreen(int percent) {
        WindowManager wm = (WindowManager) TimelineApp.getApp().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels * percent / 100;
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = TimelineApp.getApp().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void newUpdateData() {

        for (BookingPriority priority : priorityList) {
            TimelineMainItemViewModel itemViewModel = new TimelineMainItemViewModel();

            SDBookingData bookingData = bookingHashMap.get(priority.krn);
            String status = bookingData.getStatus();
            boolean isCurrent = bookingData.isBookingCurrent();

            // Set Midtitle
            itemViewModel.setMidTitle(bookingData.getBookingResponse().getCustomer_info().name);

            if (isCurrent) {
                updateCurrent(itemViewModel, status);
            } else {
                updateNotCurrent(itemViewModel, bookingData.getBookingResponse().getCustomer_info().name);
            }

            itemViewModel.setId(bookingData.getKrn());

            if (priority.type.equalsIgnoreCase("pickup")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.green));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_green));
            } else if (priority.type.equalsIgnoreCase("drop")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.red));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_red));
            }

            timelineMainItemViewModelList.add(itemViewModel);

        }
    }

    public void updateList() {

        // TODO Clear list before updating.
        // TODO Really this is required, or we should just modify some aspects.
        timelineMainItemViewModelList.clear();

        // This flag would be used to grey out all data upto current action
        // This would also ensure for completed booking we have only one variant of item(not pickup and drop - as would give same info)
        boolean reachedCurrent = false;
        for (BookingPriority priority : priorityList) {
            TimelineMainItemViewModel itemViewModel = new TimelineMainItemViewModel();

            SDBookingData bookingData = bookingHashMap.get(priority.krn);

            String status = bookingData.getStatus();
            boolean isCurrent = bookingData.isBookingCurrent();

            itemViewModel.setMidTitle(bookingData.getBookingResponse().getCustomer_info().name);

            if (isCurrent) {

                reachedCurrent = true;

                if ((status.equalsIgnoreCase("accepted") && priority.type.equalsIgnoreCase("drop")) ||
                        (status.equalsIgnoreCase("payment") && priority.type.equalsIgnoreCase("pickup")) ||
                        (status.equalsIgnoreCase("invoice") && priority.type.equalsIgnoreCase("drop")) ||
                        (status.equalsIgnoreCase("driver_reached") && priority.type.equalsIgnoreCase("drop"))) {
                    // This is not current action, this item is for pickup already done or drop pending.
                    updateNotCurrent(itemViewModel, bookingData.getBookingResponse().getCustomer_info().name);
                } else {
                    updateCurrent(itemViewModel, status);
                }
            } else {
                updateNotCurrent(itemViewModel, bookingData.getBookingResponse().getCustomer_info().name);
            }

            if (!reachedCurrent) {

                // If this krn is already there before current action, means this is completed, so shouldn't be repeated as would not give any extra info.
                if (isItemAlreadyAdded(bookingData.getKrn(), timelineMainItemViewModelList)) {
                    continue;
                }

                // TODO Not using this, as one time we have to add item
                // If this is completed, means this item is already there in list so shouldn't be repeated as would not give any extra info.
                // We would have to start persisting completed state. Currently after completion, booking gets removed.
//                if (bookingData.getStatus().equalsIgnoreCase("completed")) {
//                    continue;
//                }

                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.grey));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_grey));
            } else if (priority.type.equalsIgnoreCase("pickup")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.green));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_green));
            } else if (priority.type.equalsIgnoreCase("drop")) {
                itemViewModel.setTopColor(TimelineApp.getApp().getResources().getColor(R.color.red));
                itemViewModel.setMidImgId(TimelineApp.getApp().getResources().getDrawable(R.drawable.circle_red));
            }

            itemViewModel.setId(bookingData.getKrn());

            timelineMainItemViewModelList.add(itemViewModel);
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);

        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}