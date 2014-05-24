/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import com.mysql.jdbc.PreparedStatement;
import easychef.data.DBConnector;
import easychef.data.DeliveryStats;
import easychef.data.OrderDetail;
import easychef.net.Message.msgType;
import easychef.ui.MainController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author tumee
 */
public class OrderHandler extends Thread {

    private static final Logger logger = Logger.getLogger(OrderHandler.class.getName());
    private static final Queue<Pair<msgType, OrderDetail>> orders = new LinkedList();

    private final Object queueLock = new Object();
    private final Object newLock = new Object();

    private static OrderHandler instance;

    private final String getUserIP = "SELECT address FROM clientip WHERE "
            + "clientid=?";

    public OrderHandler() {
        super("oHandlerThread");
    }

    public static OrderHandler getInstance() {
        if (instance != null) {
            logger.info(String.format("OrderHandlerThread already exists. Returning instance: %s ", instance));
            return instance;
        } else {
            instance = new OrderHandler();
            Thread th = new Thread(instance);
            logger.info(String.format("[%s] thread instance started. Starting Thread Now.", instance.getName()));
            th.start();
            return instance;
        }
    }

    public void addOrder(Pair<msgType, OrderDetail> pair) {
        synchronized (queueLock) {
            logger.info(String.format("New order to be handled added to Pair. Order: %s", pair.getValue().toString()));
            orders.add(pair);
        }
        synchronized (newLock) {
            logger.info("Notify new OrderDetail. . . ");
            newLock.notify();
        }
    }

    @Override
    public void run() {

        while (true) {
            if (orders.isEmpty()) {
                synchronized (newLock) {
                    try {
                        newLock.wait();
                    } catch (InterruptedException ex) {
                        logger.log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                while (orders.size() > 0) {
                    Pair cPair = orders.poll();
                    msgType key = (msgType) cPair.getKey();
                    OrderDetail order = (OrderDetail) cPair.getValue();
                    switch (key) {
                        case C_CANCEL_WITH_COST:
                            //Chef cancelled this order with cost
                            order.setChefCancelled(false);
                            order.setDelivered(true);
                            logger.info(String.format("Cancel order with cost: %s", order.toString()));
                            MainController.deliveryStats.increaseOrdersWithCost();
                            order.deliverOrder();
                            break;
                        case C_CANCEL_NO_COST:
                            //Chef cancelled order without cost
                            order.setChefCancelled(true);
                            order.setDelivered(true);
                            logger.info(String.format("Cancel order without cost: %s", order.toString()));
                            MainController.deliveryStats.increaseOrdersWithNoCost();
                            order.deliverOrder();
                            break;
                        case DELIVER:
                            //Chef did not acept cancel
                            //directly deliver order
                            order.setDelivered(true);
                            order.deliverOrder();
                            notifyWaiter(order);
                            int wt = order.getWaitTime();
                            if (wt <= 10) {
                                MainController.deliveryStats.increaseLowWait();
                            } else {
                                if (wt < 15) {
                                    MainController.deliveryStats.increaseMedWait();
                                } else {
                                    MainController.deliveryStats.increaseHighWait();
                                }
                            }
                            //TO DO Notify waiter
                            break;
                        default:
                            throw new AssertionError(key.name());
                    }//SWITCH
                }//WHILE
            }//IF
        }//WHILE
    }

    /**
     *
     * @param id
     */
    private void notifyWaiter(OrderDetail detail) {
        logger.info(String.format("OrderDetail[id: %s] has been delivered. Notify to waiter[%s]", detail.getId(), detail.getUid()));
        try {

            //Send notification to client
            PreparedStatement pStat = (PreparedStatement) DBConnector.getConnection().prepareStatement(getUserIP);
            pStat.setInt(1, detail.getUid());
            ResultSet rs = pStat.executeQuery();
            if (rs.next()) {
                logger.info(String.format("Notifying user@%s", rs.getString(1)));
                //TO DO 

            } else {
                logger.info(String.format("No IP address found for user[%s]", detail.getUid()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
