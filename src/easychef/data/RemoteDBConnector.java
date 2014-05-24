/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import static easychef.data.Constants.DB_PASSWORD;
import static easychef.data.Constants.DB_USER;
import static easychef.data.Constants.Remote_DB_NAME;
import static easychef.data.Constants.Remote_DB_URL;
import static easychef.data.Constants.USE_UTF8;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author tumee
 */
public class RemoteDBConnector {

    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    public static Connection connection;

    /**
     * Empty constructor creates initial connection
     *
     * @throws java.sql.SQLException
     */
    public RemoteDBConnector() throws SQLException {
        connection = getConnection();
    }

    /**
     * Create new connection to Database using Constants
     *
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (connection != null) {
            logger.info(String.format("Remote connection is not NULL returning instance : %s", connection.toString()));
            return connection;
        } else {
            String URL = String.format("%s%s?%s", Remote_DB_URL, Remote_DB_NAME, USE_UTF8);
            System.out.println(URL);
            connection = (Connection) DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            logger.info(String.format("Remote connection is NULL creating new connection. Returning new connection: %s", connection.toString()));
            return connection;
        }
    }

    /**
     * Closes connection if it's still open
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

}
