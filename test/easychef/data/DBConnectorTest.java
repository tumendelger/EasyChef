/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package easychef.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author tumee
 */
public class DBConnectorTest {
    
    public DBConnectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class DBConnector.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetConnection() throws SQLException {
        System.out.println("getConnection");
        // Should return same connection for every instances
        Connection expResult = DBConnector.getConnection();
        System.out.println(String.format("Connection 1: %s", expResult.toString()));
        Connection result = DBConnector.getConnection();
        System.out.println(String.format("Connection 2: %s", result.toString()));
        assertEquals(expResult, result);
    }    

    /**
     * Test of closeConnection method, of class DBConnector.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCloseConnection() throws SQLException {
        System.out.println("closeConnection");
        Connection conn = DBConnector.getConnection();
        DBConnector.closeConnection();
        assertEquals(true, DBConnector.connection.isClosed());
    }

    /**
     * Test of getResultSet method, of class DBConnector.
     */
    @Test
    @Ignore
    public void testGetResultSet_PreparedStatement() throws Exception {
        System.out.println("getResultSet");
        PreparedStatement pStat = null;
        DBConnector instance = new DBConnector();
        ResultSet expResult = null;
        ResultSet result = instance.getResultSet(pStat);
        assertEquals(expResult, result);
    }

    /**
     * Test of getResultSet method, of class DBConnector.
     */
    @Test
    @Ignore
    public void testGetResultSet_String() throws Exception {
        System.out.println("getResultSet");
        String query = "";
        DBConnector instance = new DBConnector();
        ResultSet expResult = null;
        ResultSet result = instance.getResultSet(query);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class DBConnector.
     */
    @Test
    @Ignore
    public void testUpdate_PreparedStatement() throws Exception {
        System.out.println("update");
        PreparedStatement pStat = null;
        DBConnector instance = new DBConnector();
        int expResult = 0;
        int result = instance.update(pStat);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class DBConnector.
     */
    @Test
    @Ignore
    public void testUpdate_String() throws Exception {
        System.out.println("update");
        String query = "";
        DBConnector instance = new DBConnector();
        int expResult = 0;
        int result = instance.update(query);
        assertEquals(expResult, result);
    }
    
}
