/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

import easychef.data.DBConnector;
import easychef.data.RemoteDBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used for syncing Food related tables
 *
 * @author tumee
 */
public class SyncMenus {

    private final static Logger logger = Logger.getLogger(SyncMenus.class.getName());

    //Food category Queries
    private final String getFoodCategories = "SELECT id, name, action FROM foodcategory WHERE actiontime>?";
    private final String insertFoodCategory = "INSERT INTO foodcategory(id, name) VALUES(?,?)";
    private final String updateFoodCategory = "UPDATE foodcategory SET name=?, action=? WHERE id=?";

    //Food Menu Queries
    private final String getFoodMenus = "SELECT id, name, price, isonthemenu, isset, fcit, createdat, createdby, isdetails, mcid, action FROM foodmenu WHERE actiontime>?";
    private final String insertFoodMenu = "INSERT INTO foodmenu(id, name, price, isonthemenu, isset, fcit, createdat, createdby, isdetails, mcid) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?)";
    private final String updateFoodMenu = "UPDATE foodmenu SET name=?, price=?, isonthemenu=?, isset=?, fcit=?, isdetails=?, mcid=?,"
            + "action=? WHERE id=?";

    //Food Sites Queries
    private final String getFoodSites = "SELECT foodmenuid, siteid, status, action FROM food_sites WHERE actiontime>?";
    private final String insertFoodSite = "INSERT INTO food_sites(foodmenuid, siteid, status, action) VALUES(?,?,?,?)";
    private final String updateFoodSite = "UPDATE food_sites SET foodmenuid=?, siteid=?, status=?, action=? WHERE foodmenuid=? AND siteid=?";

    //Changes Queries
    private final String getChanges = "SELECT id, name, action FROM changes WHERE actiontime>?";
    private final String insertChange = "INSERT INTO changes(id, name) VALUES(?,?)";
    private final String updateChange = "UPDATE changes SET name=?, action=? WHERE id=?";

    //Food changes Queries
    private final String getFoodChanges = "SELECT id, changesid, foodmenuid, action FROM foodchange WHERE actiontime>?";
    private final String insertFoodChange = "INSERT INTO foodchange(id, changesid, foodmenuid) VALUES(?,?,?)";
    private final String updateFoodChange = "UPDATE foodchange SET changesid=?, foodmenuid=?, action=? WHERE id=?";

    //Drink Categort Queries
    private final String getDrinkCategories = "SELECT id, name, action FROM drinkcategory WHERE actiontime>?";
    private final String insertDrinkCategory = "INSERT INTO drinkcategory(id, name) VALUES(?,?)";
    private final String updateDrinkCategory = "UPDATE drinkcategory SET name=?, action=? WHERE id=?";

    //Drink Menu Queries
    private final String getDrinkMenus = "SELECT id, name, price, isonthemenu, isset, dcid, createdat, createdby, mcid, action FROM drinkmenu WHERE actiontime>?";
    private final String insertDrinkMenu = "INSERT INTO drinkmenu(id, name, price, isonthemenu, isset, dcid, createdat, createdby, mcid) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    private final String updateDrinkMenu = "UPDATE drinkmenu SET name=?, price=?, isonthemenu=?, isset=?, dcid=?, mcid=?,"
            + "action=? WHERE id=?";

    //Drink Sites Queries
    private final String getDrinkSites = "SELECT drinkmenuid, site, status, action FROM drink_sites WHERE actiontime>?";
    private final String insertDrinkSite = "INSERT INTO drink_sites(drinkmenuid, site, status, action) VALUES(?,?,?,?)";
    private final String updateDrinkSite = "UPDATE drink_sites SET status=?, action=? WHERE drinkmenuid=? AND site=?";

    private final String last_update;
    private PreparedStatement remotePS, localPS;
    private ResultSet res;

    public SyncMenus(String last_update) {
        this.last_update = last_update;
    }

