package utils;

import java.util.Calendar;

/**
 * Some helper methods for CP1953 project.
 *
 * @author smonk
 * @version 24-Sep-2015
 */
public class Helpers {

    /**
     * Formats a date and time in the format "EEE, dd-mm-yyyy at hh:MM"
     * where EEE is the three-letter day of week.
     * Cannot use a DateFormat object to do this because it is not 
     * time zone aware.
     * 
     * @param cal a Calendar object containing the date and time.
     * @return a string containing this calendar object's date and time information.
     */
    public static String formatDateTime(Calendar cal) {
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        String res = FlightAppConstants.WEEKDAYS[cal.get(Calendar.DAY_OF_WEEK) - 1] + ", ";
        res += dom < 10 ? "0" + dom : "" + dom;
        res += "-";
        res += month < 10 ? "0" + month : "" + month;
        res += "-";
        res += year + " at ";
        res += hour < 10 ? "0" + hour : "" + hour;
        res += ":";
        res += minute < 10 ? "0" + minute : "" + minute;
        return res;
    }

    /**
     * Formats a number of minutes as "hh:mm" - for example,
     * 365 minutes becomes "6:05".
     * 
     * @param minutes the number of minute to format
     * @return a string containing the minutes formatted as hours and minutes.
     */
    public static String formatHoursMinutes(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        String res = h + ":";
        res += m < 10 ? "0" + m : "" + m;
        return res;
    }

} // end class Helpers
