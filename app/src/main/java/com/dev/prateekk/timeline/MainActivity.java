package com.dev.prateekk.timeline;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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

        initTemp();

        prepareCustomerData();


    }

    private void initTemp() {
        Button button = (Button) this.findViewById(R.id.button_temp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimelineItemModel item = timeLineItemList.remove(0);
                // item.setMidTitle("Changed");
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    private void prepareCustomerData() {
        timeLineItemList.add(new TimelineItemModel("PICK UP", "Prateek1", Color.GREEN, true, getResources().getDrawable(R.drawable.circle_green)));
        timeLineItemList.add(new TimelineItemModel("PICK UP", "Prateek2", Color.GREEN, false, getResources().getDrawable(R.drawable.circle_green)));
        timeLineItemList.add(new TimelineItemModel("DROP", "Prateek1", Color.RED, false, getResources().getDrawable(R.drawable.circle_red)));
        timeLineItemList.add(new TimelineItemModel("DROP", "Prateek1", Color.RED, false, getResources().getDrawable(R.drawable.circle_red)));
    }
}