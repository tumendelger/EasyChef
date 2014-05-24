/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class DeliveryStatsTest {

    public DeliveryStats ds;

    public DeliveryStatsTest() {

    }

    @Before
    public void beforeClass() {
        System.out.println("Before test . . .");
        ds = new DeliveryStats("2014-04-24");
    }

    /**
     * Test of increaseLowWait method, of class ds.
     */
    @Test
    public void testIncreaseLowWait() {        
        System.out.println("increaseLowWait");
        int before = ds.getLowWaitOrders().get();
        ds.increaseLowWait();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals((before + 1), ds.getLowWaitOrders().get());
    }

    /**
     * Test of increaseMedWait method, of class ds.
     */
    @Test
    public void testIncreaseMedWait() {
        System.out.println("increaseMedWait");
        int before = ds.getMedWaitOrders().get();
        ds.increaseMedWait();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals((before + 1), ds.getMedWaitOrders().get());
    }

    /**
     * Test of increaseHighWait method, of class ds.
     */
    @Test
    public void testIncreaseHighWait() {
        System.out.println("increaseHighWait");
        int before = ds.getHighWaitOrders().get();
        ds.increaseHighWait();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals((before + 1), ds.getHighWaitOrders().get());
    }

    /**
     * Test of increaseOrdersWithNoCost method of class ds
     */
    public void testIncreaseOrdersWithNoCost() {
        System.out.println("cOrdersNoCost");
        int before = ds.getcOrdersNoCost().get();
        ds.increaseOrdersWithNoCost();
        assertEquals((before + 1), ds.getcOrdersNoCost().get());
    }

    /**
     * Test of increaseOrdersWithCost method
     */
    public void testIncreaseOrdersWithCost() {
        System.out.println("increaseOrdersWithCost");
        int before = ds.getcOrdersWithCost().get();
        ds.increaseOrdersWithCost();
        assertEquals((before + 1), ds.getcOrdersWithCost());
    }

    /**
     * Test of setLowWaitOrders method, of class ds.
     */
    @Test
    public void testSetLowWaitOrders() {
        System.out.println("setLowWaitOrders");
        int lowWaitOrders = 5;
        ds.setLowWaitOrders(lowWaitOrders);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(lowWaitOrders, ds.getLowWaitOrders().get());
    }

    /**
     * Test of setMedWaitOrders method, of class ds.
     */
    @Test
    public void testSetMedWaitOrders() {
        System.out.println("setMedWaitOrders");
        int medWaitOrders = 4;
        ds.setMedWaitOrders(medWaitOrders);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(medWaitOrders, ds.getMedWaitOrders().get());
    }

    /**
     * Test of setHighWaitOrders method, of class ds.
     */
    @Test
    public void testSetHighWaitOrders() {
        System.out.println("setHighWaitOrders");
        int highWaitOrders = 5;
        ds.setHighWaitOrders(highWaitOrders);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(highWaitOrders, ds.getHighWaitOrders().get());
    }

    /**
     * Test of setcOrdersWithCost method, of class ds.
     */
    @Test
    public void testSetcOrdersWithCost() {
        System.out.println("setcOrdersWithCost");
        int cOrdersWithCost = 5;
        ds.setcOrdersWithCost(cOrdersWithCost);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(cOrdersWithCost, ds.getcOrdersWithCost().get());
    }

    /**
     * Test of setcOrdersNoCost method, of class ds.
     */
    @Test
    public void testSetcOrdersNoCost() {
        System.out.println("setcOrdersNoCost");
        int cOrdersNoCost = 0;
        ds.setcOrdersNoCost(cOrdersNoCost);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(cOrdersNoCost, ds.getcOrdersNoCost().get());

    }

    /**
     * Test initDailyStatistics method
     */
    @Test
    public void testInitDailyStatistics() {
        System.out.println("initDailyStatistics");
        DeliveryStats todaysStats = new DeliveryStats("2014-04-22");
        todaysStats.initDailyStatistics();
        //Check if all stats are set to 0
        assertEquals(0, ds.getLowWaitOrders().get());
        assertEquals(0, ds.getMedWaitOrders().get());
        assertEquals(0, ds.getHighWaitOrders().get());
        assertEquals(0, ds.getcOrdersNoCost().get());
        assertEquals(0, ds.getcOrdersWithCost().get());

        //Check database if new stats created
    }
}
