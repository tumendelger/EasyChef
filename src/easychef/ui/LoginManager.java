/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.ui;

import easychef.data.Constants;
import easychef.data.User;
import easychef.data.exceptions.UserNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  Login manager handles window action between Login screen 
 * and Main screen of the EasyChef application
 * @author tumee
 */
public final class LoginManager {

    private static final Logger logger = Logger.getLogger(LoginManager.class.getName());
    private Scene scene;
    private FXMLLoader loader;
    private LoginController loginController;
    private MainController mController;
    private final Stage pStage;
    private User user;
    private String HASHED_PWD;

    public LoginManager(final Stage pStage) {
        this.pStage = pStage;
    }

    public void showLogin() {
        /**
         * Show login screen 
         */
        try {
            loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            scene.setRoot((Parent) loader.load());
            pStage.setScene(scene);
            pStage.sizeToScene();
            pStage.centerOnScreen();
            loginController = loader.getController();
            loginController.setLoginMgr(this);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Authenticate user object with password
     *
     * @param user User object
     * @param pwd given password
     * @return true if user found in the system and password matches, otherwise
     * return false
     * @throws SQLException
     * @throws UnsupportedEncodingException
     * @throws easychef.data.exceptions.UserNotFoundException
     */
    public boolean authenticateUser(User user, String pwd) throws SQLException, UnsupportedEncodingException, UserNotFoundException {
        /************************
         * Authentication Logic * 
         ************************/
        this.user = user;
        user.getUserDetails();

        calculateHash(pwd);
        if (user.getPassword().equalsIgnoreCase(HASHED_PWD)) {
            int role = user.getRoleid();
            if (role == Constants.CHEF_ROLE_ID
                    || role == Constants.ADMIN_ROLE_ID
                    || role == Constants.SUPER_ADMIN_ROLE_ID
                    || role == Constants.MANEGER_ROLE_ID) {
                user.userLog(Constants.USER_LOGGED_IN);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Show main window of the EasyChef application
     */
    public void showMain() {        
        try {
            loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            scene.setRoot((Parent) loader.load());
            pStage.setScene(scene);
            pStage.sizeToScene();
            pStage.centerOnScreen();
            mController = loader.getController();
            mController.setLoginMgr(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /*
     * Logs out user.
     * Shows login screen to the main window
     */
    public void logOut(){
        try {
            user.userLog(Constants.USER_LOGGED_OUT);
            showLogin();
        } catch (SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Calculate HASH String representation of the given password String
     *
     * @param pwd String
     * @throws UnsupportedEncodingException
     */
    private void calculateHash(String pwd) throws UnsupportedEncodingException {
        try {
            //generates MD5 Hex String
            MessageDigest md = MessageDigest.getInstance("MD5");
            String md5String = getHexString(md.digest(pwd.getBytes("UTF-8")));

            //generates SHA1 Hex String from MD5 string
            md = MessageDigest.getInstance("SHA1");
            HASHED_PWD = getHexString(md.digest(md5String.getBytes()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Generate HEX String for given byte array
     *
     * @param array byte array which will be converted to HEX string
     * @return
     */
    private String getHexString(byte[] array) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            String hex = Integer.toHexString(0xff & array[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getpStage() {
        return pStage;
    }

}
