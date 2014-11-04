/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import easychef.data.OrderDetail;
import easychef.data.PrintOrder;
import easychef.data.utils.SoundNotification;
import easychef.data.Constants;
import static easychef.net.Message.msgType.CANCEL;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;

/**
 *
 * @author tumee
 */
public class ClientMessageHandler extends Thread {

    private static final Logger logger = Logger.getLogger(ClientMessageHandler.class.getName());

    private static ClientMessageHandler instance;
    private static ObservableList<OrderDetail> orders;
    private final Queue<Message> clientMsgs = new LinkedList();

    private final Object msgLock = new Object();
    private final Object newClientMsgLock = new Object();
    private final Object orderLock = new Object();
    private final SoundNotification notify = new SoundNotification();

    private String printerName;
    private int printerWidth;

    public ClientMessageHandler() {
        super("cMessageHandlerThread");
    }

    public static ClientMessageHandler getInstance() {
        if (instance != null) {
            //If instance not null return existing instance
            logger.info(String.format("ClientMessageHandler instance already exist. Returning instance: [%s]", instance.toString()));
            return instance;
        } else {
            //Create new instance thread and return
            instance = new ClientMessageHandler();
            Thread oHandlerThread = new Thread(instance);
            logger.info(String.format("[%s] thread instcanse created. Starting Thread Now.", instance.getName()));
            oHandlerThread.start();
            return instance;
        }
    }

    public static void setOrders(ObservableList<OrderDetail> orders) {
        ClientMessageHandler.orders = orders;
    }

    public void addClientMsg(Message clientMsg) {
        synchronized (msgLock) {
            //Received new message from client
            logger.info(String.format("Received new client message. Message: [%s]", clientMsg.toString()));
            clientMsgs.add(clientMsg);
        }
        //Notify new msg
        synchronized (newClientMsgLock) {
            logger.info("Notify newClientMsgLock. . .");
            newClientMsgLock.notify();
        }
    }

    public Queue<Message> getClientMsgs() {
        return clientMsgs;
    }

