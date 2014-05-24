/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

/**
 *
 * @author tumee
 */
public final class DBSyncSQLs {

    protected final static String insertSystemSettings = "INSERT INTO system_settings(name, parameter) VALUES (?,?)";

    protected final static String getParameter = "SELECT parameter FROM system_settings WHERE name=?";

    protected final static String updateParameter = "UPDATE system_settings SET parameter=? WHERE name=?";

    protected final static String checkTables = "SELECT tablename FROM system_table_update WHERE tablename=? AND updatedate>?";

    protected final static String getCardDiscount = "SELECT id, name, percentage, conditions, ctid, action FROM carddiscount WHERE actiontime>?";
    protected final static String insertCardDiscount = "INSERT INTO carddiscount(id, name, percentage, conditions, ctid) VALUES(?,?,?,?,?)";
    protected final static String updateCardDiscount = "UPDATE carddiscount SET name=?, percentage=?, conditions=? WHERE id=?";
    protected final static String deleteCardDiscount = "DELETE FROM carddiscount WHERE id=?";

}
