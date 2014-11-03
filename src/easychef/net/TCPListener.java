/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import easychef.data.Constants;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Listen on server IP and PORT for incoming messages from waiter e.g order,
 * order update, cancel order etc..,
 *
 * @author tumee
 */
public class TCPListener extends Thread {

    private static final Logger logger = Logger.getLogger(TCPListener.class.getName());
    public static boolean shutdown;
    private static ServerSocket serverSocket;
    private static Socket accept;
    private String serverAddress;
    private int serverPort;
    private StringProperty serverStatusText = new SimpleStringProperty();
    private boolean serverStatus;

    public TCPListener(String threadName) {
        super.setName(threadName);
    }

    @Override
    public void run() {
        try {
            /**
             * Create shutdown hook for new thread instance
             */
            Runtime.getRuntime().addShutdownHook(new RunWhenShutdown());

            //Create server socket at given port
            serverSocket = new ServerSocket(this.serverPort);
            logger.info(String.format("Server socket created successfully @%s port.", serverSocket.getLocalPort()));
            setServerStatus(true);
            while (!shutdown) {

                //Wait for new connection
                logger.info("Waiting for new connection . . .");
                accept = serverSocket.accept();

                //Create new handler instance for each connection                
                new ClientHandler(accept).start();

            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
            setServerStatus(false);
        }
    }

    public void setShutdown(boolean shutdown) {
        TCPListener.shutdown = shutdown;
    }

    private static class RunWhenShutdown extends Thread {

        public RunWhenShutdown() {
        }

        @Override
        public void run() {
            try {
                serverSocket.close();
                System.out.println("Server Socket closed.");
            } catch (IOException ex) {
                Logger.getLogger(TCPListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getServerAddress() {
        return this.serverAddress;
    }

    public void setServerAddress(String address) {
        this.serverAddress = address;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void setServerPort(int port) {
        this.serverPort = port;
    }

    public void setServerStatusText(String statusMessage) {
        this.serverStatusText.set(statusMessage);
    }

    public StringProperty getServerStatusText() {
        return this.serverStatusText;
    }

    public void setServerStatus(boolean status) {
        this.serverStatus = status;
    }

    public boolean isUp() {
        return this.serverStatus;
    }
}
