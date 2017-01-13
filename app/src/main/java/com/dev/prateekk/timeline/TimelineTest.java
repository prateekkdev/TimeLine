package com.dev.prateekk.timeline;

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


    public static void clearData() {
        bookingHashMap.clear();
        bookingPriorityArrayList.clear();
    }


    public static void execute(TimelineFragment fragment) {
        initialAddThreeAcceptedBookings();
        fragment.updateData(bookingHashMap, bookingPriorityArrayList);

    }

    private static void initialAddThreeAcceptedBookings() {
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
    }

}
