package com.dev.prateekk.timeline.redundent;


import android.text.TextUtils;

import com.dev.prateekk.timeline.BuildConfig;

import java.io.Serializable;
import java.util.Arrays;

public class SDBookingData implements Serializable, Cloneable {

    public static final int DEFAULT_PASSENGER_COUNT = -1;

    public static final int DEFAULT_BOOKING_CANCEL_COUNTDOWN_TIMER = 4 * 60; // in seconds
    public static String CANCELLATION_SOURCE_DRIVER = "driver";
    public static String CANCELLATION_SOURCE_CUSTOMER = "customer";
    public long driverPickupWaitTime = -1;
    public ListViewButtonState cancelState = ListViewButtonState.UNCHECKED;
    public ListViewButtonState paymentState = ListViewButtonState.UNCHECKED;
    public long tempHardCancelTimeStamp = 0;
    public CurrentTimer currentTimerRunning = CurrentTimer.NONE;// by default none of the timers are running...
    public String sent_at;
    public String reached_at;
    public String boarded_at;
    public String completed_at;
    public String submit_invoice_at;
    public boolean isOtpValidated;
    public BookingSource mBookingSource;
    public BookingResponse mBookingResponse;
    public int retry_attempts;
    public String cancellation_source;
    public String cancellation_reason;
    public int fraud_check_count = 0;
    public boolean isClientLocateAutoNavigate = true;
    public boolean isStopTripAutoNavigate = true;
    public boolean isCustomerArrived = true;
    public String decryptedOTP;

    public boolean isBookingCurrent;
    public int customerSeatCount = DEFAULT_PASSENGER_COUNT;

    public SDBookingData() {
        mBookingResponse = new BookingResponse();
    }

    public SDBookingData(BookingResponse bookingResponse) {
        this.mBookingResponse = bookingResponse;
    }

    public static SDBookingData newInstance(BookingResponse bookingResponse) {
        if (bookingResponse != null) {
            // ShareDriverLogger.getLogger().debug("SDBookingData:newInstance - bookingData created");
            return new SDBookingData(bookingResponse);
        } else {
            // ShareDriverLogger.getLogger().debug("SDBookingData:newInstance - bookingResponse is null");
            return null;
        }
    }

    public boolean isBookingCurrent() {
        return isBookingCurrent;
    }

    public void setBookingCurrent(boolean bookingCurrent) {
        isBookingCurrent = bookingCurrent;
    }

    public int getCustomerSeatCount() {
        return customerSeatCount == DEFAULT_PASSENGER_COUNT ? mBookingResponse.getSeat_count() : customerSeatCount;
    }

    public void setCustomerSeatCount(int customerSeatCount) {
        this.customerSeatCount = customerSeatCount;
    }

    public boolean isStopTripAutoNavigate() {
        return isStopTripAutoNavigate;
    }

    public void setStopTripAutoNavigate(boolean stopTripAutoNavigate) {
        isStopTripAutoNavigate = stopTripAutoNavigate;
    }

    public boolean isClientLocateAutoNavigate() {
        return isClientLocateAutoNavigate;
    }

    public void setClientLocateAutoNavigate(boolean clientLocateAutoNavigate) {
        isClientLocateAutoNavigate = clientLocateAutoNavigate;
    }

    public boolean isCustomerArrived() {
        return isCustomerArrived;
    }

    public void setCustomerArrived(boolean customerArrived) {
        isCustomerArrived = customerArrived;
    }

    public int getBooking_cancel_countdown_time() {
        return getBookingResponse().getBooking_cancel_countdown_time();
    }

    public void setBooking_cancel_countdown_time(int booking_cancel_countdown_time) {
        getBookingResponse().setBooking_cancel_countdown_time(booking_cancel_countdown_time);
    }

    public String getKrn() {
        return this.mBookingResponse.getKrn();
    }

    public String getStatus() {
        return this.mBookingResponse.getStatus();
    }

    public String getCancellation_reason() {
        if (TextUtils.isEmpty(cancellation_reason)) {
            return "NA";
        }
        return cancellation_reason;
    }

