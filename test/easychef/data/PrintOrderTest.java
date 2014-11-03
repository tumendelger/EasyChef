/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import java.awt.print.PrinterException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class PrintOrderTest {

    PrintOrder testOrder;

    public PrintOrderTest() {

    }

    @Before
    public void setUp() {
        testOrder = new PrintOrder(1234567891, 80);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getBillDetails method, of class PrintOrder.
     */
    @Test
    public void testGetBillDetails() {
        testOrder.getBillDetails();
        System.out.println(testOrder.toString());
    }

    /**
     *
     * @throws SQLException
     * @throws java.awt.print.PrinterException
     */
    @Test
    public void testPrint() throws SQLException, PrinterException {
        testOrder.getBillDetails();
        testOrder.prepareBill();
        testOrder.print("Foxit Phantom Printer");
    }
}
