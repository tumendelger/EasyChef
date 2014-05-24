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
        String expectedPrinter = "printername";
        settings.loadSettings();

        assertEquals(expectedDate, settings.getSystemDate().get());
        assertEquals(expectedLWC, settings.getLWC());
        assertEquals(expectedMWC, settings.getMWC());
        assertEquals(expectedHWC, settings.getHWC());
        assertEquals(expectedCH, settings.getCH());
        assertEquals(expectedPrinter, settings.getPrinterName().get());
    }

}
