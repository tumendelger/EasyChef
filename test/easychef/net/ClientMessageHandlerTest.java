/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import static easychef.net.Message.msgType.DELIVER;
import static easychef.net.Message.msgType.ORDER;
import static easychef.net.Message.msgType.UPDATE;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class ClientMessageHandlerTest {

    public ClientMessageHandlerTest() {

    }

    /**
     * Test of getInstance method, of class ClientMessageHandler.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ClientMessageHandler expResult = ClientMessageHandler.getInstance();
        ClientMessageHandler result = ClientMessageHandler.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of addClientMsg method, of class ClientMessageHandler.
     */
    @Test
    public void testAddClientMsg() {
        System.out.println("addClientMsg");
        Message clientMsg = new Message(ORDER, 12345L);
        ClientMessageHandler instance = new ClientMessageHandler();
        instance.addClientMsg(clientMsg);
        assertEquals(1, instance.getClientMsgs().size());
    }

    /**
     * Test of run method, of class ClientMessageHandler. 
     * no need to test run method when returning instance
     * Thread all ready started
     */
    @Test
    @Ignore
    public void testRun() {
        System.out.println("run");
        ClientMessageHandler instance = new ClientMessageHandler();
        instance.run();
    }

    /**
     * Just to see working of the run method Checking behavior
     */
    @Test
    public void testStart() {
        System.out.println("Testing start");
        ClientMessageHandler instance = ClientMessageHandler.getInstance();

        System.out.println("Thread started. Adding some messages.");
        Message order = new Message(ORDER, 12345L);
        Message update = new Message(UPDATE, 12346L);
        Message deliver = new Message(DELIVER, 12345L);

        instance.addClientMsg(order);
        instance.addClientMsg(update);
        instance.addClientMsg(deliver);
    }

}
