package easychef.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import easychef.ui.LoginManager;
import javafx.application.Application;
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
    
    @Override
    public void start(final Stage primaryStage) {
        final LoginManager loginMgr = new LoginManager(primaryStage);
        final Scene scene = new Scene(new StackPane());
        loginMgr.setScene(scene);
        
//        loginMgr.showLogin();
        loginMgr.showMain();
        primaryStage.getIcons().add(new Image("easychef/resources/Chef_45_45.png"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("EasyChef");
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
