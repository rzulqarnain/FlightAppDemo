package entity;

import java.util.ArrayList;

public class Booking {

    private int bookingID;
    private int bookingType;
    private int bookingClass;
    private ArrayList<Passenger> passengers;
    private Flight outboundFlight;
    private Flight inboundFlight;
    private Payment payment;

    public Booking(int bookingID, int bookingType, int bookingClass, ArrayList<Passenger> passengers, Flight outboundFlight, Flight inboundFlight, Payment payment) {
        this.bookingID = bookingID;
        this.bookingType = bookingType;
        this.bookingClass = bookingClass;
        this.passengers = passengers;
        this.outboundFlight=outboundFlight;
        this.inboundFlight=inboundFlight;
        this.payment = payment;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getBookingType() {
        return bookingType;
    }

    public int getBookingClass() {
        return bookingClass;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public Flight getOutboundFlight() {
        return outboundFlight;
    }
    
    public Flight getInboundFlight() {
        return inboundFlight;
    }
    
    public Payment getPayment() {
        return payment;
    }
    
} // end class Booking
