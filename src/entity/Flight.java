package entity;

import utils.Helpers;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Flight {

    private int flightID;
    private String flightNumber;
    private Airport origin;
    private Airport destination;
    private Date departureDate;
    private int departureTime;
    private int duration;
    private ArrayList<Passenger> manifest;
    private Aircraft aircraft;
    private int economySeatsAvailable;
    private int businessSeatsAvailable;
    private int firstSeatsAvailable;

    private Calendar departureCal;
    private Calendar arrivalCal;

    public Flight(int flightID, String flightNumber, Airport origin, Airport destination, Date departureDate, int departureTime, int duration, Aircraft aircraft, int economySeats, int businessSeats, int firstSeats) {
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.duration = duration;
        this.aircraft = aircraft;
        this.economySeatsAvailable = economySeats;
        this.businessSeatsAvailable = businessSeats;
        this.firstSeatsAvailable = firstSeats;
        this.manifest = new ArrayList();

        departureCal = Calendar.getInstance(TimeZone.getTimeZone(origin.getTimeZone()));
        departureCal.setTime(departureDate);
        departureCal.set(Calendar.HOUR_OF_DAY, departureTime / 100);
        departureCal.set(Calendar.MINUTE, departureTime % 100);

        arrivalCal = (Calendar) departureCal.clone();
        arrivalCal.add(Calendar.MINUTE, duration);
        arrivalCal.setTimeZone(TimeZone.getTimeZone(destination.getTimeZone()));
    }

    public int getFlightID() {
        return flightID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }
    
    public int getEconomySeatsAvailable() {
        return economySeatsAvailable;
    }

    public int getBusinessSeatsAvailable() {
        return businessSeatsAvailable;
    }

    public int getFirstSeatsAvailable() {
        return firstSeatsAvailable;
    }

    public ArrayList<Passenger> getManifest() {
        return manifest;
    }

    public void reserveEconomySeats(int num) {
        if (num >= 0 && num < economySeatsAvailable) {
            economySeatsAvailable -= num;
        }
    }

    public void reserveBusinessSeats(int num) {
        if (num >= 0 && num < businessSeatsAvailable) {
            businessSeatsAvailable -= num;
        }
    }

    public void reserveFirstSeats(int num) {
        if (num >= 0 && num < firstSeatsAvailable) {
            firstSeatsAvailable -= num;
        }
    }

    @Override
    public String toString() {
        String res = flightNumber + " | ";
        res += origin.getCode() + " - " + destination.getCode() + " | ";
        res += " departing on " + Helpers.formatDateTime(departureCal) + " | ";
        res += " arriving on " + Helpers.formatDateTime(arrivalCal) + " | ";
        res += "duration - " + Helpers.formatHoursMinutes(duration);
        return res;
    }

} // end class Flight
