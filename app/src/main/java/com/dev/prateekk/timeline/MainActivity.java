package com.dev.prateekk.timeline;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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

//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

//        int totalVisibleItems = mLayoutManager.findLastVisibleItemPosition() - mLayoutManager.findFirstVisibleItemPosition();
//        int centeredItemPosition = totalVisibleItems / 2;
//        recyclerView.smoothScrollToPosition(2);
//        recyclerView.setScrollY(centeredItemPosition);

//        final LinearSnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
//
//        recyclerView.setOnFlingListener(snapHelper);

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

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}