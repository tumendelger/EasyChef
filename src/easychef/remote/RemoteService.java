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
    private String localURL, remoteURL;
    private Connection remoteCon, localCon;
    PreparedStatement remotePS, localPS;
    ResultSet orders, foodOrderDetails, drinkOrderDetails;

    //Get orders that are finalized
    //ispaid=1 order already payed, syn_status=0 order is NOT synced at the moment
    private final String getOrders = "SELECT id, ordertime, totalprice, vat, discount, cash, card, ispaid, totalAmount, tid, mid, uid, promocode, systemdate, syn_status FROM orders WHERE ispaid=1 AND syn_status=0";
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

}
