/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

import easychef.data.DBConnector;
import easychef.data.RemoteDBConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;

/**
 * This task used to sync database from remote server at the beginning of the
 * system
 *
 * @author tumee
 */
public class DBSyncer extends Task {

    private final static Logger logger = Logger.getLogger(DBSyncer.class.getName());
    //Whether application is ready to be shown
    private BooleanProperty appReady = new SimpleBooleanProperty(false);

    //Last updated time
    private String last_update = "N/A";

    //Site id
    private int siteid;
    //
    private Connection local_connection, remote_connection;

    private ResultSet rs;

    @Override
    protected Integer call() {
        try {
            //DO TASK HERE BEFORE LOADING
            updateMessage("Checking local DB connection...");

            local_connection = DBConnector.getConnection();

            //Get last updated date
            getLastUpdate();

            //getSite id
            getSiteId();

            updateMessage("Checking local DB connection...OK.");
            updateProgress(0.02, 1);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            updateMessage("Алдаа 101: Холболтын алдаа гарлаа.");
            return 101;
        } catch (SiteIdNotFoundException ex) {
            updateMessage("Алдаа 102: Салбарын ID олдсонгүй.");
            logger.log(Level.SEVERE, null, ex);
            return 102;
        }

        try {
            //Check Remote DB connection
            updateMessage("Checking remote DB connection...");
            remote_connection = RemoteDBConnector.getConnection();

            updateMessage("Checking remote DB connection...OK.");
            updateProgress(0.04, 1);

            //Start syncing
            syncTables();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            updateMessage("Анхааруулга 103: Серверийн холболт алга байна.");
            return 103;
        }

        //Task has been done        
        return 0;
    }

    public void syncTables() throws SQLException {
        //First sync cards
        updateMessage("Syncing discount cards . . . ");
        SyncCards syncCards = new SyncCards(getLast_update());
        syncCards.syncCard();
        updateMessage("Syncing discount cards . . . done.");
        updateProgress(0.06, 1);

        updateMessage("Syncing promo codes . . . ");
        syncCards.syncPromoCard();
        updateMessage("Syncing promo codes . . . done.");
        updateProgress(0.1, 1);

        //Sync system data
        updateMessage("Syncing employees . . . ");
        SyncSystemData syncSdata = new SyncSystemData(getLast_update());
        syncSdata.syncEmployees();
        updateMessage("Syncing employees . . . done.");
        updateProgress(0.14, 1);

        updateMessage("Syncing members . . . ");
        syncSdata.syncMembers();
        updateMessage("Syncing members . . . done.");
        updateProgress(0.18, 1);

        updateMessage("Syncing site . . .");
        syncSdata.syncSites();
        updateMessage("Syncing site . . . done");
        updateProgress(0.22, 1);

        updateMessage("Syncing tables . . . ");
        syncSdata.syncTables();
        updateMessage("Syncing tables . . . done.");
        updateProgress(0.30, 1);

        //Sync Foods
        updateMessage("Syncing Food menu . . . ");
        SyncMenus syncmenu = new SyncMenus(getLast_update());
        syncmenu.syncFoodCategory();
        updateProgress(0.4, 1);

        syncmenu.syncFoodMenu();
        updateProgress(0.5, 1);

        syncmenu.syncFoodSites();
        updateMessage("Syncing Food menu . . . done.");
        updateProgress(0.6, 1);

        updateMessage("Syncing drink menu . . . ");
        syncmenu.syncDrinkCategory();
        updateProgress(0.7, 1);

        syncmenu.syncDrinkMenu();
        updateProgress(0.8, 1);

        syncmenu.syncDrinkSites();
        updateMessage("Syncing drink menu . . . done.");
        updateProgress(0.9, 1);

        updateMessage("Syncing changes . . . ");
        syncmenu.syncChanges();
        updateProgress(0.95, 1);

        syncmenu.syncFoodChanges();
        updateMessage("Syncing changes . . . done.");
        updateProgress(1, 1);

        //Update last update
        updateMessage("Систем ачаалж дууслаа.");
        updateLastUpdate();

    }

    private void getLastUpdate() throws SQLException {
        PreparedStatement pStat = DBConnector.getConnection().prepareStatement(DBSyncSQLs.getParameter);
        pStat.setString(1, "LAST_UPDATE");
        rs = pStat.executeQuery();

        if (rs.next()) {
            //Result set should return 1 row
            System.out.println("Parameter found");
            setLast_update(rs.getString("parameter"));
            pStat.clearBatch();
            pStat.close();
        } else {//There is no row containing LAST_UPDATE
            System.out.println("Parameter NOT found");
            pStat = DBConnector.getConnection().prepareStatement(DBSyncSQLs.insertSystemSettings);
            pStat.setString(1, "LAST_UPDATE");
            Date now = new Date(System.currentTimeMillis());
            pStat.setDate(2, (java.sql.Date) now);
            pStat.execute();
            pStat.clearParameters();
            pStat.close();
        }

        rs.close();
    }

    private void updateLastUpdate() {
        try {
            PreparedStatement ps = DBConnector.getConnection().prepareStatement(DBSyncSQLs.updateParameter);
            SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd hh-mm-ss");
            Date now = new Date(System.currentTimeMillis());
            ps.setString(1, df.format(now));
            ps.setString(2, "LAST_UPDATE");

            int n = ps.executeUpdate();
            logger.log(Level.INFO, String.format("Successfully executed | %s | Updated row cnt: %s", ps.toString(), n));
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }

    private void getSiteId() throws SiteIdNotFoundException {
        try {
            PreparedStatement pStat = DBConnector.getConnection().prepareStatement(DBSyncSQLs.getParameter);
            pStat.setString(1, "SITEID");
            rs = pStat.executeQuery();

            if (rs.next()) {
                //Result set should return 1 row
                System.out.println("Parameter found");
                setSiteid(rs.getInt("parameter"));
                pStat.clearBatch();
                pStat.close();
            } else {
                throw new SiteIdNotFoundException("Салбарын ID олдсонгүй");
            }
        } catch (SQLException ex) {
            updateMessage("Өгөгдлийн сангийн алдаа гарлаа.");
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkTables(String tablename) throws SQLException {
        PreparedStatement pStat = RemoteDBConnector.getConnection().prepareStatement(DBSyncSQLs.checkTables);
        pStat.setString(1, tablename);
        pStat.setString(2, last_update);
        rs = pStat.executeQuery();
        if (rs.next()) {//Returned ResultSet
            rs.close();
            return true;
        } else {
            rs.close();
            return false;
        }
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
        logger.info(String.format("Last update set to| %s", last_update));
    }

    public String getLast_update() {
        return last_update;
    }

    public int getSiteid() {
        return siteid;
    }

    public void setSiteid(int siteid) {
        this.siteid = siteid;
        logger.info(String.format("Site ID set to| %s", siteid));
    }

    @Override
    protected void succeeded() {
        super.succeeded();
    }

    @Override
    protected void cancelled() {
        super.cancelled();
    }

    @Override
    protected void failed() {
        super.failed();
    }

    protected enum Tables {

        carddiscount,
        promocode,
        employee,
        members,
        tables,
        foodcategory,
        drinkcategory,
        foodmenu,
        drinkmenu,
        food_sites,
        drink_sites,
        foodchange
    }

}
