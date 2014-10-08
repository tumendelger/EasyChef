/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import easychef.data.exceptions.OrderDetailNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Tumendelger
 */
public class OrderDetail {

    private final static Logger logger = Logger.getLogger(OrderDetail.class.getName());
    //Main order ID
    private long orderID;
    //Order Detail ID
    private long id;
    //Which table order belongs to
    private Integer tableid;
    //Food ID
    private int foodID;
    //Food Name
    private String foodName;
    //Is the order have changes
    private boolean hasChange;
    //If order detail has changes get list of changes to list
    private List<String> changes = new ArrayList();
    //Is the order delivered
    private boolean delivered;
    //Is the order cancelled from Waiter
    private Boolean waiterCancelled;
    //Is the order cancelled from Chef
    private boolean chefCancelled;
    //What time order has been ordered
    private String orderTime;
    //How long this order been waited since ordered
    private Integer waitTime = 0;
    //Main Category ID which group this order belongs to 
    //Food order or drink order 
    //1 = kitchen order, 2 - barmen order
    private boolean isDrink;
    //User id
    private int uid;

    //DB query Strings
    //Get all orders which are NOT delivered yet but still in the database
//    private final String findAllOrders = "SELECT orders.id," //field index in result set 1
//            + "forderdetails.id, " //2
//            + "orders.tid, " //3
//            + "foodid, " //4
//            + "foodmenu.name, " //5
//            + "haschange, " //6
//            + "isdelivered, " //7       
//            + "cancelbyw, " //8
//            + "cancelbych, " //9
//            + "orders.ordertime " //10
//            + "FROM orders, forderdetails, foodmenu "
//            + "WHERE orders.id=forderdetails.orderid "
//            + "AND foodmenu.id=foodid "
//            + "AND isdelivered=0 "
//            + "ORDER BY forderdetails.id ASC";
    private final String findAllOrders = "SELECT id "
            + "FROM orders "
            + "WHERE ispaid=0";

    //Get changes
    private final String getChanges = "SELECT name FROM changes, orderchange "
            + "WHERE changes.id=orderchange.cid "
            + "AND orderchange.fodid=?";

    //Get all order details i.e: orderid, ordertime etc..,
    private final String findDetailsByOrderID = "SELECT orders.id," //field index in result set 1
            + "forderdetails.id, " //2
            + "tables.tableid, " //3
            + "foodid, " //4
            + "foodmenu.name, " //5
            + "haschange, " //6
            + "isdelivered, " //7       
            + "cancelbyw, " //8
            + "cancelbych, " //9
            + "forderdetails.ordertime, " //10
            + "orders.uid " //11
            + "FROM orders, forderdetails, foodmenu, tables "
            + "WHERE orders.id=forderdetails.orderid "
            + "AND foodmenu.id=foodid "
            + "AND isdelivered=0 "
            + "AND orders.id=? "
            + "AND tables.id=orders.tid "
            + "ORDER BY forderdetails.id ASC";

    //Get all drink order details which sent to kitchen
    private final String getDrinksByOID = "SELECT orderid," //1
            + "dorderdetails.id, " //2
            + "orders.tid, " //3
            + "drinkid, " //4
            + "drinkmenu.name, " //5
            + "isdelivered, " //6
            + "cancelbyw, " //7
            + "cancelbych, " //8
            + "dorderdetails.ordertime, " //9
            + "orders.uid " //10
            + "FROM orders, dorderdetails, drinkmenu "
            + "WHERE orders.id=orderid "
            + "AND drinkmenu.id=dorderdetails.drinkid "
            + "AND isdelivered=0 "
            + "AND dorderdetails.mcid=1 "
            + "AND orders.id=? "
            + "ORDER BY dorderdetails.id ASC";

