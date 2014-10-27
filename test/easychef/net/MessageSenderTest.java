/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import easychef.data.OrderDetail;
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
public class MessageSenderTest {

    public MessageSenderTest() {
    }

    /**
     * Test of getInstance method, of class MessageSender.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        MessageSender expResult = MessageSender.getInstance();
        MessageSender result = MessageSender.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of addMessageToSend method, of class MessageSender.
     */
    @Test
    public void testAddMessageToSend() {
        System.out.println("addMessageToSend");
        MessageSender instance = MessageSender.getInstance();
        OrderDetail msg = new OrderDetail();
        instance.addMessageToSend(msg);
        assertEquals(1, instance.getListToSend().size());
    }

    /**
     * Test of getClientIP method, of class MessageSender.
     *
     * @throws java.lang.Exception
     */
    @Ignore
    @Test
    public void testGetClientIP() throws Exception {
        System.out.println("getClientIP");
        int uid = 3;
        MessageSender instance = MessageSender.getInstance();
        String expResult = "192.168.120.12";
        String result = instance.getClientIP(uid);
        assertEquals(expResult, result);
    }
}
