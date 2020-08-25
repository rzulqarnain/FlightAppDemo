package entity;

public class Airport {

    private String code;
    private String city;
    private String country;
    private String timeZone;

    /**
     * Creates a new airport object.
     * 
     * @param code three-letter airport code
     * @param city 
     * @param country
     * @param timeZone a valid Java time zone ID (see TimeZone class.) 
     */
    public Airport(String code, String city, String country, String timeZone) {
        this.code = code;
        this.city = city;
        this.country = country;
        this.timeZone = timeZone;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return code + " (" + city + ", " + country + ')';
    }

} // end class Airport
