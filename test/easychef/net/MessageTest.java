/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package easychef.net;

import static easychef.net.Message.msgType.CANCEL;
import static easychef.net.Message.msgType.DELIVER;
import static easychef.net.Message.msgType.ORDER;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class MessageTest {
    
    public MessageTest() {
    }

    /**
     * Test of getId method, of class Message.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Message instance = new Message();
        instance.setId(12345L);
        long expResult = 12345L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMtype method, of class Message.
     */
    @Test
    public void testGetMtype() {
        System.out.println("getMtype");
        Message instance = new Message();
        instance.setMtype(ORDER);
        Message.msgType expResult = ORDER;
        Message.msgType result = instance.getMtype();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Message.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 12345L;
        Message instance = new Message();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of setMtype method, of class Message.
     */
    @Test
    public void testSetMtype() {
        System.out.println("setMtype");
        Message.msgType mtype = DELIVER;
        Message instance = new Message();
        instance.setMtype(mtype);
        assertEquals(DELIVER, instance.getMtype());
    }

    /**
     * Test of toString method, of class Message.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Message instance = new Message();
        instance.setId(12345L);
        instance.setMtype(CANCEL);
        String expResult = "[CANCEL: 12345]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
