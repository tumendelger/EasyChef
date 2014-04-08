/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 * Sends message for every 30 seconds to keep session alive
 *
 * @author tumee
 */
public class MessageSender extends Thread {

    private static final Logger logger = Logger.getLogger(MessageSender.class.getName());
    private static MessageSender instance;

    private static OutputStream outStream;

//    private final Queue<Message> listToSend = new LinkedList();
    private final Object msgLock = new Object();
    private final Object newMsgLock = new Object();

    public MessageSender() throws JAXBException {
        logger.log(Level.INFO, String.format("new MessageSender instance initiated. %s", this.toString()));
        getInstance();
    }

    public static synchronized MessageSender getInstance() throws JAXBException {
        if (instance != null) {
            return instance;
        }

        instance = new MessageSender();
        Thread t = new Thread(instance);
        t.setName("MessageSender");
        t.start();
        return instance;
    }

//    public void addMessageToSend(Message msg) {
//        logger.log(Level.INFO, String.format("New msg to send: %s", msg.toString()));
//        synchronized (msgLock) {
//            listToSend.add(msg);
//        }
//        synchronized (newMsgLock) {
//            newMsgLock.notify();
//        }
//    }

//    public Queue<Message> getListToSend() {
//        return listToSend;
//    }

    public static OutputStream getOutStream() {
        return outStream;
    }

    public static void setOutStream(OutputStream outStream) {
        MessageSender.outStream = outStream;
    }

    @Override
    public void run() {
        while (true) {

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
//                if (outStream == null) {
//                    logger.log(Level.SEVERE, "Output Stream can NOT be NULL");
//                } else {
//                    while (listToSend.size() > 0) {
//                        
//                    }
//                }// IF ELSE
//            }// IF ELSE

        }//WHILE
    }
}
