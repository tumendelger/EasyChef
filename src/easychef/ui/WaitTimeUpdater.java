/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.ui;

import easychef.data.OrderDetail;
import easychef.data.utils.DateTimeUtils;
import static java.lang.Thread.sleep;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;

/**
 *
 * @author tumee
 */
public class WaitTimeUpdater extends Thread {

    private static final Logger logger = Logger.getLogger(WaitTimeUpdater.class.getName());
    private final long UPDATE_FREQ = 60 * 1000;
    private ObservableList<OrderDetail> orders;
    private final DateTimeUtils dtUtil = new DateTimeUtils();

    public void setOrders(ObservableList<OrderDetail> orders) {
        this.orders = orders;
    }

    public WaitTimeUpdater() {
        super("wtUpdater");
        logger.info(String.format("[%s] new instance created.", Thread.currentThread().getName()));
    }

    @Override
    public void run() {
        //Run every 1 minute to increase wait minute
        while (true) {
            try {
                logger.info("1 Minute timer ran.");
                sleep(UPDATE_FREQ);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaitTimeUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (orders.size() > 0) {
                logger.log(Level.INFO, "Size of the table: {0}", orders.size());

                for (OrderDetail od : orders) {
                    try {
                        od.setWaitTime(dtUtil.getTimeDifferenceInMinutes(od.getOrderTime()));
                    } catch (ParseException ex) {
                        logger.log(Level.SEVERE, null, ex);
                    }
                }

                OrderDetail last = orders.get(orders.size() - 1);
                orders.remove(last);
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WaitTimeUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }

                Platform.runLater(() -> {
                    orders.add(last);
                });

            }
        }

    }
}
