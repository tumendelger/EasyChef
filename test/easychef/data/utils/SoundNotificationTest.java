/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tumendelger
 */
public class SoundNotificationTest {

    public SoundNotificationTest() {
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
     * Test of playSound method, of class SoundNotification.
     */
    @Test
    public void testPlaySound() {
        System.out.println("playSound");
        String soundFile = easychef.data.Constants.NEW_ORDER;
        SoundNotification instance = new SoundNotification();
        instance.playSound(soundFile);

    }

}
