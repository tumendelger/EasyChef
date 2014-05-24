/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class SyncSystemDataTest {

    protected SyncSystemData syncEmp;

    public SyncSystemDataTest() {
        syncEmp = new SyncSystemData("2014-05-13");
    }

    @Test
    @Ignore
    public void testSyncEmployee() {
        syncEmp.syncEmployees();
    }

    @Test
    @Ignore
    public void testSyncMembers() {
        syncEmp.syncMembers();
    }

    @Test
    @Ignore
    public void testSyncSites() {
        syncEmp.syncSites();
    }

    @Test
    public void testSyncTables() {
        syncEmp.syncTables();
    }

}
