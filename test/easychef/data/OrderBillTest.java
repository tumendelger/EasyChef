/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import easychef.data.OrderBill.PaperWidth;
import javax.swing.text.SimpleAttributeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Tumendelger
 */
public class OrderBillTest {

    public OrderBillTest() {
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
     * Test of getOrderDetails method, of class OrderBill.
     */
    @Test
    public void testGetOrderDetails() throws Exception {
        System.out.println("\t\t = = = = getOrderDetails = = = =");
        OrderBill instance = new OrderBill(1234567891, PaperWidth.EIGHTY_MM);
        instance.getOrderDetails();
        // TODO review the generated test code and remove the default call to fail.
        int expectedTid = 1;
        double expectedTotalPrice = 68800;
        double expectedVat = 6880;
        double expectedDiscount = 0;
        String expectedMid = "2";

        assertEquals("Table id is NOT correct", expectedTid, instance.getTableid());
        assertEquals("TotalPrice is NOT correct", expectedTotalPrice, instance.getTotalAmount(), 0.01);
        assertEquals("VAT is NOT correct", expectedVat, instance.getVAT(), 0.01);
        assertEquals("Discount is NOT correct", expectedDiscount, instance.getDiscount(), 0.01);
        assertEquals("MemberId is NOT correct", expectedMid, instance.getMemberid());

    }

    /**
     * Test of getHeaders method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testGetHeaders() throws Exception {
        System.out.println("getHeaders");
        OrderBill instance = new OrderBill();
        instance.getHeaders();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertString method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testInsertString() throws Exception {
        System.out.println("insertString");
        String line = "";
        SimpleAttributeSet align = null;
        OrderBill instance = new OrderBill();
        instance.insertString(line, align);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeader method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testGetHeader() {
        System.out.println("getHeader");
        OrderBill instance = new OrderBill();
        String[] expResult = null;
        String[] result = instance.getHeader();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHeader method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testSetHeader() {
        System.out.println("setHeader");
        String[] header = null;
        OrderBill instance = new OrderBill();
        instance.setHeader(header);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFooter method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testGetFooter() {
        System.out.println("getFooter");
        OrderBill instance = new OrderBill();
        String[] expResult = null;
        String[] result = instance.getFooter();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFooter method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testSetFooter() {
        System.out.println("setFooter");
        String[] footer = null;
        OrderBill instance = new OrderBill();
        instance.setFooter(footer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableid method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testGetTableid() {
        System.out.println("getTableid");
        OrderBill instance = new OrderBill();
        int expResult = 0;
        int result = instance.getTableid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTableid method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testSetTableid() {
        System.out.println("setTableid");
        int tableid = 0;
        OrderBill instance = new OrderBill();
        instance.setTableid(tableid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalAmount method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testGetTotalAmount() {
        System.out.println("getTotalAmount");
        OrderBill instance = new OrderBill();
        double expResult = 0.0;
        double result = instance.getTotalAmount();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalAmount method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testSetTotalAmount() {
        System.out.println("setTotalAmount");
        double totalAmount = 0.0;
        OrderBill instance = new OrderBill();
        instance.setTotalAmount(totalAmount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVAT method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testGetVAT() {
        System.out.println("getVAT");
        OrderBill instance = new OrderBill();
        double expResult = 0.0;
        double result = instance.getVAT();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVAT method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testSetVAT() {
        System.out.println("setVAT");
        double VAT = 0.0;
        OrderBill instance = new OrderBill();
        instance.setVAT(VAT);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiscount method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testGetDiscount() {
        System.out.println("getDiscount");
        OrderBill instance = new OrderBill();
        double expResult = 0.0;
        double result = instance.getDiscount();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMemberid method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testGetMemberid() {
        System.out.println("getMemberid");
        OrderBill instance = new OrderBill();
        String expResult = "";
        String result = instance.getMemberid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMemberid method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testSetMemberid() {
        System.out.println("setMemberid");
        String memberid = "";
        OrderBill instance = new OrderBill();
        instance.setMemberid(memberid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiscount method, of class OrderBill.
     */
    @Ignore
    @Test
    public void testSetDiscount() {
        System.out.println("setDiscount");
        double discount = 0.0;
        OrderBill instance = new OrderBill();
        instance.setDiscount(discount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