    public void syncFoodCategory() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getFoodCategories);
            remotePS.setString(1, last_update);

            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                int action = res.getInt(3);

                switch (action) {
                    case 0://New
                        localPS = DBConnector.getConnection().prepareStatement(insertFoodCategory);
                        localPS.setInt(1, id);
                        localPS.setString(2, name);

                        localPS.execute();
                        logger.info(String.format("Successfully executed | %s", localPS.toString()));
                        break;
                    case 1://Update
                    case 2://Delete
                        localPS = DBConnector.getConnection().prepareStatement(updateFoodCategory);
                        localPS.setString(1, name);
                        localPS.setInt(2, action);
                        localPS.setInt(3, id);

                        int n = localPS.executeUpdate();
                        logger.info(String.format("Successfully executed | %s | Updated row count : %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SyncMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void syncFoodMenu() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getFoodMenus);
            remotePS.setString(1, last_update);
            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                int price = res.getInt(3);
                int isonthemenu = res.getInt(4);
                int isset = res.getInt(5);
                int fcit = res.getInt(6);
                String createdat = res.getString(7);
                int createdby = res.getInt(8);
                int isdetails = res.getInt(9);
                int mcid = res.getInt(10);
                int action = res.getInt(11);

                //TO DO
                switch (action) {
                    case 0://NEW INSERT
                        localPS = DBConnector.getConnection().prepareStatement(insertFoodMenu);
                        localPS.setInt(1, id);
                        localPS.setString(2, name);
                        localPS.setInt(3, price);
                        localPS.setInt(4, isonthemenu);
                        localPS.setInt(5, isset);
                        localPS.setInt(6, fcit);
                        localPS.setString(7, createdat);
                        localPS.setInt(8, createdby);
                        localPS.setInt(9, isdetails);
                        localPS.setInt(10, mcid);

                        localPS.execute();
                        logger.info(String.format("Successfully executed | %s", localPS.toString()));
                        break;
                    case 1://UPDATE
                    case 2://DELETE
                        localPS = DBConnector.getConnection().prepareStatement(updateFoodMenu);
                        localPS.setString(1, name);
                        localPS.setInt(2, price);
                        localPS.setInt(3, isonthemenu);
                        localPS.setInt(4, isset);
                        localPS.setInt(5, fcit);
                        localPS.setInt(6, isdetails);
                        localPS.setInt(7, mcid);
                        localPS.setInt(8, action);
                        localPS.setInt(9, id);

                        int n = localPS.executeUpdate();
                        logger.info(String.format("Successfully executed | %s | Updated row count : %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SyncMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void syncFoodSites() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getFoodSites);
            remotePS.setString(1, last_update);
            res = remotePS.executeQuery();

            while (res.next()) {
                int foodmenuid = res.getInt(1);
                int siteid = res.getInt(2);
                int status = res.getInt(3);
                int action = res.getInt(4);

                switch (action) {
                    case 0://NEW INSERT
                        localPS = DBConnector.getConnection().prepareStatement(insertFoodSite);
                        localPS.setInt(1, foodmenuid);
                        localPS.setInt(2, siteid);
                        localPS.setInt(3, status);
                        localPS.setInt(4, action);

                        localPS.execute();
                        logger.info(String.format("Successfully executed | %s", localPS.toString()));
                        break;
                    case 1://UPDATE
                    case 2://DELETE
                        localPS = DBConnector.getConnection().prepareStatement(updateFoodSite);
                        localPS.setInt(1, status);
                        localPS.setInt(2, action);
                        localPS.setInt(3, foodmenuid);
                        localPS.setInt(4, siteid);

                        int n = localPS.executeUpdate();
                        logger.info(String.format("Successfully executed | %s | Updated row count : %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SyncMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void syncDrinkCategory() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getDrinkCategories);
            remotePS.setString(1, last_update);

            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                int action = res.getInt(3);

                switch (action) {
                    case 0:
                        localPS = DBConnector.getConnection().prepareStatement(insertDrinkCategory);
                        localPS.setInt(1, id);
                        localPS.setString(2, name);

                        localPS.execute();
                        logger.info(String.format("Successfully executed | %s", localPS.toString()));
                        break;
                    case 1:
                    case 2:
                        localPS = DBConnector.getConnection().prepareStatement(updateDrinkCategory);
                        localPS.setString(1, name);
                        localPS.setInt(2, action);
                        localPS.setInt(3, id);

                        int n = localPS.executeUpdate();
                        logger.info(String.format("Successfully executed | %s | Updated row count : %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void syncDrinkMenu() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getDrinkMenus);
            remotePS.setString(1, last_update);
            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                int price = res.getInt(3);
                int isonthemenu = res.getInt(4);
                int isset = res.getInt(5);
                int dcid = res.getInt(6);
                String createdat = res.getString(7);
                int createdby = res.getInt(8);
                int mcid = res.getInt(9);
                int action = res.getInt(10);

                //TO DO
                switch (action) {
                    case 0://NEW INSERT
                        localPS = DBConnector.getConnection().prepareStatement(insertDrinkMenu);
                        localPS.setInt(1, id);
                        localPS.setString(2, name);
                        localPS.setInt(3, price);
                        localPS.setInt(4, isonthemenu);
                        localPS.setInt(5, isset);
                        localPS.setInt(6, dcid);
                        localPS.setString(7, createdat);
                        localPS.setInt(8, createdby);
                        localPS.setInt(9, mcid);

                        localPS.execute();
                        logger.info(String.format("Successfully executed | %s", localPS.toString()));
                        break;
                    case 1://UPDATE
                    case 2://DELETE
                        localPS = DBConnector.getConnection().prepareStatement(updateDrinkMenu);
                        localPS.setString(1, name);
                        localPS.setInt(2, price);
                        localPS.setInt(3, isonthemenu);
                        localPS.setInt(4, isset);
                        localPS.setInt(5, dcid);
                        localPS.setInt(6, mcid);
                        localPS.setInt(7, action);
                        localPS.setInt(8, id);

                        int n = localPS.executeUpdate();
                        logger.info(String.format("Successfully executed | %s | Updated row count : %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SyncMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void syncDrinkSites() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getDrinkSites);
            remotePS.setString(1, last_update);
            res = remotePS.executeQuery();

            while (res.next()) {
                int drinkmenuid = res.getInt(1);
                int siteid = res.getInt(2);
                int status = res.getInt(3);
                int action = res.getInt(4);

                switch (action) {
                    case 0://NEW INSERT
                        localPS = DBConnector.getConnection().prepareStatement(insertDrinkSite);
                        localPS.setInt(1, drinkmenuid);
                        localPS.setInt(2, siteid);
                        localPS.setInt(3, status);
                        localPS.setInt(4, action);

                        localPS.execute();
                        logger.info(String.format("Successfully executed | %s", localPS.toString()));
                        break;
                    case 1://UPDATE
                    case 2://DELETE
                        localPS = DBConnector.getConnection().prepareStatement(updateDrinkSite);
                        localPS.setInt(1, status);
                        localPS.setInt(2, action);
                        localPS.setInt(3, drinkmenuid);
                        localPS.setInt(4, siteid);

                        int n = localPS.executeUpdate();
                        logger.info(String.format("Successfully executed | %s | Updated row count : %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SyncMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void syncChanges() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getChanges);
            remotePS.setString(1, last_update);
            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                int action = res.getInt(3);

                switch (action) {
                    case 0://NEW
                        localPS = DBConnector.getConnection().prepareStatement(insertChange);
                        localPS.setInt(1, id);
                        localPS.setString(2, name);

                        localPS.execute();
                        logger.info(String.format("Successfully executed | %s", localPS.toString()));
                        break;
                    case 1://UPDATE
                    case 2://DELETE
                        localPS = DBConnector.getConnection().prepareStatement(updateChange);
                        localPS.setString(1, name);
                        localPS.setInt(2, action);
                        localPS.setInt(3, id);

                        int n = localPS.executeUpdate();
                        logger.info(String.format("Successfully executed | %s | Updated row count : %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void syncFoodChanges() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getFoodChanges);
            remotePS.setString(1, last_update);
            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                int changesid = res.getInt(2);
                int foodmenuid = res.getInt(3);
                int action = res.getInt(4);

                switch (action) {
                    case 0://New
                        localPS = DBConnector.getConnection().prepareStatement(insertFoodChange);
                        localPS.setInt(1, id);
                        localPS.setInt(2, changesid);
                        localPS.setInt(3, foodmenuid);

                        localPS.execute();
                        logger.info(String.format("Successfully executed | %s", localPS.toString()));
                        break;
                    case 1://UPDATE
                    case 2://DELETE
                        localPS = DBConnector.getConnection().prepareStatement(updateFoodChange);
                        localPS.setInt(1, changesid);
                        localPS.setInt(2, foodmenuid);
                        localPS.setInt(3, action);
                        localPS.setInt(4, id);

                        int n = localPS.executeUpdate();
                        logger.info(String.format("Successfully executed | %s | Updated row count : %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
