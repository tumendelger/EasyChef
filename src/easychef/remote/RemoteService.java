/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

import static easychef.data.Constants.DB_NAME;
import static easychef.data.Constants.DB_PASSWORD;
import static easychef.data.Constants.DB_USER;
import static easychef.data.Constants.Local_DB_URL;
import static easychef.data.Constants.Remote_DB_NAME;
import static easychef.data.Constants.Remote_DB_URL;
import static easychef.data.Constants.USE_UTF8;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ugugdliin sang remote server luu bichne Background thread
 *
 * @author tumee
 */
public class RemoteService extends Thread {

    public static final Logger logger = Logger.getLogger(RemoteService.class.getName());
    private boolean shutdown = false;

    //Thread running status
    private static final int RUNNING = 1;
    private static final int STOPPED = 2;
    private static final int INTERRUPTED = 3;

    //Check Frequency
    private final double CHECK_FREQ = 60;

    //Database variables
    private final String localURL, remoteURL;
    private Connection remoteCon, localCon;
    PreparedStatement orderRemotePS, orderLocalPS, oDetailPS;
    ResultSet orders, foodOrders, drinkOrders;

    //Get orders that are finalized
    //ispaid=1 order already payed, syn_status=0 order is NOT synced at the moment
    private final String getOrders = "SELECT id, ordertime, totalprice, vat, discount, cash, card, totalAmount, tid, mid, uid, promocode, systemdate FROM orders WHERE ispaid=1 AND syn_status=0";
    private final String iOrder = "INSERT INTO orders(id, ordertime, totalprice, vat, discount, cash, card, ispaid, totalAmount, tid, mid, uid, promocode, systemdate, syn_status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //Update status of the orders once synced
    private final String updateSynStatus = "UPDATE orders SET syn_status=1 WHERE id=?";

    //Get FoodOrderDetails for the order
    private final String getFoodOrderDetails = "SELECT id, foodid, haschange, price, isdelivered, waittime, ordertime, cancelbyw, cancelbych, mcid  FROM forderdetails WHERE orderid=?";
    private final String iOrderDetail = "INSERT INTO forderdetails(id, foodid, haschange, price, isdelivered, waittime, ordertime, cancelbyw, cancelbych, mcid, orderid) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    //Get DrinkOrderDetails for the order
    private final String getDrinkOrderDetails = "SELECT id, drinkid, price, isdelivered, ordertime, waittime, cancelbyw, cancelbych, mcid FROM dorderdetails WHERE orderid=?";
    private final String iDrinkOrderDetail = "INSERT INTO dorderdetails(id, drinkid, price, isdelivered, ordertime, waittime, cancelbyw, cancelbych, mcid, orderid) VALUES (?,?,?,?,?,?,?,?,?,?)";

    RemoteService(String name) {
        super(name);

        localURL = String.format("%s%s?%s", Local_DB_URL, DB_NAME, USE_UTF8);
        logger.info(String.format("Local DB URL set | %s", localURL));

        remoteURL = String.format("%s%s?%s", Remote_DB_URL, Remote_DB_NAME, USE_UTF8);
        logger.info(String.format("Remote DB URL set | %s", remoteURL));

    }

