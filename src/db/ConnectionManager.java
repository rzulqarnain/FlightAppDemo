package db;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDataSource;

/**
 * Manage connections to the FlightApp database.
 *
 * @author smonk
 * @version 18-Oct-2015
 */
public class ConnectionManager {

    private static ClientDataSource ds = null;
    private static final String server = "localhost";
    private static final int port = 1527;
    private static final String dbname = "FlightApp";
    private static final String username = "app";
    private static final String password = "app";

    private ConnectionManager() {
    } // no instantiation

    /**
     * Gets a connection to the FlightApp database.
     *
     * @return a connection to the FlightApp database
     * @throws SQLException if the connection fails
     */
    public static Connection getConnection() throws SQLException {
        initDataSource();
        Connection conn = ds.getConnection();
        return conn;
    }

    /**
     * Tests the connection to the FlightApp database.
     * 
     * @return <var>true</var> if the connection is OK; <var>false</var> otherwise 
     */
    public static boolean testConnection() {
        boolean res;

        try {
            Connection conn = getConnection();
            res = conn != null;
        } catch (SQLException ex) {
            res = false;
        }

        return res;
    }

    private static void initDataSource() {
        if (isNewSource()) {
            ds = new ClientDataSource();
            ds.setServerName(server);
            ds.setPortNumber(port);
            ds.setDatabaseName(dbname);
            ds.setUser(username);
            ds.setPassword(password);
        }
    }

    private static boolean isNewSource() {
        return (ds == null)
                || (!server.equals(ds.getServerName()))
                || (port != ds.getPortNumber())
                || (!dbname.equals(ds.getDatabaseName()))
                || (!username.equals(ds.getUser()))
                || (!password.equals(ds.getPassword()));
    }

} // end class ConnectionManager
