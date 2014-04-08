/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import easychef.data.exceptions.UserNotFoundException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class UserTest {

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Before class");
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
     * Test of getUserDetails method, of class User.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetUserDetails() throws SQLException {
        System.out.println("getUserDetails");
        User instance = new User("Tumee");
        boolean expected = true;
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        User instance = new User();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class User.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        User instance = new User();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        int newId = instance.getId();
        assertEquals(id, newId);
    }

    /**
     * Test of getUname method, of class User.
     */
    @Test
    public void testGetUname() {
        System.out.println("getUname");
        User instance = new User();
        String expResult = "";
        String result = instance.getUname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUname method, of class User.
     */
    @Test
    public void testSetUname() {
        System.out.println("setUname");
        String expected = "user";
        User instance = new User();
        instance.setUname(expected);
        String result = instance.getUname();
        assertEquals(expected, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        User instance = new User();
        instance.setPassword(password);
        String result = instance.getPassword();
        assertEquals(password, result);
    }

    /**
     * Test of getFirstname method, of class User.
     */
    @Test
    public void testGetFirstname() {
        System.out.println("getFirstname");
        User instance = new User();
        String expResult = "";
        String result = instance.getFirstname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstname method, of class User.
     */
    @Test
    public void testSetFirstname() {
        System.out.println("setFirstname");
        String firstname = "firstname";
        User instance = new User();
        instance.setFirstname(firstname);
        String result = instance.getFirstname();
        assertEquals(firstname, result);
    }

    /**
     * Test of getLastname method, of class User.
     */
    @Test
    public void testGetLastname() {
        System.out.println("getLastname");
        User instance = new User();
        String expResult = "";
        String result = instance.getLastname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastname method, of class User.
     */
    @Test
    public void testSetLastname() {
        System.out.println("setLastname");
        String lastname = "lastname";
        User instance = new User();
        instance.setLastname(lastname);
        String result = instance.getLastname();
        assertEquals(lastname, result);
    }

    /**
     * Test of isIsActive method, of class User.
     */
    @Test
    public void testIsIsActive() {
        System.out.println("isIsActive");
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.isIsActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsActive method, of class User.
     */
    @Test
    public void testSetIsActive() {
        System.out.println("setIsActive");
        boolean isActive = true;
        User instance = new User();
        instance.setIsActive(isActive);
        boolean result = instance.isIsActive();
        assertEquals(isActive, result);
    }

    /**
     * Test of getRoleid method, of class User.
     */
    @Test
    public void testGetRoleid() {
        System.out.println("getRoleid");
        User instance = new User();
        int expResult = 0;
        int result = instance.getRoleid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRoleid method, of class User.
     */
    @Test
    public void testSetRoleid() {
        System.out.println("setRoleid");
        int roleid = 12;
        User instance = new User();
        instance.setRoleid(roleid);
        int result = instance.getRoleid();
        assertEquals(roleid, result);
    }

    /**
     * Test of isUserFound method, of class User.
     *
     * @throws java.sql.SQLException
     * @throws easychef.data.exceptions.UserNotFoundException
     */
    @Test(expected = UserNotFoundException.class)
    public void testIsUserFound() throws SQLException, UserNotFoundException {
        System.out.println("isUserFound");
        User instance = new User();
        instance.setUname("tumee");
        instance.getUserDetails();
    }
    
    /**
     *
     * @throws SQLException
     */
    @Test
    public void testUserLog() throws SQLException{
        User admin = new User();
        admin.setId(1);
        boolean expected = false; // return false if query returned affected number of rows or there is no result set returned
        boolean result = admin.userLog(0);
        assertEquals(expected, result);
    }

}
