package com.dev.prateekk.timeline;

/**
 * Created by prateek.kesarwani on 11/01/17.
 */

public interface TimelineContract {

    interface View {
        void onShowDropDown(String bookingId);

        // This would be needed to scroll recycler view to that location.
        void onItemSelected(int itemPosition);
    }

    interface ViewModel {
        // void onItemSelected(android.view.View view, int itemPosition);
    }

}