    public void setCancellation_reason(String cancellation_reason) {
        this.cancellation_reason = cancellation_reason;
    }

    public String getCancellation_source() {
        if (CANCELLATION_SOURCE_DRIVER.equalsIgnoreCase(cancellation_source)) {
            return CANCELLATION_SOURCE_DRIVER;
        } else {
            return CANCELLATION_SOURCE_CUSTOMER;
        }
    }

    public void setCancellation_source(String cancellation_source) {
        this.cancellation_source = cancellation_source;
    }

    public int getFraud_check_count() {
        return fraud_check_count;
    }

    public void updateFraud_check_count() {
        this.fraud_check_count++;
    }

    public BookingSource getBookingSource() {
        return mBookingSource;
    }

    public void setBookingSource(BookingSource bookingSource) {
        this.mBookingSource = bookingSource;
    }

    public boolean isOtpValidated() {
        return isOtpValidated;
    }

    public void setOtpValidated(boolean otpValidated) {
        isOtpValidated = otpValidated;
    }

    public BookingResponse getBookingResponse() {
        return mBookingResponse;
    }

    public void setBookingResponse(BookingResponse bookingResponse) {
        if (bookingResponse != null) {
            this.mBookingResponse = bookingResponse;
        }
    }

    public String getDecryptedOTP() {
        if (TextUtils.isEmpty(decryptedOTP)) {
            //Decrypting first time
            // decryptedOTP = BookingManager.decryptOSN(mBookingResponse.getEnc_otp());
        }
        return decryptedOTP;
    }

