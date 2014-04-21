/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.ui;

import easychef.data.OrderDetail;
import easychef.net.ClientMessageHandler;
import easychef.net.Message;
import static easychef.net.Message.msgType.ALL;
import static easychef.net.Message.msgType.C_CANCEL_NO_COST;
import static easychef.net.Message.msgType.C_CANCEL_WITH_COST;
import static easychef.net.Message.msgType.DELIVER;
import easychef.net.OrderHandler;
import easychef.net.TCPListener;
import java.awt.MouseInfo;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author tumee
 */
public class MainController implements Initializable {

    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    @FXML
    private AnchorPane containerPane;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TableView<OrderDetail> orderTable;
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
    private TableColumn<OrderDetail, Integer> tableid;
    private TableColumn<OrderDetail, String> foodName;
    private TableColumn<OrderDetail, List<String>> changes;
    private TableColumn<OrderDetail, Integer> waitTime;
    private TableColumn<OrderDetail, String> orderTime;
    private TableColumn<OrderDetail, Boolean> wCancelled;

    //
    private OrderDetail orderToProcess;
    private ContextMenu tableMenu;
    private MenuItem deliverOrder;
    private MenuItem cancelWithoutCost;
    private MenuItem cancelWithCost;
    private MenuItem doNothing;

    public static final ObservableList<OrderDetail> orderData = FXCollections.observableArrayList();
    private final TCPListener listener = new TCPListener("ServerThread");
    private WaitTimeUpdater wtUpdater;
    private ClientMessageHandler mHandler;
    private OrderHandler oHandler;
    private LoginManager loginMgr;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize TableView first
        initTableView();

        //next load if there is any orders not completed
        //Set TableView items as orderData
        orderTable.setItems(orderData);

        //Initialize TCPListener
        listener.start();

        //initialize cMHandler
        mHandler = ClientMessageHandler.getInstance();
        ClientMessageHandler.setOrders(orderData);

        //Initialize oHandler
        oHandler = OrderHandler.getInstance();

        //Load all orders in the system
        Message all = new Message(ALL, 0);
        mHandler.addClientMsg(all);

