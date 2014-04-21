/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles client data
 *
 * @author tumee
 */
public class ClientHandler extends Thread {

    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    private final Socket socket;
    private static int clientID = 1;
    private BufferedReader buffReader;
    private final String delim = "=";
    private String[] inMsg;

    public ClientHandler(Socket _socket) {
        super("cHandlerThread#" + clientID);
        logger.info(String.format("ClientHandler created. ThreadID: [%s]", Thread.currentThread().getName()));
        logger.info(String.format("New client connected from: %s", _socket.getInetAddress().toString()));
        socket = _socket;
        clientID++;
    }

    @Override
    public void run() {
        try {
            //Prepare reader
            buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //read incoming string msg
            String line = buffReader.readLine();

            //Split incoming string by delimiter
            inMsg = line.split(delim);

            if (inMsg.length != 2) {
                //Our message should have structure like 'type'='id'
                logger.warning(String.format("Received incorrect message. Message[%s]", line));
                logger.info("Closing socket connection.");
                socket.close();
            } else {
                Message newMsg = new Message();

                /**
                 * sets newly created message type
                 */
                if (inMsg[0].equalsIgnoreCase("PRINT")) {
                    newMsg.setMtype(Message.msgType.PRINT);
                }
                if (inMsg[0].equalsIgnoreCase("CANCEL")) {
                    newMsg.setMtype(Message.msgType.CANCEL);
                }
                if (inMsg[0].equalsIgnoreCase("ORDER")) {
                    newMsg.setMtype(Message.msgType.ORDER);
                }
                if (inMsg[0].equalsIgnoreCase("UPDATE")) {
                    newMsg.setMtype(Message.msgType.UPDATE);
                }
                if (inMsg[0].equalsIgnoreCase("UPDATE_DRINK")) {
                    newMsg.setMtype(Message.msgType.UPDATE_DRINK);
                }
                if (inMsg[0].equalsIgnoreCase("ALL")) {
                    newMsg.setMtype(Message.msgType.ALL);
                }
                if (inMsg[0].equalsIgnoreCase("DELIVER")) {
                    newMsg.setMtype(Message.msgType.DELIVER);
                }

                //Set new msg id
                newMsg.setId(Long.parseLong(inMsg[1]));
                
                ClientMessageHandler.getInstance().addClientMsg(newMsg);
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
