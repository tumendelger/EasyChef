/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.ui;

import easychef.data.FoodOrderDetail;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author tumee
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane containerPane;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TableView<FoodOrderDetail> orderTable;
    @FXML
    private Label noOfLowWaits;
    @FXML
    private Label noOfMediumWaits;
    @FXML
    private Label noOfHighWaits;
    @FXML
    private Label noOfCancelOrdersWithoutCost;
    @FXML
    private Label noOfCancelOrdersWithCost;
    @FXML
    private Label serverStatusLabel;
    @FXML
    private AnchorPane footerPane;
    @FXML
    private AnchorPane headerPane;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnMaximize;
    @FXML
    private Button btnMinimize;

    /**
     * Internal variable declarations
     */
    private TableColumn<FoodOrderDetail, Integer> tableid;
    private TableColumn<FoodOrderDetail, String> foodName;
    private TableColumn<FoodOrderDetail, List> changes;
    private TableColumn<FoodOrderDetail, Integer> waitTime;
    private TableColumn<FoodOrderDetail, String> orderTime;

    private ObservableList orderData;
    private LoginManager loginMgr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTableView();
//        connecServer("TCPConnector", "localhost", 4444);
    }

    private void initTableView() {
        // Initialize new table column instances
        tableid = new TableColumn("Ширээ");
        foodName = new TableColumn("Хоолны нэр");
        changes = new TableColumn("Өөрчлөлт");
        waitTime = new TableColumn("Хүлээлт");
        orderTime = new TableColumn("Захиалсан");

        // Total lenght of Table View is 892
        // Setting table column lenghts
        tableid.setMinWidth(80);
        tableid.setMaxWidth(80);
        
        foodName.setMinWidth(480);
        foodName.setMaxWidth(480);
        
        changes.setMinWidth(120);
        changes.setMaxWidth(120);
        
        waitTime.setMinWidth(80);
        waitTime.setMaxWidth(80);
        
        orderTime.setMinWidth(130);
        orderTime.setMaxWidth(130);

        // Set table cell data factory
//        tableid.setCellValueFactory(new PropertyValueFactory<FoodOrderDetail, Integer>("tableID"));
        tableid.setCellValueFactory(new PropertyValueFactory<FoodOrderDetail, Integer>("tableID"));
        tableid.setCellFactory(new Callback<TableColumn<FoodOrderDetail, Integer>, TableCell<FoodOrderDetail, Integer>>() {

            @Override
            public TableCell<FoodOrderDetail, Integer> call(TableColumn<FoodOrderDetail, Integer> p) {
                final TableCell<FoodOrderDetail, Integer> cell = new TableCell<FoodOrderDetail, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            setText(item.toString());
                            setGraphic(null);
                        }
                    }

                };
                cell.setStyle("-fx-alignment: CENTER;"
                        + "-fx-text-fill: #000;");
                return cell;
            }
        });

        // Has default text alignment RIGHT
        foodName.setCellValueFactory(new PropertyValueFactory<FoodOrderDetail, String>("foodName"));

        changes.setCellValueFactory(new PropertyValueFactory<FoodOrderDetail, List>("changes"));
        changes.setCellFactory(new Callback<TableColumn<FoodOrderDetail, List>, TableCell<FoodOrderDetail, List>>() {

            @Override
            public TableCell<FoodOrderDetail, List> call(TableColumn<FoodOrderDetail, List> p) {
                final TableCell<FoodOrderDetail, List> cell = new TableCell<FoodOrderDetail, List>() {
                    private Text text;

                    @Override
                    public void updateItem(List item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            if (item.size() > 0) {
                                String changeItem = "";

                                // Add items text
                                for (int i = 0; i < item.size(); i++) {
                                    changeItem = changeItem + item.get(i) + "\n";
                                }
                                setText(changeItem);
                            }
                        }
                        setGraphic(text);
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;"
                        + "-fx-text-fill: #0080FF;"
                        + "-fx-font: bold 12 \"Times New Roman\";");
                return cell;
            }
        });

        waitTime.setCellValueFactory(new PropertyValueFactory<FoodOrderDetail, Integer>("waitMinutes"));

        waitTime.setCellFactory(new Callback<TableColumn<FoodOrderDetail, Integer>, TableCell<FoodOrderDetail, Integer>>() {

            @Override
            public TableCell<FoodOrderDetail, Integer> call(TableColumn<FoodOrderDetail, Integer> p) {
                final TableCell<FoodOrderDetail, Integer> cell = new TableCell<FoodOrderDetail, Integer>() {
                    private Text text;

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            setText(item.toString());

                            if (item.intValue() < 10) {
                                setTextFill(Paint.valueOf("#35AA74"));
                            } else {
                                if (item.intValue() < 15) {
                                    setTextFill(Paint.valueOf("#ffb848"));
                                } else {
                                    setTextFill(Paint.valueOf("#ff3f3f"));
                                }
                            }
                            setGraphic(text);
                        }
                    }

                };
                cell.setStyle("-fx-alignment: CENTER;"
                        + "-fx-font: bold 24 \"Verdana\";");

                return cell;
            }
        });

        orderTime.setCellValueFactory(new PropertyValueFactory<FoodOrderDetail, String>("orderTime"));
        orderTime.setCellFactory(new Callback<TableColumn<FoodOrderDetail, String>, TableCell<FoodOrderDetail, String>>() {

            @Override
            public TableCell<FoodOrderDetail, String> call(TableColumn<FoodOrderDetail, String> p) {
                final TableCell<FoodOrderDetail, String> cell = new TableCell<FoodOrderDetail, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            setText(item.toString());
                            setGraphic(null);
                        }
                    }

                };
                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        });

        orderTable.getColumns().addAll(tableid, foodName, changes, waitTime, orderTime);
    }

    /**
     * Exit application
     *
     * @throws SQLException
     */
    @FXML
    public void exit() throws SQLException {
        // Logout connected user first
//        loginMgr.getUser().userLog(Constants.USER_LOGGED_OUT);
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void minimize() {
        loginMgr.getpStage().setIconified(true);
    }

    @FXML
    public void maximize(){
    }
    
    public void setLoginMgr(final LoginManager loginMgr) {
        this.loginMgr = loginMgr;
    }

}
