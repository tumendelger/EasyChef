/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author tumee
 */
public class TCPConnector extends Thread {

    private static final Logger logger = Logger.getLogger(TCPConnector.class.getName());
    private static Socket socket;
    private static InputStream inStream;
    private static OutputStream outStream;
    private static BufferedReader _inFromServer;
    private static int port;
    private static String address;

    private MessageSender sender;

    public TCPConnector(String name, String remoteAddress, int remotePort) throws UnknownHostException, IOException, JAXBException {
        super.setName(name);
        setPort(remotePort);
        setAddress(remoteAddress);

        logger.log(Level.INFO, String.format("Connecting server socket at: {%s:%s}", getAddress(), getPort()));
        socket = new Socket(InetAddress.getByName(getAddress()), getPort());

        logger.log(Level.INFO, String.format("Succesfully connected to server socket. {%s}", socket.toString()));

        setInStream(socket.getInputStream());
        logger.log(Level.INFO, String.format("Set input stream. %s", inStream.toString()));

        setOutStream(socket.getOutputStream());
        logger.log(Level.INFO, String.format("Set output stream. %s", outStream.toString()));

        sender = MessageSender.getInstance();
        MessageSender.setOutStream(getOutStream());

    }

    @Override
    public void run() {
        while (true) {
            if (socket.isConnected()) {
                
            } else {
                logger.log(Level.WARNING, "Server socket is disconnected.");
            }
        }
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        TCPConnector.port = port;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        TCPConnector.address = address;
    }

    public static InputStream getInStream() {
        return inStream;
    }

    public static void setInStream(InputStream inStream) {
        TCPConnector.inStream = inStream;
    }

    public static OutputStream getOutStream() {
        return outStream;
    }

    public static void setOutStream(OutputStream outStream) {
        TCPConnector.outStream = outStream;
    }

}
