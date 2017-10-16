package se.miun.antonsskafferi;

/**
 * Created by Wictor on 2017-10-15.
 */

public class BookedEvents {

    private String startDate;
    private String endDate;
    private String eventName;
    private String bookingQuantity;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getBookingQuantity() {
        return bookingQuantity;
    }

    public void setBookingQuantity(String bookingQuantity) {
        this.bookingQuantity = bookingQuantity;
    }
}
