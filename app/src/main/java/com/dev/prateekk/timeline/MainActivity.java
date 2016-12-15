package com.dev.prateekk.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareCustomerData();

    }

    private void prepareCustomerData() {
        timeLineItemList.add(new TimelineItemModel("Pick Up", "Prateek1"));
        timeLineItemList.add(new TimelineItemModel("Pick Up", "Prateek2"));
        timeLineItemList.add(new TimelineItemModel("Drop", "Prateek1"));
        timeLineItemList.add(new TimelineItemModel("Drop", "Prateek1"));

        mAdapter.notifyDataSetChanged();
    }
}