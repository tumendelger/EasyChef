/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class RemoteServiceTest {

    protected RemoteService rs;

    public RemoteServiceTest() {
        rs = new RemoteService("remote");
    }

    @Test
    public void testGetOrders() {
        int n = 0;
        try {
            ResultSet result = rs.getOrders();
            System.out.println(String.format("Result set: %s", result.toString()));
            while (result.next()) {
                n++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        int expected = 3;
        System.out.println(String.format("Number of orders returned : %s", n));
        assertEquals(expected, n);
    }

    @Test
    public void testGetFoodOrderDetails() {
        try {
            int n = 0;

            ResultSet result = rs.getFoodOrders(1234567891);
            System.out.println(String.format("Result set: %s", result.toString()));
            if (result != null) {
                while (result.next()) {
                    n++;
                }
                System.out.println(String.format("Number of orders returned : %s", n));
            } else {
                fail("Returned null object");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetDrinkOrderDetails() {
        try {
            int n = 0;

            ResultSet result = rs.getDrinkOrders(1234567891);
            System.out.println(String.format("Result set: %s", result.toString()));
            if (result != null) {
                while (result.next()) {
                    n++;
                }
                System.out.println(String.format("Number of drink orders returned : %s", n));
            } else {
                fail("Returned null object");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
