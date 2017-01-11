package com.dev.prateekk.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        // EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // final TimelineRecyclerView items = (TimelineRecyclerView) findViewById(R.id.recycler_view);

        /*
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
        */
    }

}