    @Override
    public void run() {
        while (!shutdown) {
            try {
                /*
                 *  First get orders getOrders() returns ResultSet
                 *  if there is no Orders
                 */
                orders = getOrders();

                if (orders != null) {
                    logger.info(String.format("Got Orders to process | %s", orders.toString()));
                    logger.info("Start processing orders to remote server.");

                    int no = 0;
                    while (orders.next()) {

                    }

                }//else do nothing
                else {
                    logger.info("No Orders to process continue with sleep.");
                }
                sleep((long) (CHECK_FREQ * 1000));
            } catch (InterruptedException | SQLException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet getOrders() {
        PreparedStatement pStat;
        try {
            //Connect to local DB
            logger.info(String.format("Connecting to local DB | %s | %s", localURL, DB_NAME));
            localCon = DriverManager.getConnection(localURL, DB_USER, DB_PASSWORD);
            pStat = localCon.prepareStatement(getOrders);

            logger.info(String.format("Executing | %s", pStat.toString()));
            pStat.executeQuery();
            return pStat.getResultSet();
        } catch (SQLException ex) {
            logger.info(String.format("Could NOT connect to database@%s", localCon.toString()));
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param orderid
     * @return
     */
    public ResultSet getFoodOrders(long orderid) {
        try {
            PreparedStatement pStat;

            if (localCon == null) {
                logger.info("Local connection to DB is null. Try to connect local BD.");
                localCon = DriverManager.getConnection(localURL, DB_USER, DB_PASSWORD);
                logger.info("Connected to local DB successfully.");
            }

            logger.info(String.format("Getting FoodOrderDetails for order | %s", orderid));
            pStat = localCon.prepareStatement(getFoodOrderDetails);
            pStat.setLong(1, orderid);

            return pStat.executeQuery();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * m
     *
     * @param orderid
     * @return
     */
    public ResultSet getDrinkOrders(long orderid) {
        PreparedStatement ps;
        try {
            if (localCon == null) {
                logger.info("Local connection to DB is null. Try to connect local BD.");
                localCon = DriverManager.getConnection(localURL, DB_USER, DB_PASSWORD);
                logger.info("Connected to local DB successfully.");
            }

            logger.info(String.format("Getting FoodOrderDetails for order | %s", orderid));
            ps = localCon.prepareStatement(getDrinkOrderDetails);
            ps.setLong(1, orderid);

            return ps.executeQuery();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * insert all the FoodOrderDetails to Remote server
     *
     * @param orders
     * @return
     */
    public boolean processOrder(ResultSet orders) {
        try {
            while (orders.next()) {
                /**
                 * For every orders that are NOT synced Create connection to
                 * remote site and sync order and other related details
                 */
                long oid = orders.getLong(1);
                String otime = orders.getString(2);
                double totalprice = orders.getDouble(3);
                double vat = orders.getDouble(4);
                double discount = orders.getDouble(5);
                double cash = orders.getDouble(6);
                double card = orders.getDouble(7);
                double totalAmount = orders.getDouble(8);
                int tid = orders.getInt(9);
                int mid = orders.getInt(10);
                int uid = orders.getInt(11);
                String promocode = orders.getString(12);
                String sysDate = orders.getString(13);

                if (remoteCon == null) {
                    logger.info("Remote connection to DB is null. Try to connect remote BD.");
                    remoteCon = DriverManager.getConnection(remoteURL, DB_USER, DB_PASSWORD);
                    logger.info("Connected to remote DB successfully.");

                    /**
                     * Set autocommit false After syncing every forderdetails
                     * and drinkorderdetails to server we commit transaction
                     */
                    remoteCon.setAutoCommit(false);
                }

                orderRemotePS = remoteCon.prepareStatement(iOrder);
                orderRemotePS.setLong(1, oid);
                orderRemotePS.setString(2, otime);
                orderRemotePS.setDouble(3, totalprice);
                orderRemotePS.setDouble(4, vat);
                orderRemotePS.setDouble(5, discount);
                orderRemotePS.setDouble(6, cash);
                orderRemotePS.setDouble(7, card);
                orderRemotePS.setInt(8, 1);
                orderRemotePS.setDouble(9, totalAmount);
                orderRemotePS.setInt(10, tid);
                orderRemotePS.setInt(11, mid);
                orderRemotePS.setInt(12, uid);
                orderRemotePS.setString(13, promocode);
                orderRemotePS.setString(14, sysDate);
                orderRemotePS.setInt(15, 1);

                logger.info(String.format("Executing prepared statememt | %s", orderRemotePS));
                orderRemotePS.execute();

                logger.info(String.format("Successfully executed statement | %s", orderRemotePS));

                //Process Food order Details and Drink Order Details
                if (!processOrderDetails(oid)) {
                    logger.info(String.format("Something went wrong while processing Order[%s]", oid));
                    return false;
                }

            }//WHILE orders

            return true;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean processOrderDetails(long orderid) {

        int n = 0;

        //Get FoodOrderDetails 
        if (localCon == null) {
            try {
                logger.info(String.format("%s connection is NULL", localURL));
                logger.info(String.format("Try connecting | %s", localURL));

                localCon = DriverManager.getConnection(localURL, DB_USER, DB_PASSWORD);

                logger.info(String.format("Successfully connected to [LocalDB] | %s", localCon.toString()));
            } catch (SQLException ex) {
                Logger.getLogger(RemoteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            logger.info(String.format("Getting [FoodOrderDetails] for OrderID: %s", orderid));

            foodOrders = getFoodOrders(orderid);

            logger.info(String.format("Successfully retrieved [FoodOrderDetails]: %s", foodOrders.toString()));

            while (foodOrders.next()) {
                long id = foodOrders.getLong(1);
                int foodid = foodOrders.getInt(2);
                int haschange = foodOrders.getInt(3);
                double price = foodOrders.getDouble(4);
                int isdelivered = foodOrders.getInt(5);
                int waittime = foodOrders.getInt(6);
                String orderTime = foodOrders.getString(7);
                int cbw = foodOrders.getInt(8);
                int cbc = foodOrders.getInt(9);
                int mcid = foodOrders.getInt(10);

                oDetailPS = remoteCon.prepareStatement(iOrderDetail);
                oDetailPS.setLong(1, id);
                oDetailPS.setInt(2, foodid);
                oDetailPS.setInt(3, haschange);
                oDetailPS.setDouble(4, price);
                oDetailPS.setInt(5, isdelivered);
                oDetailPS.setInt(6, waittime);
                oDetailPS.setString(7, orderTime);
                oDetailPS.setInt(8, cbw);
                oDetailPS.setInt(9, cbc);
                oDetailPS.setInt(10, mcid);
                oDetailPS.setLong(11, orderid);

                logger.info(String.format("Executing | %s", oDetailPS.toString()));
                oDetailPS.execute();

                logger.info(String.format("Successfully executed | %s", oDetailPS.toString()));
                n++;
            }

            logger.info(String.format("Successfully synced FoodOrderDetails for Order[%s]", orderid));
            logger.info(String.format("Total FoodOrderDetails Synced: [%s]", n));

            logger.info(String.format("Getting [DrinkOrderDetails] for OrderID: %s", orderid));

            drinkOrders = getDrinkOrders(orderid);

            logger.info(String.format("Successfully retrieved [DrinkOrderDetails]: %s", drinkOrders.toString()));

            n = 0;

            while (drinkOrders.next()) {
                long id = drinkOrders.getLong(1);
                int drinkid = drinkOrders.getInt(2);
                double price = drinkOrders.getInt(3);
                int isdelivered = drinkOrders.getInt(4);
                String ordertime = drinkOrders.getString(5);
                int waittime = drinkOrders.getInt(6);
                int cancelbyw = drinkOrders.getInt(7);
                int cancelbych = drinkOrders.getInt(8);
                int mcid = drinkOrders.getInt(9);

                oDetailPS = remoteCon.prepareStatement(iDrinkOrderDetail);
                oDetailPS.setLong(1, id);
                oDetailPS.setInt(2, drinkid);
                oDetailPS.setDouble(3, price);
                oDetailPS.setInt(4, isdelivered);
                oDetailPS.setString(5, ordertime);
                oDetailPS.setInt(6, waittime);
                oDetailPS.setInt(7, cancelbyw);
                oDetailPS.setInt(8, cancelbych);
                oDetailPS.setInt(9, mcid);
                oDetailPS.setLong(10, orderid);

                logger.info(String.format("Executing | %s", oDetailPS.toString()));
                oDetailPS.execute();

                logger.info(String.format("Successfully executed | %s", oDetailPS.toString()));
                n++;
            }

            logger.info(String.format("Successfully synced all DrinkOrderDetails for Order[%s]", orderid));
            logger.info(String.format("Total DrinkOrderDetails Synced: [%s]", n));
            return true;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
