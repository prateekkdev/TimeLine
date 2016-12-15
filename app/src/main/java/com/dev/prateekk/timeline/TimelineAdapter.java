package com.dev.prateekk.timeline;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by prateek.kesarwani on 15/12/16.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.BindingHolder> {

    private List<TimelineItemModel> timelineItemModelList;

    public TimelineAdapter(List<TimelineItemModel> timelineItemModelList) {
        this.timelineItemModelList = timelineItemModelList;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_view_item, parent, false);
        BindingHolder holder = new BindingHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final TimelineItemModel item = timelineItemModelList.get(position);
        holder.getBinding().setVariable(BR.timelineitem, item);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return timelineItemModelList.size();
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