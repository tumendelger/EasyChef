/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tumee
 */
public class SyncDiscountCardTest {

    protected SyncCards syncCard;

    public SyncDiscountCardTest() {
        syncCard = new SyncCards("2014-05-12");
    }

    @Test
    public void testSomeMethod() throws SQLException {
        syncCard.syncCard();
    }

}