    //Get details of new food orderDetail by foid
    private final String getFoodOrderDetail = "SELECT orders.id," //field index in result set 1
            + "orders.tid, " //2
            + "foodid, " //3
            + "foodmenu.name, " //4
            + "haschange, " //5
            + "isdelivered, " //6
            + "cancelbyw, " //7
            + "cancelbych, " //8
            + "forderdetails.ordertime, " //9
            + "orders.uid " //10
            + "FROM orders, forderdetails, foodmenu "
            + "WHERE orders.id=forderdetails.orderid "
            + "AND foodmenu.id=forderdetails.foodid "
            + "AND forderdetails.id = ?";

    //Get details of new drink orderDetail by doid
    private final String getDrinkOrderDetail = "SELECT orders.id," //field index in result set 1
            + "orders.tid, " //2
            + "drinkid, " //3
            + "drinkmenu.name, " //4
            + "isdelivered, " //5    
            + "cancelbyw, " //6
            + "cancelbych, " //7
            + "dorderdetails.ordertime, " //8
            + "orders.uid " //9
            + "FROM orders, dorderdetails, drinkmenu "
            + "WHERE orders.id=dorderdetails.orderid "
            + "AND drinkmenu.id=dorderdetails.drinkid "
            + "AND dorderdetails.id = ?";

    //Deliver food order
    //Set table name depending on whether 
    //drink or food
    private final String deliverFoodOrder = "UPDATE forderdetails "
            + "SET isdelivered=?, "
            + "waittime=?, "
            + "cancelbyw=?, "
            + "cancelbych=? "
            + "WHERE id=?";

    private final String deliverDrinkOrder = "UPDATE dorderdetails "
            + "SET isdelivered=?, "
            + "waittime=?,"
            + "cancelbyw=?, "
            + "cancelbych=? "
            + "WHERE id=?";

    //JDBC MYSQL Connection
    private Connection connection;

    //SQL Statements
    private Statement stat;
    private PreparedStatement pStat;

    /**
     * Default empty constructor
     */
    public OrderDetail() {
    }

    /**
     *
     * @param orderID
     * @param id
     * @param tableid
     * @param foodID
     * @param foodName
     * @param hasChange
     * @param delivered
     * @param waiterCancelled
     * @param chefCancelled
     * @param orderTime
     * @param uid
     * @param isDrink
     */
    public OrderDetail(long orderID, long id, Integer tableid, int foodID, String foodName, boolean hasChange, boolean delivered, boolean waiterCancelled, boolean chefCancelled, String orderTime, int uid, boolean isDrink) {
        this.orderID = orderID;
        this.id = id;
        this.tableid = tableid;
        this.foodID = foodID;
        this.foodName = foodName;
        this.hasChange = hasChange;
        this.delivered = delivered;
        this.waiterCancelled = waiterCancelled;
        this.chefCancelled = chefCancelled;
        this.orderTime = orderTime;
        this.uid = uid;
        this.isDrink = isDrink;
    }

