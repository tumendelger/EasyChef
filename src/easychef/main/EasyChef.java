package easychef.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import easychef.ui.LoginManager;
import easychef.ui.SplashScreenController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tumee
 */
public class EasyChef extends Application {

    Scene scene;    
    LoginManager loginMgr;

    @Override
    public void start(final Stage primaryStage) {

        loginMgr = new LoginManager(primaryStage);
        scene = new Scene(new StackPane());
        loginMgr.setScene(scene);        
        loginMgr.showSplashScreen();
//        loginMgr.showLogin();
//        loginMgr.showMain();
        
        primaryStage.getIcons().add(new Image("easychef/resources/Chef_45_45.png"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("EasyChef");
        primaryStage.opacityProperty().set(0);
        primaryStage.toBack();
        primaryStage.show();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