    @Override
    public void run() {
        while (true) {
            if (clientMsgs.isEmpty()) {
                synchronized (newClientMsgLock) {
                    try {
                        //List is empty wait till new msg comes
                        logger.info("clientMsgs list is empty. Waiting for new msg.");
                        newClientMsgLock.wait();
                    } catch (InterruptedException ex) {
                        logger.log(Level.SEVERE, null, ex);
                    }
                }//Synchronized                
            } else {
                //List is not empty continue to process list
                logger.info("clientMsgs list is NOT empty. Continue processing messages.");
                while (clientMsgs.size() > 0) {
                    Message cMsg = clientMsgs.poll();
                    switch (cMsg.getMtype()) {
                        case ALL:
                            //Read all Orders which are not payed yet
                            //When Chef system goes down we need to show 
                            //all remaining orders from previous state
                            logger.info("Message to handle is msgType[ALL]");

                            //Adding retrieved data to TableViews items
                            getAllOrderDetails(orders);
                            notify.playSound(Constants.NEW_ORDER);
                            break;
                        case ORDER:
                            //New Order should be received
                            //Get order details and add tprinterNameo orderTable
                            logger.info("Message to handle is msgType[ORDER]");

                            //Adding retrieved data to TableViews items
                            getNewOrderDetails(cMsg.getId(), orders);
                            notify.playSound(Constants.NEW_ORDER);
                            break;
                        case CANCEL:
                            //OrderDetail has been cancelled 
                            //It has to be notified to the kitchen Chef
                            logger.info("Message to handle is msgType[CANCEL]");
                            notifyCancel(cMsg.getId(), orders);
                            notify.playSound(Constants.CANCEL_ORDER);
                            break;
                        case UPDATE:
                            //Order has just been updated with new order
                            //Msg contains newly added OrderDetail ID
                            logger.info("Message to handle is msgType[UPDATE]");

                            //Adding retrieved data to ObservableList<OrderDetail> 
                            //which is source items                            
                            getNewOrderDetail(cMsg.getId(), orders);
                            notify.playSound(Constants.NEW_ORDER);
                            break;
                        case UPDATE_DRINK:
                            //Order updated with drink from kitchen
                            logger.info("Message to handler is msgType[UPDATE_DRINK]");

                            //Add retrived data to observable list
                            getNewDrinkOrderDetail(cMsg.getId(), orders);
                            notify.playSound(Constants.NEW_ORDER);
                            break;
                        case DELIVER:
                            //Order has been delivered from Kitchen to 

                            //Tek has to notify Waiter
                            logger.info("Message to handle is msgType[DELIVER]");
                            notifyWaiter(cMsg.getId());
                            notify.playSound(Constants.DELIVER_ORDER);
                            break;
                        case PRINT:
                            //Customer requested Order Bill 
                            //Bill needs to be printed from Kitchen printer
                            logger.info("Message to handle is msgType[PRINT]");
                             {
                                try {
                                    printOrderBill(cMsg.getId(), printerName, printerWidth);
                                } catch (PrinterException ex) {
                                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            notify.playSound(Constants.PRINT_BILL);
                            break;
                        default:
                            throw new AssertionError(cMsg.getMtype());
                    }
                }

                if (orders.size() > 0) {
                    OrderDetail last = orders.get(orders.size() - 1);
                    orders.remove(last);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        logger.log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(() -> {
                        orders.add(last);
                    });
                }

            }
        }//WHILE
    }

    /**
     *
     * @param orders
     */
    private void getAllOrderDetails(ObservableList<OrderDetail> orders) {
        logger.info("Reading all orders from database. Which are still not delivered.");
        OrderDetail allOrders = new OrderDetail();
        try {
            allOrders.getAllOrders(orders);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get all order details for given ORDERID
     *
     * @param id
     */
    private void getNewOrderDetails(long id, ObservableList<OrderDetail> tableData) {
        logger.info(String.format("Get order details. OrderID: [%s]", id));
        OrderDetail detail = new OrderDetail();
        detail.setOrderID(id);
        try {
            detail.getOrderDetailsByOID(tableData);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
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

    /**
     *
     * @param id
     */
    private void printOrderBill(long id, String printerName, int paperWidth) throws PrinterException {
        logger.info(String.format("Order bill requested [OrderID: %s]. Start printing bill.", id));

        PrintOrder orderToPrint = new PrintOrder(id, paperWidth);
        orderToPrint.getBillDetails();
        logger.info(String.format("Print request received for Order:%s", id));
        logger.info(String.format("Printing Order| %s", orderToPrint.toString()));

        //TO DO 
        //Print service code here
        orderToPrint.print(printerName);
    }

    /**
     *
     * @param id
     */
    private void notifyCancel(long id, ObservableList<OrderDetail> orders) {
        logger.info(String.format("OrderDetail [id: %s] cancelled notify kitchen. ", id));
        for (OrderDetail order : orders) {
            if (order.getId() == id) {
                order.setWaiterCancelled(true);
                return;
            }
        }
    }

    /**
     * New food orderdetail received
     *
     * @param id
     * @param orders
     */
    private void getNewOrderDetail(long id, ObservableList<OrderDetail> orders) {
        logger.info(String.format("New Order[id: %s] received. Start processing.", id));
        //TO DO
        OrderDetail od = new OrderDetail();
        od.setId(id);
        od.getFoodDetails();
        orders.add(od);
    }

    /**
     * New drink orderdetail from kitchen ordered
     *
     * @param id
     * @param orders
     */
    private void getNewDrinkOrderDetail(long id, ObservableList<OrderDetail> orders) {
        logger.info(String.format("New DrinkOrder[id: %s] received. Start processing.", id));
        //TO DO
        OrderDetail od = new OrderDetail();
        od.setId(id);
        od.getDrinkDetails();
        orders.add(od);
    }

    private int deliverOrderDetail(long id) {
        int rows = 0;

        //TO DO
        return rows;
    }

    public void setPrinterName(String name) {
        this.printerName = name;
        logger.log(Level.INFO, "Printer name set to --> {0}", this.printerName);
    }

    public void setPrinterWidth(int width) {
        this.printerWidth = width;
        logger.log(Level.INFO, "Printer paper width set to --> {0}", this.printerWidth);
    }
}