    /**
     * finds all order details which are available in the system and which are
     * not delivered yet
     *
     * @param tableData
     * @throws SQLException
     */
    public void getAllOrders(ObservableList<OrderDetail> tableData) throws SQLException {
        connection = (Connection) DBConnector.getConnection();
        stat = (Statement) connection.createStatement();

        if (stat.execute(findAllOrders)) {//Return true if result is ResultSet
            //Continue to process result set returned
            ResultSet rs = stat.getResultSet();
            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrderID(rs.getLong(1));
//                detail.getDrinksByOID(tableData);
                detail.getOrderDetailsByOID(tableData);
            }//WHILE
            rs.close();
            stat.close();
        }//Else there is no result set returned

    }

    /**
     *
     * @param tableData
     * @throws java.sql.SQLException
     */
    public void getOrderDetailsByOID(ObservableList<OrderDetail> tableData) throws SQLException {
        connection = (Connection) DBConnector.getConnection();
        logger.info(String.format("Connection created [%s]", connection.toString()));

        pStat = (PreparedStatement) connection.prepareStatement(findDetailsByOrderID);
        logger.info(String.format("Prepared statement created [%s]", pStat.toString()));

        pStat.setLong(1, getOrderID());
        logger.info(String.format("Executing prepared statement. [%s]", pStat.toString()));

        if (pStat.execute()) {//Returns true if result is ResultSet

            ResultSet rs = pStat.getResultSet();
            logger.info(String.format("Got result set. Start processing."));
            while (rs.next()) {
                OrderDetail detail = new OrderDetail(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getBoolean(9),
                        rs.getString(10),
                        rs.getInt(11),
                        false);

                //Get changes
                if (detail.hasChange) {
                    getDetailChanges(detail);
                }

                //Add new detail to the List                
                tableData.add(detail);
                logger.info(String.format("%s --> has added to orderData.", detail.toString()));
            }//WHILE

            rs.close();
            //Clear Prepared Statement
            pStat.close();

            // Check if there is drink to Kitchen
            pStat = (PreparedStatement) connection.prepareStatement(getDrinksByOID);
            pStat.setLong(1, getOrderID());

            if (pStat.execute()) {
                rs = pStat.executeQuery();
                while (rs.next()) {
                    OrderDetail kitchenDrink = new OrderDetail(
                            rs.getLong(1),
                            rs.getLong(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getString(5),
                            false, //Гал тогоонд ирж байгаа уух юмны захиалга өөрчлөлт байхгүй
                            rs.getBoolean(6),
                            rs.getBoolean(7),
                            rs.getBoolean(8),
                            rs.getString(9),
                            rs.getInt(10),
                            true);
                    tableData.add(kitchenDrink);
                }//WHILE
                //Close result set
                rs.close();
                //Close Prepared statement
                pStat.close();
            }//IF

        }//Else there is no result
    }

    /**
     * Хэрэв хоолны захиалга нэмэлт өөрчлөлттэй байвал өөрчлөлтүүдийг авна
     *
     * @param detail
     * @throws SQLException
     */
    public void getDetailChanges(OrderDetail detail) throws SQLException {
        PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement(getChanges);
        pStatement.setLong(1, detail.getId());
        try (ResultSet detailChanges = pStatement.executeQuery()) {
            while (detailChanges.next()) {
                detail.getChanges().add(detailChanges.getString(1));
            }//WHILE
            detailChanges.close();
        }
        //Read all the changes
        //Close prepared statement
        pStatement.close();
    }

    public void deliverOrder() {
        try {
            connection = (Connection) DBConnector.getConnection();

            if (!isDrink()) {
                //This is Food order
                pStat = (PreparedStatement) connection.prepareStatement(deliverFoodOrder);
            } else {
                //This is drink order
                pStat = (PreparedStatement) connection.prepareStatement(deliverDrinkOrder);
            }

            logger.info(String.format("Prepared Statement created: %s", pStat.getPreparedSql()));

            pStat.setInt(1, (isDelivered()) ? 1 : 0);
            pStat.setInt(2, getWaitTime());
            pStat.setInt(3, (isWaiterCancelled()) ? 1 : 0);
            pStat.setInt(4, (isChefCancelled()) ? 1 : 0);
            pStat.setLong(5, getId());

            logger.info(String.format("Delivering order: %s", this.toString()));
            logger.info(String.format("Executing query: %s", pStat.toString()));

            pStat.execute();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get Food OrderDetail values
     */
    public void getFoodDetails() {
        try {
            connection = (Connection) DBConnector.getConnection();
            pStat = (PreparedStatement) connection.prepareStatement(getFoodOrderDetail);

            logger.info(String.format("Prepared Statement created for query: %s", pStat.getPreparedSql()));

            pStat.setLong(1, getId());
            logger.info(String.format("Prepared Statement values set: %s", pStat.toString()));
            logger.info("Executing statement.");

            if (pStat.execute()) {
                ResultSet rs = pStat.getResultSet();
                //Should return only one row
                rs.next();
                setOrderID(rs.getLong(1));
                setTableid(rs.getInt(2));
                setFoodID(rs.getInt(3));
                setFoodName(rs.getString(4));
                setHasChange(rs.getBoolean(5));
                setDelivered(rs.getBoolean(6));
                setWaiterCancelled(rs.getBoolean(7));
                setChefCancelled(rs.getBoolean(8));
                setOrderTime(rs.getString(9));
                setUid(rs.getInt(10));
                setIsDrink(false);

                if (hasChange) {
                    getDetailChanges(this);
                }
            } else {
                throw new OrderDetailNotFoundException(String.format("%s id-тай захиалга олдсонгүй", getId()));
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (OrderDetailNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get Drink OrderDetail values
     */
    public void getDrinkDetails() {
        try {
            connection = (Connection) DBConnector.getConnection();
            pStat = (PreparedStatement) connection.prepareStatement(getDrinkOrderDetail);

            logger.info(String.format("Prepared Statement created for query: %s", pStat.getPreparedSql()));

            pStat.setLong(1, getId());
            logger.info(String.format("Prepared Statement values set: %s", pStat.toString()));
            logger.info("Executing statement.");

            if (pStat.execute()) {
                ResultSet rs = pStat.getResultSet();
                //Should return only one row
                rs.next();
                setOrderID(rs.getLong(1));
                setTableid(rs.getInt(2));
                setFoodID(rs.getInt(3));
                setFoodName(rs.getString(4));
                setHasChange(false);
                setDelivered(rs.getBoolean(5));
                setWaiterCancelled(rs.getBoolean(6));
                setChefCancelled(rs.getBoolean(7));
                setOrderTime(rs.getString(8));
                setUid(rs.getInt(9));
                setIsDrink(true);

            } else {
                throw new OrderDetailNotFoundException(String.format("%s id-тай захиалга олдсонгүй", getId()));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OrderDetailNotFoundException ex) {
            Logger.getLogger(OrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getTableid() {
        return tableid;
    }

    public void setTableid(Integer tableid) {
        this.tableid = tableid;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String FoodName) {
        this.foodName = FoodName;
    }

    public boolean hasChange() {
        return hasChange;
    }

    public void setHasChange(boolean hasChange) {
        this.hasChange = hasChange;
    }

    public List<String> getChanges() {
        return changes;
    }

    public void setChanges(List<String> changes) {
        this.changes = changes;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isWaiterCancelled() {
        return waiterCancelled;
    }

    public void setWaiterCancelled(boolean waiterCancelled) {
        this.waiterCancelled = waiterCancelled;
    }

    public boolean isChefCancelled() {
        return chefCancelled;
    }

    public void setChefCancelled(boolean chefCancelled) {
        this.chefCancelled = chefCancelled;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public boolean isDrink() {
        return isDrink;
    }

    public void setIsDrink(boolean isDrink) {
        this.isDrink = isDrink;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("[OrderID: ").append(getOrderID()).append("] ");
        sb.append("[DetailID: ").append(getId()).append("] ");
        sb.append("[TableID: ").append(getTableid()).append("] ");
        sb.append("[FoodID: ").append(getFoodID()).append("] ");
        sb.append("[FoodName: ").append(getFoodName()).append("] ");
        sb.append("[HasChange: ").append(hasChange()).append("] ");
        if (changes != null) {
            sb.append("{Changes : ");
            for (String change : changes) {
                sb.append(String.format("[%s]", change));
            }
            sb.append("}");
        }
        sb.append("[IsDelivered: ").append(isDelivered()).append("] ");
        sb.append("[WaiterCancelled: ").append(isWaiterCancelled()).append("]");
        sb.append("[ChefCancelled: ").append(isChefCancelled()).append("] ");
        sb.append("[OrderTime: ").append(getOrderTime()).append("] ");
        sb.append("[WaitMinutes: ").append(getWaitTime()).append("] ");
        sb.append("[isDrink: ").append(isDrink()).append("] ");
        sb.append("[UserID: ").append(getUid()).append("] }");

        return sb.toString();
    }

}
