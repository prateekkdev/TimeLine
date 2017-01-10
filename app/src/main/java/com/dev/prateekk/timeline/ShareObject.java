package com.dev.prateekk.timeline;

import java.util.ArrayList;
import java.util.HashMap;

enum Order {
    PICKUP,
    DROP
}

enum State {
    ACCEPTED(1),
    REACHED(2),
    INVOICE(3),
    PAYMENT(4),
    COMPLETE(5);

    private int value;

    State(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }
}

enum StateStatus {
    PENDING,
    DONE
}

/**
 * Created by prateek.kesarwani on 10/01/17.
 */

class BookingPriority1 {
    Order order;
    String krn;
}

class BookingState {

    /**
     * PICKUP = ACCEPTED, REACHED, INVOICE
     * DROP = PAYMENT, COMPLETE
     */

    State state;
    StateStatus stateStatus;
}

class obj {
    State state;
    String krn;
    SDBookingData object;
}

public class ShareObject {

    ArrayList<obj> shareObjects;

//    void makeShareObject(HashMap<String, SDBookingData> bookingDataHashMap, ArrayList<BookingPriority1> bookingPriorityList) {
//
//        for (BookingPriority1 bookingPriority1 : bookingPriorityList) {
//
//            if(bookingPriority1.order == Order.PICKUP) {
//                shareObjects.add(bookingPriority1.krn, )
//            }
//
//        }
//
//    }


}
