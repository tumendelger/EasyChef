/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author tumee
 */
public class RemoteDBConnectorTest {

    public RemoteDBConnectorTest() {
    }

    @Test
    public void testGetConnection() throws SQLException {
        // TODO review the generated test code and remove the default call to fail.
        Connection connection = RemoteDBConnector.getConnection();
        if (connection == null) {
            fail("Connection is null");
        }

    }

}
