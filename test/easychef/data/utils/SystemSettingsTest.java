/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data.utils;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tumee
 */
public class SystemSettingsTest {

    public SystemSettingsTest() {
    }

    @Test
    public void testGetValue() throws SQLException {
        SystemSettings settings = new SystemSettings();
        String expectedDate = "2014-05-05";
        String expectedLWC = "#35AA74";
        String expectedMWC = "#FFB848";
        String expectedHWC = "#FF3F3F";
        String expectedCH = "#4D90FE";
        String expectedPrinter = "Send To OneNote 2010";
        String expectedAddress = "192.168.1.120";
        int expectedPort = 7800;
        settings.loadSettings();

        assertEquals(expectedDate, settings.getSystemDate().get());
        assertEquals(expectedLWC, settings.getLWC());
        assertEquals(expectedMWC, settings.getMWC());
        assertEquals(expectedHWC, settings.getHWC());
        assertEquals(expectedCH, settings.getCH());
        assertEquals(expectedPrinter, settings.getPrinterName().get());
        assertEquals(expectedAddress, settings.getServerAddress().get());
        assertEquals(expectedPort, settings.getServerPort());
    }

    @Test
    public void testSetKitchenIpPort() {
        SystemSettings settings = new SystemSettings();
        int expected = 2;
        int result = settings.setKitchenIpPort("192.168.1.120", 7800);
        assertEquals(expected, result);
    }

}
