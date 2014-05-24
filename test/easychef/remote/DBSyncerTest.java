/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class DBSyncerTest {

    private DBSyncer syncer;

    public DBSyncerTest() {
        syncer = new DBSyncer();
    }

    @Test
//    @Ignore
    public void testcheckTables() throws SQLException {
        System.out.println("* * * Check tables ");
        if (syncer.checkTables("carddiscount")) {
            System.out.println("Need to sync");
            return;
        } else {
            System.out.println("No Need to sync");
            fail("ok");
        }

    }

    @Test
    @Ignore
    public void testGetLastUpdate() throws SQLException {
//        System.out.println("* * * Test getLastUpdate with LAST_UPDATE parameter existing");
//        syncer.getLastUpdate();
//        String expected = "2014-05-08";
//        String returned = syncer.getLast_update();
//        System.out.println(String.format("Expected :%s Returned: %s", expected, returned));
//        assertEquals(expected, returned);

        System.out.println("* * * Test getLastUpdate with LAST_UPDATE parameter NOT existing");
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date(System.currentTimeMillis());
        String expected = fm.format(now);
        
        String returned = syncer.getLast_update();
        System.out.println(String.format("Expected :%s Returned: %s", expected, returned));
        assertEquals(expected, returned);

    }

}
