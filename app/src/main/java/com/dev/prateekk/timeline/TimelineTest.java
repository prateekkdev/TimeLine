package com.dev.prateekk.timeline;

import android.os.Handler;

import com.dev.prateekk.timeline.redundent.BookingPriority;
import com.dev.prateekk.timeline.redundent.SDBookingData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by prateek.kesarwani on 13/01/17.
 */

public class TimelineTest {


    private static ArrayList<BookingPriority> bookingPriorityArrayList = new ArrayList<>();
    private static HashMap<String, SDBookingData> bookingHashMap = new HashMap<>();

    private static Handler mHandler = new Handler();

    // Need to make this instance rather than static
    private static TimelineFragment timelineFragment;

    public static void clearData() {
        bookingHashMap.clear();
        bookingPriorityArrayList.clear();
    }


    public static void execute(TimelineFragment fragment) {
        timelineFragment = fragment;

        initialAddThreeAcceptedBookings();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                removeFirstBooking();
            }
        }, 5000);

    }

    private static void updateData(TimelineFragment fragment) {
        fragment.updateData(bookingHashMap, bookingPriorityArrayList);
    }

    private static void initialAddThreeAcceptedBookings() {

        // Always clear data, and build bookings rather than removing or modifying it.
        // As, we would directly get from SharedPreferences/SomeOtherDataStore in MainApplication.
        clearData();

        SDBookingData bookingData1 = new SDBookingData();
        bookingData1.getBookingResponse().setKrn("111");
        bookingData1.setBookingCurrent(true);
        bookingData1.mBookingResponse.setStatus("accepted");
        bookingData1.mBookingResponse.customer_info.name = "Prateek1";
        bookingData1.mBookingResponse.customer_info.phone_no = "1111111111";

        SDBookingData bookingData2 = new SDBookingData();
        bookingData2.getBookingResponse().setKrn("222");
        bookingData2.setBookingCurrent(false);
        bookingData2.mBookingResponse.setStatus("accepted");
        bookingData2.mBookingResponse.customer_info.name = "Prateek2";
        bookingData2.mBookingResponse.customer_info.phone_no = "2222222222";

        SDBookingData bookingData3 = new SDBookingData();
        bookingData3.getBookingResponse().setKrn("333");
        bookingData3.setBookingCurrent(false);
        bookingData3.mBookingResponse.setStatus("accepted");
        bookingData3.mBookingResponse.customer_info.name = "Prateek3";
        bookingData3.mBookingResponse.customer_info.phone_no = "3333333333";

        bookingHashMap.put("111", bookingData1);
        bookingHashMap.put("222", bookingData2);
        bookingHashMap.put("333", bookingData3);

        bookingPriorityArrayList.add(new BookingPriority("111", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("111", "drop"));
        bookingPriorityArrayList.add(new BookingPriority("222", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("222", "drop"));
        bookingPriorityArrayList.add(new BookingPriority("333", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("333", "drop"));

        updateData(timelineFragment);
    }

    private static void removeFirstBooking() {

        // When removing bookings, even then clear all and always remember to set Current.
        clearData();

        SDBookingData bookingData2 = new SDBookingData();
        bookingData2.getBookingResponse().setKrn("222");
        bookingData2.setBookingCurrent(true);
        bookingData2.mBookingResponse.setStatus("accepted");
        bookingData2.mBookingResponse.customer_info.name = "Prateek2";
        bookingData2.mBookingResponse.customer_info.phone_no = "2222222222";

        SDBookingData bookingData3 = new SDBookingData();
        bookingData3.getBookingResponse().setKrn("333");
        bookingData3.setBookingCurrent(false);
        bookingData3.mBookingResponse.setStatus("accepted");
        bookingData3.mBookingResponse.customer_info.name = "Prateek3";
        bookingData3.mBookingResponse.customer_info.phone_no = "3333333333";

        bookingHashMap.put("222", bookingData2);
        bookingHashMap.put("333", bookingData3);

        bookingPriorityArrayList.add(new BookingPriority("222", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("222", "drop"));
        bookingPriorityArrayList.add(new BookingPriority("333", "pickup"));
        bookingPriorityArrayList.add(new BookingPriority("333", "drop"));

        updateData(timelineFragment);

    }

    private static void removeFirstBookings() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bookingHashMap.remove("111");
                bookingPriorityArrayList.remove(0);
                bookingPriorityArrayList.remove(1);

                updateData(timelineFragment);
            }
        }, 5000);
    }
}