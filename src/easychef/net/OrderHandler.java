/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import easychef.data.OrderDetail;
import easychef.net.Message.msgType;
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
                            order.setWaiterCancelled(false);
                            order.setDelivered(true);
                            logger.info(String.format("Cancel order with cost: %s", order.toString()));
//                            order.deliverOrder();
                            break;
                        case C_CANCEL_NO_COST:
                            //Chef cancelled order without cost
                            order.setWaiterCancelled(true);
                            order.setDelivered(true);
                            logger.info(String.format("Cancel order without cost: %s", order.toString()));
                            break;
                        case DELIVER:                            
                            //Chef did not acept cancel
                            //directly deliver order
                            order.setDelivered(true);
                            //
//                                notifyWaiter(order.getId());      
                            break;
                        default:
                            throw new AssertionError(key.name());
                    }    
                    order.deliverOrder();
                }
            }
        }
    }

    /**
     *
     * @param id
     */
    private void notifyWaiter(long id) {
        logger.info(String.format("OrderDetail[id: %s] has been delivered. Notify Waiter start.", id));
        //TO DO
        //Update database for delivered order
        //Send notification to client
    }
}
