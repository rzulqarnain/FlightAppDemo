package utils;

import entity.Flight;
import java.util.Comparator;

/**
 * A comparator to allow sorting flights by departure times. All flights
 * should have the same departure date.
 *
 * @author smonk
 * @version 24-Sep-2015
 */
public class DepartureTimeComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        int res;
        Flight f1 = (Flight) o1;
        Flight f2 = (Flight) o2;
        if (f1.getDepartureDate().before(f2.getDepartureDate())) {
            res = -1;
        } else if (f1.getDepartureDate().after(f2.getDepartureDate())) {
            res = 1;
        } 
        else { // same departure date
            res = f1.getDepartureTime() - f2.getDepartureTime();
        }

        return res;
    }

} // end class DepartureTimeComparator
