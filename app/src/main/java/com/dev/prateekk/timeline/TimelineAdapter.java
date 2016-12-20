package com.dev.prateekk.timeline;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.BindingHolder> {

    private static final int VIEW_TYPE_PADDING = 1;
    private static final int VIEW_TYPE_ITEM = 2;

    private List<TimelineItemModel> timelineItemModelList;

    public TimelineAdapter(List<TimelineItemModel> timelineItemModelList) {
        this.timelineItemModelList = timelineItemModelList;
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
        if (position == 0 || position == getItemCount() - 1) {
            return VIEW_TYPE_PADDING;
        }
        return VIEW_TYPE_ITEM;
    }


    @Override
    public TimelineAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int type) {

        View v;
        if (type == VIEW_TYPE_ITEM) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_view_item, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_padding, parent, false);
        }

        BindingHolder holder = new BindingHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {

        if (getItemViewType(position) == VIEW_TYPE_ITEM) {

            // As 0th position is for padding
            position = position - 1;

            final TimelineItemModel item = timelineItemModelList.get(position);
            holder.getBinding().setVariable(BR.timelineitem, item);
            holder.getBinding().executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return timelineItemModelList.size() + 2;
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