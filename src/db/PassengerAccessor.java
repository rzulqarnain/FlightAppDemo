
package db;


import static db.AirportAccessor.findAirports;
import static db.PassengerAccessor.findPassengers;
import entity.Airport;
import entity.Passenger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Passenger;




public class PassengerAccessor {
    private static final String sqlSelectAll = "select * from Passenger order by PASSPORTNUMBER";
    private static final String sqlInsert = "insert into Passenger values(?,?,?,?)";
    private static final String sqlDelete = "delete from Passenger where PASSPORTNUMBER = ?";
    private static final String sqlUpdate = "update Passenger set PASSPORTCOUNTRY = ?, FAMILYNAME = ?, GIVENNAMES = ? where PASSPORTNUMBER = ?";


    private PassengerAccessor() {}
     /**
     * Retrieves all airports from the database. 
     * An empty list is returned if a connection error occurs.
     *
     * @return a list of airports
     */
    public static ArrayList<Passenger> findAllPassengers() {
        return findPassengers(sqlSelectAll);
    }
    
    // end findAll

    /**
     * Retrieves all airports that match the specified query.
     * An empty list is returned if a connection error occurs.
     * 
     * @param query the query used to select airports
     * @return a list of airports
     */
    public static ArrayList<Passenger> findPassengers(String query) {
        ArrayList<Passenger> pas = new ArrayList();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next() == true) {
                String FamilyName = rs.getString("FAMILYNAME");
                String GivenName = rs.getString("GIVENNAMES");
                String PasportNo = rs.getString("PASSPORTNUMBER");
                String PassengerCountry = rs.getString("PASSPORTCOUNTRY");
                Passenger item = new Passenger(FamilyName,GivenName,PasportNo,PassengerCountry);
                pas.add(item);
            }

        }
        catch (SQLException e) {
            pas = new ArrayList();
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
        return pas;
    }
    
    /**
     * Retrieves the airport from the database with the specified code.
     * 
     * @param code the code to match
     * @return the airport object with the specified code, or null if no such airport exists
     */
    public static Passenger findPassengerByName(String code) {
        Passenger res = null;
        List<Passenger> passengers = findPassengers("select * from Passenger where PASSPORTNUMBER='" + code + "'");
        if (passengers.size() == 1) {
            res = passengers.get(0);
        }
        return res;
    }

    /**
     * Helper method for create, update, and delete.
     *
     * @param passenger
     * @return <var>true</var> if the airport is in the database; <var>false</var> otherwise
     */
    private static boolean passengerExists(Passenger passenger) {
        return findPassengerByName(passenger.getPasportNo()) != null;
    }

    /**
     * Deletes a record from the Airport table.
     *
     * @param item the record to delete
     * @return <var>true</var> if the item was deleted, <var>false</var>
     * otherwise
     */
    public static boolean deletePassenger(Passenger item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (!passengerExists(item)) {
            return false;
        }
//private static final String sqlDelete = "delete from Passenger where PASSPORTNUMBER = ?";
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlDelete);
            ps.setString(1, item.getPasportNo());

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
    public static boolean addPassenger(Passenger item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (passengerExists(item)) {
            return false;
        }

        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlInsert);

            String GivenName = item.getGivenName();
            String FamilyName = item.getFamilyName();
            String country = item.getPassengerCountry();
            String PassportNo = item.getPasportNo();
            
            ps.setString(1, GivenName);
            ps.setString(2, FamilyName);
            ps.setString(3, country);
            ps.setString(4, PassportNo);

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
    public static boolean updatePassenger(Passenger item) {
        boolean res = false;
        Connection conn = null;
        PreparedStatement ps = null;

        if (!passengerExists(item)) {
            return false;
        }

        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement(sqlUpdate);

            
            String GivenName = item.getGivenName();
            String FamilyName = item.getFamilyName();
            String country = item.getPassengerCountry();
            String PassportNo = item.getPasportNo();
            
            ps.setString(4, GivenName);
            ps.setString(1, FamilyName);
            ps.setString(2, country);
            ps.setString(3, PassportNo);
            
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




