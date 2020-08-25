package db;

import entity.Aircraft;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to the Aircraft database table.
 * 
 * @author smonk
 * @version 18-Oct-2015
 */
public class AircraftAccessor {

    private static final String sqlSelectAll = "select * from AIRCRAFT order by AIRCRAFTID";
    private static final String sqlInsert = "insert into AIRCRAFT values(?,?,?,?,?)";
    private static final String sqlDelete = "delete from AIRCRAFT where AIRCRAFTID = ?";
    private static final String sqlUpdate = "update AIRCRAFT set MODEL = ?, ECONOMYSEATS = ?, BUSINESSSEATS = ?, FIRSTSEATS = ? where AIRCRAFTID = ?";

    private AircraftAccessor() {
    } // no instantiation

    /**
     * Retrieves all aircraft from the database. 
     * An empty list is returned if a connection error occurs.
     *
     * @return a list of aircraft
     */
    public static ArrayList<Aircraft> findAllAircraft() {
        return findAircraft(sqlSelectAll);
    } // end findAll

    /**
     * Retrieves all aircraft that match the specified query.
     * An empty list is returned if a connection error occurs.
     * 
     * @param query the query used to select aircraft
     * @return a list of aircraft
     */
    public static ArrayList<Aircraft> findAircraft(String query) {
        ArrayList<Aircraft> res = new ArrayList();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                String aircraftID = rs.getString("AIRCRAFTID");
                String model = rs.getString("MODEL");
                int economySeats = rs.getInt("ECONOMYSEATS");
                int businessSeats = rs.getInt("BUSINESSSEATS");
                int firstSeats = rs.getInt("FIRSTSEATS");
                Aircraft item = new Aircraft(aircraftID, model, economySeats, businessSeats, firstSeats);
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
     * Retrieves the aircraft from the database with the specified ID.
     * 
     * @param id the ID to match
     * @return the aircraft object with the specified ID, or null if no such aircraft exists
     */
    public static Aircraft findAircraftByID(int id) {
        Aircraft res = null;
        List<Aircraft> aircraft = findAircraft("select * from AIRCRAFT where AIRCRAFTID=" + id);
        if (aircraft.size() == 1) {
            res = aircraft.get(0);
        }
        return res;
    }

    /**
     * Helper method for create, update, and delete.
     *
     * @param aircraft
     * @return <var>true</var> if the aircraft is in the database; <var>false</var> otherwise
     */
    private static boolean aircraftExists(Aircraft aircraft) {
        return findAircraftByID(Integer.parseInt(aircraft.getAircraftID())) != null;
    }

    /**
     * Deletes a record from the Aircraft table.
     *
     * @param item the record to delete
     * @return <var>true</var> if the item was deleted, <var>false</var>
     * otherwise
     */
    public static boolean deleteAircraft(Aircraft item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (!aircraftExists(item)) {
            return false;
        }

        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlDelete);
            ps.setString(1, item.getAircraftID());

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
     * Adds a record to the Aircraft table.
     * 
     * @param item the item to add
     * @return <var>true</var> if the item was added, <var>false</var> otherwise
     */
    public static boolean addAircraft(Aircraft item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (aircraftExists(item)) {
            return false;
        }

        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlInsert);

            String id = item.getAircraftID();
            String model = item.getModel();
            int economySeats = item.getEconomySeats();
            int businessSeats = item.getBusinessSeats();
            int firstSeats = item.getFirstSeats();

            ps.setString(1, id);
            ps.setString(2, model);
            ps.setInt(3, economySeats);
            ps.setInt(4, businessSeats);
            ps.setInt(5, firstSeats);

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
     * Updates a record in the Aircraft table.
     * 
     * @param item the item to update
     * @return <var>true</var> if the item was updated, <var>false</var> otherwise
     */
    public static boolean updateAircraft(Aircraft item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (!aircraftExists(item)) {
            return false;
        }

        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlUpdate);

            String id = item.getAircraftID();
            String model = item.getModel();
            int economySeats = item.getEconomySeats();
            int businessSeats = item.getBusinessSeats();
            int firstSeats = item.getFirstSeats();

            ps.setString(5, id);
            ps.setString(1, model);
            ps.setInt(2, economySeats);
            ps.setInt(3, businessSeats);
            ps.setInt(4, firstSeats);

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

} // end class AircraftAccessor
