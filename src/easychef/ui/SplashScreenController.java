/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.ui;

import easychef.remote.DBSyncer;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author tumee
 */
public class SplashScreenController implements Initializable {

    @FXML
    private Label appMessage;

    @FXML
    private ProgressBar bar;

    @FXML
    private Button btnExit;

    private LoginManager loginMgr;

    private final static Logger logger = Logger.getLogger(SplashScreenController.class.getName());
    private DBSyncer syncTask;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
        btnExit.setDisable(true);
        syncTask = new DBSyncer();

    }

    public void init() {
        Thread sync = new Thread(syncTask);
        sync.start();
        appMessage.textProperty().bind(syncTask.messageProperty());
        bar.progressProperty().bind(syncTask.progressProperty());

//        syncTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldState, Worker.State newState) {
//
//                if (newState == Worker.State.SUCCEEDED) {
//                    //Hides splash screen once sync done and show login screen
//                    loginMgr.hideSplash();
//                }
//
//                if (newState == Worker.State.FAILED) {
//                    logger.info("Failed");
//                }
//
//                if (newState == Worker.State.RUNNING) {
//                    logger.info("Running still");
//                }
//            }
//        });
        syncTask.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                switch (newValue) {
                    case 0:
                        loginMgr.hideSplash();
                        loginMgr.showLogin();
                        break;
                    case 101:
                        btnExit.setDisable(false);
                        break;
                    case 102:
                        btnExit.setDisable(false);
                        break;
                    case 103:
                        btnExit.setDisable(false);
                        break;
                }
            }
        });
    }

    public void nextScreen() {

    }

    public void setLoginMgr(LoginManager loginMgr) {
        this.loginMgr = loginMgr;
    }

    @FXML
    public void exit() {
        Platform.exit();
        System.exit(0);
    }

}
