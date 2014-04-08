/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import static easychef.data.Constants.DB_URL;
import static easychef.data.Constants.DB_NAME;
import static easychef.data.Constants.DB_PASSWORD;
import static easychef.data.Constants.DB_USER;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tumee
 */
public final class DBConnector {

    public static Connection connection;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet rs;

    public DBConnector() {
        try {
            connection = getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Create new connection to Database using Constants
     *
     * @return <Connection>
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        } else {
            connection = (Connection) DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
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

    /**
     * Executes given prepared statement for <ResultSet>
     *
     * @param pStat <PreparedStatement> to be executed against connection
     * @return <ResultSet>
     * @throws SQLException
     */
    public ResultSet getResultSet(PreparedStatement pStat) throws SQLException {
        System.out.println("Before execute pStat.");
        rs = pStat.executeQuery();
        return rs;
    }

    /**
     * Executes given query String for <ResultSet>
     *
     * @param query to be executed
     * @return <ResultSet>
     * @throws SQLException
     */
    public ResultSet getResultSet(String query) throws SQLException {
        statement = connection.createStatement();
        rs = statement.executeQuery(query);
        return rs;
    }

    /**
     * Execute prepared statement for UPDATE
     *
     * @param pStat <PreparedStatement> to be executed
     * @return (1) number of updated rows (2) 0 if not DML query
     *
     * @throws SQLException
     */
    public int update(PreparedStatement pStat) throws SQLException {
        int affectedRows = pStat.executeUpdate();
        return affectedRows;
    }

    /**
     * Execute given update String for UPDATE
     *
     * @param query to be executed
     * @return (1) number of updated rows (2) 0 if not DML query
     * @throws SQLException
     */
    public int update(String query) throws SQLException {
        statement = connection.createStatement();
        int affectedRows = statement.executeUpdate(query);
        return affectedRows;
    }

}
