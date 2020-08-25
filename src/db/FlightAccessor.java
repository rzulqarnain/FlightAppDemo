package db;

import entity.Aircraft;
import entity.Airport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entity.Flight;
import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

public class FlightAccessor {

    private static final String sqlSelectAll = "select * from FLIGHT";
    private static final String sqlOrigin = "select airportcode from FLIGHTAIRPORT where FLIGHTID = ? and LOCATIONTYPE = 'O'";
    private static final String sqlDestination = "select airportcode from FLIGHTAIRPORT where FLIGHTID = ? and LOCATIONTYPE = 'D'";

    private FlightAccessor() {
    } // no instantiation

    public static ArrayList<Flight> search(String originCode, String destinationCode, String departureDateStr) {
        ArrayList<Flight> res = new ArrayList();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        Airport origin = AirportAccessor.findAirportByCode(originCode);
        Airport destination = AirportAccessor.findAirportByCode(destinationCode);
        String[] dateTokens = departureDateStr.split("-");
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone(origin.getTimeZone()));
        c.set(Integer.parseInt(dateTokens[0]), Integer.parseInt(dateTokens[1]) - 1, Integer.parseInt(dateTokens[2]));
        java.util.Date realDate = c.getTime();

        try {
            conn = ConnectionManager.getConnection();
            st = conn.createStatement();
            String query = "select * from FLIGHT where FLIGHTID in "
                    + "(select f.FLIGHTID from FLIGHT f "
                    + "join FLIGHTAIRPORT fa1 on f.FLIGHTID=fa1.FLIGHTID and fa1.AIRPORTCODE='" + originCode + "' and fa1.LOCATIONTYPE='O' "
                    + "join FLIGHTAIRPORT fa2 on f.FLIGHTID=fa2.FLIGHTID and fa2.AIRPORTCODE='" + destinationCode + "' and fa2.LOCATIONTYPE='D' "
                    + "where f.DEPARTUREDATE='" + departureDateStr + "')";
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                int flightID = rs.getInt("FLIGHTID");
                String flightNumber = rs.getString("FLIGHTNUMBER");
                int departureTime = rs.getInt("DEPARTURETIME");
                int duration = rs.getInt("DURATION");
                Aircraft aircraft = AircraftAccessor.findAircraftByID(rs.getInt("AIRCRAFTID"));
                int economySeats = rs.getInt("ECONOMYSEATSAVAILABLE");
                int businessSeats = rs.getInt("BUSINESSSEATSAVAILABLE");
                int firstSeats = rs.getInt("FIRSTSEATSAVAILABLE");
                Flight item = new Flight(flightID, flightNumber, origin, destination, realDate, departureTime, duration, aircraft, economySeats, businessSeats, firstSeats);
                res.add(item);
            }
        }
        catch (SQLException e) {
            res = new ArrayList();
            System.err.println(e);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex) {
                System.err.println("Could not close resources!");
            }

        }
        return res;
    } // end find

    /**
     * Retrieves all flights from the database. An empty list is returned if a
     * connection error occurs.
     *
     * @return a list of flights
     */
    public static ArrayList<Flight> findAllFlights() {
        ArrayList<Flight> res = new ArrayList();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet rsfa = null;
        PreparedStatement originStatement;
        PreparedStatement destinationStatement;

        try {
            conn = ConnectionManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sqlSelectAll);
            originStatement = conn.prepareStatement(sqlOrigin);
            destinationStatement = conn.prepareStatement(sqlDestination);

            while (rs.next() == true) {
                int flightID = rs.getInt("FLIGHTID");
                String flightNumber = rs.getString("FLIGHTNUMBER");
                originStatement.setInt(1, flightID);
                destinationStatement.setInt(1, flightID);
                rsfa = originStatement.executeQuery();
                rsfa.next();
                String originCode = rsfa.getString("AIRPORTCODE");
                Airport origin = AirportAccessor.findAirportByCode(originCode);
                rsfa = destinationStatement.executeQuery();
                rsfa.next();
                String destinationCode = rsfa.getString("AIRPORTCODE");
                Airport destination = AirportAccessor.findAirportByCode(destinationCode);
                Date departureDate = rs.getDate("DEPARTUREDATE");
                java.util.Date realDate = new java.util.Date(departureDate.getTime());
                int departureTime = rs.getInt("DEPARTURETIME");
                int duration = rs.getInt("DURATION");
                Aircraft aircraft = AircraftAccessor.findAircraftByID(rs.getInt("AIRCRAFTID"));
                int economySeats = rs.getInt("ECONOMYSEATSAVAILABLE");
                int businessSeats = rs.getInt("BUSINESSSEATSAVAILABLE");
                int firstSeats = rs.getInt("FIRSTSEATSAVAILABLE");
                Flight item = new Flight(flightID, flightNumber, origin, destination, realDate, departureTime, duration, aircraft, economySeats, businessSeats, firstSeats);
                res.add(item);
            }

        }
        catch (SQLException e) {
            res = new ArrayList();
            System.err.println(e);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex) {
                System.err.println("Could not close resources!");
            }

        }
        return res;
    } // end findAll

    public static Flight findFlightByID(int id) {
        ArrayList<Flight> flights = new ArrayList();
        Flight res = null;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet rsfa = null;
        PreparedStatement originStatement;
        PreparedStatement destinationStatement;

        try {
            conn = ConnectionManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from FLIGHT where FLIGHTID=" + id);
            originStatement = conn.prepareStatement(sqlOrigin);
            destinationStatement = conn.prepareStatement(sqlDestination);

            while (rs.next() == true) {
                int flightID = rs.getInt("FLIGHTID");
                String flightNumber = rs.getString("FLIGHTNUMBER");
                originStatement.setInt(1, flightID);
                destinationStatement.setInt(1, flightID);
                rsfa = originStatement.executeQuery();
                rsfa.next();
                String originCode = rsfa.getString("AIRPORTCODE");
                Airport origin = AirportAccessor.findAirportByCode(originCode);
                rsfa = destinationStatement.executeQuery();
                rsfa.next();
                String destinationCode = rsfa.getString("AIRPORTCODE");
                Airport destination = AirportAccessor.findAirportByCode(destinationCode);
                Date departureDate = rs.getDate("DEPARTUREDATE");
                java.util.Date realDate = new java.util.Date(departureDate.getTime());
                int departureTime = rs.getInt("DEPARTURETIME");
                int duration = rs.getInt("DURATION");
                Aircraft aircraft = AircraftAccessor.findAircraftByID(rs.getInt("AIRCRAFTID"));
                int economySeats = rs.getInt("ECONOMYSEATSAVAILABLE");
                int businessSeats = rs.getInt("BUSINESSSEATSAVAILABLE");
                int firstSeats = rs.getInt("FIRSTSEATSAVAILABLE");
                Flight item = new Flight(flightID, flightNumber, origin, destination, realDate, departureTime, duration, aircraft, economySeats, businessSeats, firstSeats);
                flights.add(item);
            }

        }
        catch (SQLException e) {
            flights = new ArrayList();
            System.err.println(e);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex) {
                System.err.println("Could not close resources!");
            }

        }
        if (flights.size() == 1) {
            res = flights.get(0);
        }
        return res;
    }

} // end class FlightAccessor
