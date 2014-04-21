/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import easychef.data.Constants;
import easychef.data.DBConnector;
import easychef.data.OrderDetail;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Захиалга бэлэн болсон гэсэн мессежийг Тухайн үйлчлэгч рүү илгээнэ
 *
 * @author tumee
 */
public class MessageSender extends Thread {

    private static final Logger logger = Logger.getLogger(MessageSender.class.getName());
    private static MessageSender instance;

    private static OutputStream outStream;

    private final Queue<OrderDetail> listToSend = new LinkedList();
    private final Object msgLock = new Object();
    private final Object newMsgLock = new Object();

    private final String getClientIP = "SELECT address FROM clientip "
            + "WHERE clientid=?";

    public MessageSender() {
        logger.log(Level.INFO, String.format("new MessageSender instance initiated. %s", this.toString()));
    }

    /**
     * Create new instance of MessageSender and start Thread
     *
     * @return
     */
    public static synchronized MessageSender getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new MessageSender();
        Thread t = new Thread(instance);
        t.setName("MessageSender");
        t.start();
        return instance;
    }

    public void addMessageToSend(OrderDetail msg) {
        logger.log(Level.INFO, String.format("New order ready msg to send: %s", msg.toString()));
        synchronized (msgLock) {
            listToSend.add(msg);
        }
        synchronized (newMsgLock) {
            newMsgLock.notify();
        }
    }

    public Queue<OrderDetail> getListToSend() {
        return listToSend;
    }

    @Override
    public void run() {
//        while (true) {
//
//            if (listToSend.isEmpty()) {
//                //  If list is empty wait till 
//                //  list gets added with new message
//                synchronized (newMsgLock) {
//                    try {
//                        newMsgLock.wait();
//                    } catch (InterruptedException ex) {
//                        logger.log(Level.WARNING, "Interrupted: {0}", ex);
//                        return;
//                    }
//                }
//            } else {
//                logger.log(Level.INFO, "Start sending messages: {0}", listToSend.size());
////                while (listToSend.size() > 0) {
////                    OrderDetail readyOrder = listToSend.poll();
////                    try {
////                        String clientIp = getClientIP(readyOrder.getUid());
////                        if (clientIp != null) {
////                            Socket socket = new Socket(InetAddress.getByName(clientIp), Constants.CLIENT_PORT);
////                            logger.info(String.format("Connected to client user @%s:%s. Will send message.", clientIp, Constants.CLIENT_PORT));
////
////                            outStream = socket.getOutputStream();
////                            //TO DO generate order ready message and send to output stream
////
////                        } else {
////                            logger.log(Level.WARNING, String.format("[User@id: %s] has no IP Address specified in the system.",
////                                    readyOrder.getUid()));
////                        }
////                    } catch (SQLException ex) {
////                        logger.log(Level.SEVERE, null, ex);
////                    } catch (UnknownHostException ex) {
////                        logger.log(Level.SEVERE, null, ex);
////                    } catch (IOException ex) {
////                        logger.log(Level.SEVERE, null, ex);
////                    }
////                }//WHILE
//            }// IF ELSE
//
//        }//WHILE
    }

    /**
     * Get remote client IP Address
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public String getClientIP(int uid) throws SQLException {
        Connection connection = (Connection) DBConnector.getConnection();
        PreparedStatement pStat = (PreparedStatement) connection.prepareStatement(getClientIP);
        pStat.setInt(1, uid);
        if (pStat.execute()) {
            //Should return single row
            ResultSet rs = pStat.getResultSet();
            rs.first();
            String ip = rs.getString("address");
            rs.close();
            pStat.close();
            return ip;
        } else {
            return null;
        }
    }
}
