/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.ui;

import easychef.data.User;
import easychef.data.exceptions.UserNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author tumee
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginPane;
    @FXML
    private VBox containerVBox;
    @FXML
    private Pane headerPane;
    @FXML
    private ImageView logo;
    @FXML
    private Pane containerPane;
    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField username;
    @FXML
    private Label userFieldEmpty;
    @FXML
    private Insets x1;
    @FXML
    private Insets x2;
    @FXML
    private PasswordField password;
    @FXML
    private Label pwdFieldEmpty;
    @FXML
    private CheckBox rememberMe;
    @FXML
    private Font x3;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonCancel;
    @FXML
    private Pane footerPane;
    @FXML
    private Label errorMsg;

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    private LoginManager loginMgr;
    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rememberMe.setFocusTraversable(true);
        username.setFocusTraversable(true);
        password.setFocusTraversable(true);
        buttonLogin.setFocusTraversable(true);
        buttonCancel.setFocusTraversable(true);

        //Load saved user instance
        File userFile = new File("user");
        if (userFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(userFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                user = (User) ois.readObject();
                username.setText(user.getUname());
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        password.requestFocus();
                        rememberMe.setSelected(true);
                    }
                });
            } catch (FileNotFoundException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void login() {
        if (validateEmptyFields()) {
            try {
                /* * * * * * * * * * * * * * * * *
                 * Authorization logic goes here *
                 * * * * * * * * * * * * * * * * */
                user = new User(username.getText());
                if (loginMgr.authenticateUser(user, password.getText())) {
                    File userFile = new File("user");
                    if (rememberMe.isSelected()) {
                        logger.info("Checking existing file");
                        if (userFile.exists()) {
                            saveUser(userFile);
                        } else {
                            logger.info("File not exists. Creating new one.");
                            try {
                                userFile.createNewFile();
                                saveUser(userFile);
                            } catch (IOException ex) {
                                logger.log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        if (userFile.exists()) {
                            userFile.delete();
                        }
                    }
                    loginMgr.showMain();
                } else {
                    errorMsg.setText("Хэрэглэгчийн нэр, нууц үгээ шалгана уу.");
                }
            } catch (SQLException ex) {
                errorMsg.setText("Error 100: Холболтын алдаа гарлаа.");
                logger.log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                errorMsg.setText("Error 101: UnSupported Enconding");
                logger.log(Level.SEVERE, null, ex);
            } catch (UserNotFoundException ex) {
                errorMsg.setText("Error 102: Хэрэглэгч олдсонгүй.");
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void exit() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Validates whether input fields are empty
     *
     * @return
     */
    private boolean validateEmptyFields() {
        if (username.getText().isEmpty()) {
            showLabel(userFieldEmpty);
            username.requestFocus();
            return false;
        } else {
            hideLabel(userFieldEmpty);
            if (password.getText().isEmpty()) {
                showLabel(pwdFieldEmpty);
                password.requestFocus();
                return false;
            } else {
                hideLabel(pwdFieldEmpty);
                return true;
            }
        }

    }

    private void showLabel(Label label) {
        if (!label.isVisible()) {
            label.setVisible(true);
        }
    }

    private void hideLabel(Label label) {
        if (label.isVisible()) {
            label.setVisible(false);
        }
    }

    public void setLoginMgr(final LoginManager loginMgr) {
        this.loginMgr = loginMgr;
    }

    public void saveUser(File userFile) {
        try {
            FileOutputStream fos = new FileOutputStream(userFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new User(user.getUname()));
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