    @Override
    public Object clone() {
        try {
            SDBookingData bookingData = (SDBookingData) super.clone();
            bookingData.setBookingResponse((SDBookingData.BookingResponse) bookingData.getBookingResponse().clone());
            return bookingData;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setSubmitInvoiceAt(String invoice_at) {
        this.submit_invoice_at = invoice_at;
    }

    public String getInvoiceAt() {
        return submit_invoice_at;
    }

    public String getBoarded_at() {
        return boarded_at;
    }

    public void setBoarded_at(String boarded_at) {
        this.boarded_at = boarded_at;
    }

    public String getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(String completed_at) {
        this.completed_at = completed_at;
    }

    public String getSent_at() {
        return sent_at;
    }

    public void setSent_at(String sent_at) {
        this.sent_at = sent_at;
    }

    public String getReached_at() {
        return reached_at;
    }

    public void setReached_at(String reached_at) {
        this.reached_at = reached_at;
    }

    public int getRetry_attempts() {
        return retry_attempts;
    }

    public void updateOTP_Failure_attempts() {
        this.retry_attempts++;
    }

    @Override
    public String toString() {

        if (!BuildConfig.DEBUG) {
            return "SDBookingData";
        }

        return "SDBookingData{" +
                ", driverPickupWaitTime=" + driverPickupWaitTime +
                ", cancelState=" + cancelState +
                ", paymentState=" + paymentState +
                ", sent_at='" + sent_at + '\'' +
                ", reached_at='" + reached_at + '\'' +
                ", boarded_at='" + boarded_at + '\'' +
                ", completed_at='" + completed_at + '\'' +
                ", submit_invoice_at='" + submit_invoice_at + '\'' +
                ", isOtpValidated=" + isOtpValidated +
                ", mBookingSource=" + mBookingSource +
                ", mBookingResponse=" + mBookingResponse +
                ", retry_attempts=" + retry_attempts +
                '}';
    }

    // this enum will keep track of all
    // which type of timer is running ??
    // either HardCancelTimer or HardCancelMsgTimer or none of the timer is running...
    public enum CurrentTimer {
        NONE, HARD_CANCEL_MSG, HARD_CANCEL, WAIT_FOR_BOOKING;
    }

    public enum BookingSource {
        GET_BOOKINGS,
        LONGPOLLER,
        SMS
    }

    public enum BookingFeature {

        // 1st - 0001 - (Backend Value 1)
        OSN_AUTH_CHECK(1), // 1st Bit

        // 2nd - 0010 - (Backend Value 2)
        OTP_DISTANCE_CHECK(2), // 2nd Bit

        // 3rd - 0100 - (Backend Value 4)
        HOTSPOT_CHECK(3), // 3rd Bit

        // 4th - 1000 - (Backend Value 8)
        // This is deprecated now
        SHOW_NUMBER_OF_CUSTOMERS(4),

        // 5th bit - 10000 (Backend Value 16 used for share pass)
        ENCRYPTED_OSN(6),

        // Backend value 64
        // If this is true show otp screen else osn screen
        OTP_AUTH_CHECK(7),

        // add trip features as well
        TRIP_HOTSPOT_V2_ENABLED(1),
        TRIP_HOTSPOT_V2_2_BOOKINGS(2),
        TRIP_HOTSPOT_V2_3_BOOKINGS(3);


        public int mBookingFeature;

        BookingFeature(int bookingFeature) {
            this.mBookingFeature = bookingFeature;
        }

        public int getBookingFeatureValue() {
            return mBookingFeature;
        }
    }

    public enum ListViewButtonState {
        CHECKED,
        UNCHECKED,
    }

    public static class BookingResponse implements Serializable, Cloneable {
        public BookingInvoice invoice;
        public double distance;
        public String pickup_time;
        public String krn;
        public String share_id;
        public String status;
        public int seats_selected = 1;

        // This is deprecated now, not utilised
        public boolean show_seat_list;

        public boolean is_hotspot;
        public long booking_rules;
        public CustomerInfo customer_info;
        public LoctionInfo pick_up_info;
        public LoctionInfo drop_info;
        public String hotspot_address_title;
        public long buffer_wait_time;
        public int latest_invoice_retries;
        public String enc_otp;
        public int booking_cancel_countdown_time = -1;

        public TripInfo trip_info;

        public BookingResponse() {
            customer_info = new CustomerInfo();
        }

        public static boolean checkFeature(long rule, BookingFeature feature) {
            return ((rule >> (feature.getBookingFeatureValue() - 1) & 1)) == 1 ? true : false;
        }

        public TripInfo getTrip_info() {
            return this.trip_info;
        }

        public void setTrip_info(TripInfo trip_info) {
            this.trip_info = trip_info;
        }

        public String getSentAt() {
            return null;
        }

        public int getBooking_cancel_countdown_time() {
            if (booking_cancel_countdown_time == -1) {
                booking_cancel_countdown_time = DEFAULT_BOOKING_CANCEL_COUNTDOWN_TIMER; // in seconds
            }
            return booking_cancel_countdown_time;
        }

        public void setBooking_cancel_countdown_time(int booking_cancel_countdown_time) {
            this.booking_cancel_countdown_time = booking_cancel_countdown_time;
        }

        public void createTripInfo(long tripRules, String bookingID) {
            // create trip info from trip rules...
            this.trip_info = new SDBookingData.BookingResponse.TripInfo();
            this.trip_info.setTripRules(tripRules);
            this.trip_info.setHotSpotV2(bookingID);
            this.trip_info.setBookingCount(TripInfo.DEFAULT_BOOKING_COUNT);
        }

        public String getEnc_otp() {
            return enc_otp;
        }

        public void setEnc_otp(String enc_otp) {
            this.enc_otp = enc_otp;
        }

        public BookingInvoice getInvoice() {
            return invoice;
        }

        public void setInvoice(BookingInvoice invoice) {
            this.invoice = invoice;
        }

        public String getPickup_time() {
            return pickup_time;
        }

        public void setPickup_time(String pickup_time) {
            this.pickup_time = pickup_time;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public void setBookingRules(long bookingRules) {
            this.booking_rules = bookingRules;
        }

        public String getHotspot_address_title() {
            return hotspot_address_title;
        }

        public void setHotspotAddressTitle(String hotspotAddressTitle) {
            this.hotspot_address_title = hotspotAddressTitle;
        }

        public long getBuffer_wait_time() {
            return buffer_wait_time;
        }

        public void setHotspot() {
            this.is_hotspot = true;
        }

        public void setHotspot(boolean isHotspot) {
            this.is_hotspot = isHotspot;
        }

        public void updateHotSpot(boolean value) {
            this.is_hotspot = value;
        }

        public LoctionInfo getPick_up_info() {
            return pick_up_info;
        }

        public void setPick_up_info(LoctionInfo pick_up_info) {
            this.pick_up_info = pick_up_info;
        }

        public LoctionInfo getDrop_info() {
            return drop_info;
        }

        public void setDrop_info(LoctionInfo drop_info) {
            this.drop_info = drop_info;
        }

        public CustomerInfo getCustomer_info() {
            return customer_info;
        }

        public void setCustomer_info(CustomerInfo customer_info) {
            this.customer_info = customer_info;
        }

        public String getKrn() {
            return krn;
        }

        public void setKrn(String krn) {
            this.krn = krn;
        }

        public String getShare_id() {
            return share_id;
        }

        public void setShare_id(String share_id) {
            this.share_id = share_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isFeatureEnabled(BookingFeature bookingFeature) {
            return checkFeature(booking_rules, bookingFeature);
        }

        public boolean isShow_seat_list() {
            return show_seat_list;
        }

        // This is done considering isHotspot comes standalone in Longpollor/GetBookings and in booking_rules in SMS.
        public boolean isHotSpot() {
            return is_hotspot || isFeatureEnabled(BookingFeature.HOTSPOT_CHECK);
        }

        public int getLatest_invoice_retries() {
            return latest_invoice_retries;
        }

        public void setLatest_invoice_retries(int latest_invoice_retries) {
            this.latest_invoice_retries = latest_invoice_retries;
        }

        public int getSeat_count() {
            return seats_selected;
        }

        public void setSeat_count(int seat_count) {
            this.seats_selected = seat_count;
        }

        public void updateLatestInvoiceRetries() {
            this.latest_invoice_retries++;
        }

        @Override
        public Object clone() {
            try {
                BookingResponse bookingResponse = (BookingResponse) super.clone();

                bookingResponse.setCustomer_info((CustomerInfo) bookingResponse.getCustomer_info().clone());
                bookingResponse.setInvoice((BookingInvoice) bookingResponse.getInvoice().clone());
                bookingResponse.pick_up_info = (LoctionInfo) bookingResponse.pick_up_info.clone();
                bookingResponse.drop_info = (LoctionInfo) bookingResponse.drop_info.clone();
                return bookingResponse;
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }

        public void setDecryptedOSN(String decryptedOSN) {
            this.krn = decryptedOSN;
        }

        public long getBooking_rules() {
            return booking_rules;
        }

        @Override
        public String toString() {

            if (!BuildConfig.DEBUG) {
                return "BookingResponse";
            }

            return "BookingResponse{" +
                    "invoice=" + invoice +
                    ", distance=" + distance +
                    ", pickup_time='" + pickup_time + '\'' +
                    ", krn='" + krn + '\'' +
                    ", share_id='" + share_id + '\'' +
                    ", status='" + status + '\'' +
                    ", seats_selected=" + seats_selected +
                    ", show_seat_list=" + show_seat_list +
                    ", is_hotspot=" + is_hotspot +
                    ", booking_rules=" + booking_rules +
                    ", customer_info=" + customer_info +
                    ", pick_up_info=" + pick_up_info +
                    ", drop_info=" + drop_info +
                    ", hotspot_address_title='" + hotspot_address_title + '\'' +
                    ", buffer_wait_time=" + buffer_wait_time +
                    ", latest_invoice_retries=" + latest_invoice_retries +
                    ", booking_cancel_countdown_time=" + booking_cancel_countdown_time +
                    '}';
        }

        public void setTripRules(long tripRules) {
            if (trip_info == null) {
                trip_info = new TripInfo();
            }

            this.trip_info.trip_rules = tripRules;
        }

        public static class TripInfo {
            public static final int DEFAULT_BOOKING_COUNT = 2;
            public long trip_rules;
            public HotSpotV2 hotspot_v2;

            public TripInfo() {
                hotspot_v2 = new HotSpotV2();
            }

            public HotSpotV2 getHotSpotV2() {
                return this.hotspot_v2;
            }

            public void setHotSpotV2(String bookingId) {
                this.hotspot_v2 = new HotSpotV2();
                this.hotspot_v2.bookings = new String[1];
                this.hotspot_v2.bookings[0] = bookingId;
            }

            public long getTripRules() {
                return trip_rules;
            }

            public void setTripRules(long tripRules) {
                this.trip_rules = tripRules;
            }

            public void setBookingCount(int count) {
                this.hotspot_v2.enable_st_bk_cnt = count;
            }

            @Override
            public String toString() {
                String out = "trip_rules = " + trip_rules;
                if (hotspot_v2 != null) {
                    out = out + ", hotspot_v2 = " + hotspot_v2.toString();
                }
                return out;
            }
        }

        public static class HotSpotV2 {
            public String[] bookings;
            public int enable_st_bk_cnt = TripInfo.DEFAULT_BOOKING_COUNT;

            @Override
            public String toString() {
                String out = "enable_st_bk_cnt = " + enable_st_bk_cnt;
                if (bookings != null)
                    out = out + ", " + Arrays.toString(bookings);
                return out;
            }
        }
    }

    public static class LoctionInfo implements Serializable, Cloneable {

        public String lat;
        public String lng;
        public String address;

        public LoctionInfo() {

        }

        public LoctionInfo(String lat, String lng, String address) {
            this.lat = lat;
            this.lng = lng;
            this.address = address;
        }

        public LoctionInfo(LoctionInfo locationInfo) {
            this.lat = locationInfo.lat;
            this.lng = locationInfo.lng;
            this.address = locationInfo.address;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }

        }

        @Override
        public String toString() {

            if (!BuildConfig.DEBUG) {
                return "LocationInfo";
            }

            return "LoctionInfo{" +
                    "lat='" + lat + '\'' +
                    ", lng='" + lng + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    public static class CustomerInfo implements Serializable, Cloneable {

        public String name;
        public String phone_no;
        public String user_id;

        public CustomerInfo() {

        }

        public CustomerInfo(String name, String phone_no, String user_id) {
            this.name = name;
            this.phone_no = phone_no;
            this.user_id = user_id;
        }

        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }

        @Override
        public String toString() {

            if (!BuildConfig.DEBUG) {
                return "CustomerInfo";
            }

            return "CustomerInfo{" +
                    "name='" + name + '\'' +
                    ", phone_no='" + phone_no + '\'' +
                    ", user_id='" + user_id + '\'' +
                    '}';
        }
    }

    public static class BookingInvoice implements Serializable, Cloneable {
        public long krn;
        public double total_bill;
        public double deductible_ola_money;
        public double cash_payable;
        public double fare;
        public double cancellation_chgs;

        public BookingInvoice() {

        }

        public BookingInvoice(BookingInvoice bookingInvoice) {
            this.total_bill = bookingInvoice.total_bill;
            this.deductible_ola_money = bookingInvoice.deductible_ola_money;
            this.cash_payable = bookingInvoice.cash_payable;
            this.fare = bookingInvoice.fare;
            this.cancellation_chgs = bookingInvoice.cancellation_chgs;
            this.krn = bookingInvoice.krn;
        }

        public BookingInvoice(double cash_payable) {
            this.cash_payable = cash_payable;
        }

        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }

        }

        @Override
        public String toString() {

            if (!BuildConfig.DEBUG) {
                return "BookingInvoice";
            }

            return "BookingInvoice{" +
                    "krn=" + krn +
                    ", total_bill=" + total_bill +
                    ", deductible_ola_money=" + deductible_ola_money +
                    ", cash_payable=" + cash_payable +
                    ", fare=" + fare +
                    ", cancellation_chgs=" + cancellation_chgs +
                    '}';
        }
    }
}