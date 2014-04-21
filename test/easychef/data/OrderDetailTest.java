/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package easychef.data;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class OrderDetailTest {
    
    public OrderDetailTest() {
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
     * Test of getAllOrders method, of class OrderDetail.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAllOrders() throws SQLException{
        System.out.println("getAllOrders");
        ObservableList<OrderDetail> tableData = FXCollections.observableArrayList();
        OrderDetail instance = new OrderDetail();
        instance.getAllOrders(tableData);
        // TODO review the generated test code and remove the default call to fail.
        // 6 Food order details are in test database 
        // table data size must be 6
        for(OrderDetail detail: tableData){
            System.out.println(detail.toString());
        }
        assertEquals(11, tableData.size());        
    }

    /**
     * Test of getOrderDetailsByOID method, of class OrderDetail.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetOrderDetailsByOID() throws SQLException {
        System.out.println("getOrderDetailsByOID");
        ObservableList<OrderDetail> tableData = FXCollections.observableArrayList();
        OrderDetail instance = new OrderDetail();
        instance.setOrderID(1234567891);
        instance.getOrderDetailsByOID(tableData);
        
        // 6 Food order details are in test database 
        // table data size must be 6
        for(OrderDetail detail: tableData){
            System.out.println(detail.toString());
        }
        assertEquals(6, tableData.size());
    }    
    
//
//    /**
//     * Test of getDetailChanges method, of class OrderDetail.
//     */
//    @Test
//    public void testGetDetailChanges() throws Exception {
//        System.out.println("getDetailChanges");
//        OrderDetail detail = null;
//        OrderDetail instance = new OrderDetail();
//        instance.getDetailChanges(detail);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOrderID method, of class OrderDetail.
//     */
//    @Test
//    public void testGetOrderID() {
//        System.out.println("getOrderID");
//        OrderDetail instance = new OrderDetail();
//        long expResult = 0L;
//        long result = instance.getOrderID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setOrderID method, of class OrderDetail.
//     */
//    @Test
//    public void testSetOrderID() {
//        System.out.println("setOrderID");
//        long orderID = 0L;
//        OrderDetail instance = new OrderDetail();
//        instance.setOrderID(orderID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getId method, of class OrderDetail.
//     */
//    @Test
//    public void testGetId() {
//        System.out.println("getId");
//        OrderDetail instance = new OrderDetail();
//        long expResult = 0L;
//        long result = instance.getId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setId method, of class OrderDetail.
//     */
//    @Test
//    public void testSetId() {
//        System.out.println("setId");
//        long id = 0L;
//        OrderDetail instance = new OrderDetail();
//        instance.setId(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTableid method, of class OrderDetail.
//     */
//    @Test
//    public void testGetTableid() {
//        System.out.println("getTableid");
//        OrderDetail instance = new OrderDetail();
//        Integer expResult = null;
//        Integer result = instance.getTableid();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setTableid method, of class OrderDetail.
//     */
//    @Test
//    public void testSetTableid() {
//        System.out.println("setTableid");
//        Integer tableid = null;
//        OrderDetail instance = new OrderDetail();
//        instance.setTableid(tableid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFoodID method, of class OrderDetail.
//     */
//    @Test
//    public void testGetFoodID() {
//        System.out.println("getFoodID");
//        OrderDetail instance = new OrderDetail();
//        int expResult = 0;
//        int result = instance.getFoodID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFoodID method, of class OrderDetail.
//     */
//    @Test
//    public void testSetFoodID() {
//        System.out.println("setFoodID");
//        int foodID = 0;
//        OrderDetail instance = new OrderDetail();
//        instance.setFoodID(foodID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFoodName method, of class OrderDetail.
//     */
//    @Test
//    public void testGetFoodName() {
//        System.out.println("getFoodName");
//        OrderDetail instance = new OrderDetail();
//        String expResult = "";
//        String result = instance.getFoodName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFoodName method, of class OrderDetail.
//     */
//    @Test
//    public void testSetFoodName() {
//        System.out.println("setFoodName");
//        String FoodName = "";
//        OrderDetail instance = new OrderDetail();
//        instance.setFoodName(FoodName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isHasChange method, of class OrderDetail.
//     */
//    @Test
//    public void testIsHasChange() {
//        System.out.println("isHasChange");
//        OrderDetail instance = new OrderDetail();
//        boolean expResult = false;
//        boolean result = instance.isHasChange();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setHasChange method, of class OrderDetail.
//     */
//    @Test
//    public void testSetHasChange() {
//        System.out.println("setHasChange");
//        boolean hasChange = false;
//        OrderDetail instance = new OrderDetail();
//        instance.setHasChange(hasChange);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getChanges method, of class OrderDetail.
//     */
//    @Test
//    public void testGetChanges() {
//        System.out.println("getChanges");
//        OrderDetail instance = new OrderDetail();
//        List<String> expResult = null;
//        List<String> result = instance.getChanges();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setChanges method, of class OrderDetail.
//     */
//    @Test
//    public void testSetChanges() {
//        System.out.println("setChanges");
//        List<String> changes = null;
//        OrderDetail instance = new OrderDetail();
//        instance.setChanges(changes);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isDelivered method, of class OrderDetail.
//     */
//    @Test
//    public void testIsDelivered() {
//        System.out.println("isDelivered");
//        OrderDetail instance = new OrderDetail();
//        boolean expResult = false;
//        boolean result = instance.isDelivered();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDelivered method, of class OrderDetail.
//     */
//    @Test
//    public void testSetDelivered() {
//        System.out.println("setDelivered");
//        boolean delivered = false;
//        OrderDetail instance = new OrderDetail();
//        instance.setDelivered(delivered);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isWaiterCancelled method, of class OrderDetail.
//     */
//    @Test
//    public void testIsWaiterCancelled() {
//        System.out.println("isWaiterCancelled");
//        OrderDetail instance = new OrderDetail();
//        boolean expResult = false;
//        boolean result = instance.isWaiterCancelled();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setWaiterCancelled method, of class OrderDetail.
//     */
//    @Test
//    public void testSetWaiterCancelled() {
//        System.out.println("setWaiterCancelled");
//        boolean waiterCancelled = false;
//        OrderDetail instance = new OrderDetail();
//        instance.setWaiterCancelled(waiterCancelled);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isChefCancelled method, of class OrderDetail.
//     */
//    @Test
//    public void testIsChefCancelled() {
//        System.out.println("isChefCancelled");
//        OrderDetail instance = new OrderDetail();
//        boolean expResult = false;
//        boolean result = instance.isChefCancelled();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setChefCancelled method, of class OrderDetail.
//     */
//    @Test
//    public void testSetChefCancelled() {
//        System.out.println("setChefCancelled");
//        boolean chefCancelled = false;
//        OrderDetail instance = new OrderDetail();
//        instance.setChefCancelled(chefCancelled);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOrderTime method, of class OrderDetail.
//     */
//    @Test
//    public void testGetOrderTime() {
//        System.out.println("getOrderTime");
//        OrderDetail instance = new OrderDetail();
//        String expResult = "";
//        String result = instance.getOrderTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setOrderTime method, of class OrderDetail.
//     */
//    @Test
//    public void testSetOrderTime() {
//        System.out.println("setOrderTime");
//        String orderTime = "";
//        OrderDetail instance = new OrderDetail();
//        instance.setOrderTime(orderTime);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getWaitTime method, of class OrderDetail.
//     */
//    @Test
//    public void testGetWaitTime() {
//        System.out.println("getWaitTime");
//        OrderDetail instance = new OrderDetail();
//        Integer expResult = null;
//        Integer result = instance.getWaitTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setWaitTime method, of class OrderDetail.
//     */
//    @Test
//    public void testSetWaitTime() {
//        System.out.println("setWaitTime");
//        Integer waitTime = null;
//        OrderDetail instance = new OrderDetail();
//        instance.setWaitTime(waitTime);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
