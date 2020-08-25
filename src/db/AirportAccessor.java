package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Airport;

/**
 * Provides access to the Airport database table.
 * 
 * @author smonk
 * @version 18-Oct-2015
 */
public class AirportAccessor {

    private static final String sqlSelectAll = "select * from AIRPORT order by CODE";
    private static final String sqlInsert = "insert into AIRPORT values(?,?,?,?)";
    private static final String sqlDelete = "delete from AIRPORT where CODE = ?";
    private static final String sqlUpdate = "update AIRPORT set CITY = ?, COUNTRY = ?, TIMEZONE = ? where CODE = ?";

    private AirportAccessor() {} // no instantiation

    /**
     * Retrieves all airports from the database. 
     * An empty list is returned if a connection error occurs.
     *
     * @return a list of airports
     */
    public static ArrayList<Airport> findAllAirports() {
        return findAirports(sqlSelectAll);
    } // end findAll

    /**
     * Retrieves all airports that match the specified query.
     * An empty list is returned if a connection error occurs.
     * 
     * @param query the query used to select airports
     * @return a list of airports
     */
    public static ArrayList<Airport> findAirports(String query) {
        ArrayList<Airport> res = new ArrayList();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                String code = rs.getString("CODE");
                String city = rs.getString("CITY");
                String country = rs.getString("COUNTRY");
                String timezone = rs.getString("TIMEZONE");
                Airport item = new Airport(code,city,country,timezone);
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
     * Retrieves the airport from the database with the specified code.
     * 
     * @param code the code to match
     * @return the airport object with the specified code, or null if no such airport exists
     */
    public static Airport findAirportByCode(String code) {
        Airport res = null;
        List<Airport> airports = findAirports("select * from AIRPORT where CODE='" + code + "'");
        if (airports.size() == 1) {
            res = airports.get(0);
        }
        return res;
    }

    /**
     * Helper method for create, update, and delete.
     *
     * @param airport
     * @return <var>true</var> if the airport is in the database; <var>false</var> otherwise
     */
    private static boolean airportExists(Airport airport) {
        return findAirportByCode(airport.getCode()) != null;
    }

    /**
     * Deletes a record from the Airport table.
     *
     * @param item the record to delete
     * @return <var>true</var> if the item was deleted, <var>false</var>
     * otherwise
     */
    public static boolean deleteAirport(Airport item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (!airportExists(item)) {
            return false;
        }

        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlDelete);
            ps.setString(1, item.getCode());

            ps.execute();
            res = true;
        }
        catch (SQLException e) {
            res = false;
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
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
    } // end remove

    /**
     * Adds a record to the Airport table.
     * 
     * @param item the item to add
     * @return <var>true</var> if the item was added, <var>false</var> otherwise
     */
    public static boolean addAirport(Airport item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (airportExists(item)) {
            return false;
        }

        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlInsert);

            String code = item.getCode();
            String city = item.getCity();
            String country = item.getCountry();
            String timezone = item.getTimeZone();
            
            ps.setString(1, code);
            ps.setString(2, city);
            ps.setString(3, country);
            ps.setString(4, timezone);

            ps.execute();
            res = true;
        }
        catch (SQLException e) {
            res = false;
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
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
    } // end add

    /**
     * Updates a record in the Airport table.
     * 
     * @param item the item to update
     * @return <var>true</var> if the item was updated, <var>false</var> otherwise
     */
    public static boolean updateAirport(Airport item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (!airportExists(item)) {
            return false;
        }

        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlUpdate);

            String code = item.getCode();
            String city = item.getCity();
            String country = item.getCountry();
            String timezone = item.getTimeZone();
            
            ps.setString(4, code);
            ps.setString(1, city);
            ps.setString(2, country);
            ps.setString(3, timezone);
            
            ps.execute();
            res = true;
        }
        catch (SQLException e) {
            res = false;
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
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
    } // end update

} // end class AirportAccessor