        //Initialize wtUpdater thread
        wtUpdater = new WaitTimeUpdater();
        wtUpdater.setOrders(orderData);
        wtUpdater.start();
    }

    /**
     * Initializes TableView
     */
    private void initTableView() {
        // Initialize new table column instances
        logger.info("Initializing TableView.");
        tableid = new TableColumn("Ширээ");
        foodName = new TableColumn("Хоолны нэр");
        changes = new TableColumn("Өөрчлөлт");
        waitTime = new TableColumn("Хүлээлт");
        orderTime = new TableColumn("Захиалсан");
        wCancelled = new TableColumn(" ");

        // Total lenght of Table View is 892
        // Setting table column lenghts
        tableid.setMinWidth(80);
        tableid.setMaxWidth(80);

        foodName.setMinWidth(417);
        foodName.setMaxWidth(417);

        changes.setMinWidth(150);
        changes.setMaxWidth(150);

        waitTime.setMinWidth(80);
        waitTime.setMaxWidth(80);

        orderTime.setMinWidth(130);
        orderTime.setMaxWidth(130);

        wCancelled.setMinWidth(35);
        wCancelled.setMaxWidth(35);

        // Set table cell data factory
        tableid.setCellValueFactory(new PropertyValueFactory<>("tableid"));
        tableid.setCellFactory(new Callback<TableColumn<OrderDetail, Integer>, TableCell<OrderDetail, Integer>>() {

            @Override
            public TableCell<OrderDetail, Integer> call(TableColumn<OrderDetail, Integer> param) {
                final TableCell<OrderDetail, Integer> cell = new TableCell<OrderDetail, Integer>() {
                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            setText(item.toString());

                        } else {
                            //we have to set value to null since no content shown
                            setText(null);
                        }

                        setGraphic(null);
                    }
                };
                return cell;
            }

        });

        // Has default text alignment RIGHT
        foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodName.setCellFactory(new Callback<TableColumn<OrderDetail, String>, TableCell<OrderDetail, String>>() {

            @Override
            public TableCell<OrderDetail, String> call(TableColumn<OrderDetail, String> param) {
                final TableCell<OrderDetail, String> cell = new TableCell<OrderDetail, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            setText(item);
                        } else {
                            //we have to set value to null since no content shown
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        changes.setCellValueFactory(new PropertyValueFactory<>("changes"));
        changes.setCellFactory(new Callback<TableColumn<OrderDetail, List<String>>, TableCell<OrderDetail, List<String>>>() {

            @Override
            public TableCell<OrderDetail, List<String>> call(TableColumn<OrderDetail, List<String>> p) {
                final TableCell<OrderDetail, List<String>> cell = new TableCell<OrderDetail, List<String>>() {
                    private Text text;

                    @Override
                    public void updateItem(List<String> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            if (item.size() > 0) {
                                String changeItem = "";

                                // Add items text
                                for (String change : item) {
                                    changeItem = changeItem + change + " ";
                                }
                                setText(changeItem);
                            }
                        } else {
                            //we have to set value to null since no content shown
                            setText(null);
                        }
                        setGraphic(null);
                    }
                };
                cell.setId("changes");
                return cell;
            }
        });

        waitTime.setCellValueFactory(new PropertyValueFactory<>("waitTime"));
        waitTime.setCellFactory(new Callback<TableColumn<OrderDetail, Integer>, TableCell<OrderDetail, Integer>>() {

            @Override
            public TableCell<OrderDetail, Integer> call(TableColumn<OrderDetail, Integer> p) {
                final TableCell<OrderDetail, Integer> cell = new TableCell<OrderDetail, Integer>() {
                    private Text text;

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            setText(item.toString());

                            if (item < 10) {
                                //Low wait orders
                                setTextFill(Paint.valueOf("#35AA74"));
//                                this.setId("low-wait");
                            } else {
                                if (item > 15) {
                                    //High wait order
                                    setTextFill(Paint.valueOf("#ff3f3f"));
//                                    this.setId("high-wait");
                                } else {
                                    setTextFill(Paint.valueOf("#ffb848"));
//                                    this.setId("medium-wait");
                                }
                            }
                            setGraphic(null);
                        } else {
                            //we have to set value to null since no content shown
                            setText(null);
                        }
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;"
                        + "-fx-font: bold 24 \"Verdana\";");

                return cell;
            }
        });

        orderTime.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        orderTime.setCellFactory(new Callback<TableColumn<OrderDetail, String>, TableCell<OrderDetail, String>>() {

            @Override
            public TableCell<OrderDetail, String> call(TableColumn<OrderDetail, String> p) {
                final TableCell<OrderDetail, String> cell = new TableCell<OrderDetail, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            setText(item);
                        } else {
                            //we have to set value to null since no content shown
                            setText(null);
                        }
                        setGraphic(null);
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        });

        wCancelled.setCellValueFactory(new PropertyValueFactory<>("waiterCancelled"));
        wCancelled.setCellFactory(new Callback<TableColumn<OrderDetail, Boolean>, TableCell<OrderDetail, Boolean>>() {

            @Override
            public TableCell<OrderDetail, Boolean> call(TableColumn<OrderDetail, Boolean> param) {
                final TableCell<OrderDetail, Boolean> cell = new TableCell<OrderDetail, Boolean>() {
                    ImageView btnImage;
                    Image cncld;

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {

//                            logger.info("Order has been updated");
                            if (item) {
                                //This order has been cancelled
                                //set image black image
                                cncld = new Image("easychef/resources/cancel.png");
                                this.setId("cancelled-order");
                            } else {
                                //This order is not cancelled
                                //set white image
                                cncld = new Image("easychef/resources/normal.png");
                                this.setId("normal-order");
                            }

                            btnImage = new ImageView(cncld);
                            btnImage.setFitHeight(24);
                            btnImage.setFitWidth(24);
                            setGraphic(btnImage);
                        } else {
                            //we have to set value to null since no content shown
                            setText(null);
                        }
                    }
                };
                cell.alignmentProperty().setValue(Pos.CENTER);
                return cell;
            }
        });

        //OrderTable selection listener
        orderTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        orderTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetail>() {

            @Override
            public void changed(ObservableValue<? extends OrderDetail> observable, OrderDetail oldValue, OrderDetail newValue) {
                //Notify index is changes
                if (orderTable.getSelectionModel().getSelectedIndex() >= 0) {
                    orderToProcess = orderTable.getSelectionModel().getSelectedItem();
                    showMenu();
                }
            }
        });

        orderTable.getColumns().addAll(tableid, foodName, changes, wCancelled, waitTime, orderTime);
        orderTable.tableMenuButtonVisibleProperty().set(false);
        logger.info("TableView initialization done.");

        tableMenu = new ContextMenu();

        deliverOrder = new MenuItem("Захиалга хүргэх");
        deliverOrder.setOnAction((ActionEvent event) -> {
            logger.info(String.format("Deliver order: %s", orderToProcess.toString()));
            oHandler.addOrder(new Pair(DELIVER, orderToProcess));
            orderTable.getSelectionModel().clearSelection();
            orderData.remove(orderToProcess);

        });

        cancelWithCost = new MenuItem("Зарлагатай буцаалт");
        cancelWithCost.setOnAction((ActionEvent event) -> {
            logger.info(String.format("Cancel order with cost: %s", orderToProcess.toString()));
            oHandler.addOrder(new Pair(C_CANCEL_WITH_COST, orderToProcess));
            orderTable.getSelectionModel().clearSelection();
            orderData.remove(orderToProcess);
        });

        cancelWithoutCost = new MenuItem("Зарлагагүй буцаалт");
        cancelWithoutCost.setOnAction((ActionEvent event) -> {
            logger.info("Cancel without cost");
            oHandler.addOrder(new Pair(C_CANCEL_NO_COST, orderToProcess));
            orderTable.getSelectionModel().clearSelection();
            orderData.remove(orderToProcess);
        });

        tableMenu.getItems().addAll(deliverOrder, cancelWithCost, cancelWithoutCost);
        tableMenu.setOnHidden((WindowEvent event) -> {
            orderTable.getSelectionModel().clearSelection();
        });
        orderTable.setContextMenu(tableMenu);
    }

    private void showMenu() {

        if (orderToProcess.isWaiterCancelled()) {
            deliverOrder.setDisable(true);
            cancelWithCost.setDisable(false);
            cancelWithoutCost.setDisable(false);
        } else {
            deliverOrder.setDisable(false);
            cancelWithCost.setDisable(true);
            cancelWithoutCost.setDisable(true);
        }
//        orderTable.getSelectionModel().clearSelection();
        orderTable.getContextMenu().show(orderTable, MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
    }

    /**
     * We have to interact TableView items within from JavaFX application thread
     * To do this need to invoke runlater method of Platform
     */
    public static void applyUpdateToUI() {

        OrderDetail last = orderData.get(orderData.size() - 1);
        orderData.remove(last);

        Platform.runLater(() -> {
            try {
                Thread.sleep(100);
                orderData.add(last);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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

    /**
     * Minimize application window
     */
    @FXML
    public void minimize() {
        //Minimize user interface
        loginMgr.getpStage().setIconified(true);
    }

    /**
     * Maximize or it set application to full screen mode
     */
    @FXML
    public void maximize() {
        loginMgr.maximizeScreen();
        splitPane.setDividerPositions(0.70);
    }

    /**
     * Sets login manager.
     *
     * @param loginMgr
     */
    public void setLoginMgr(final LoginManager loginMgr) {
        this.loginMgr = loginMgr;
    }

}
