/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tumee
 */
public class PrintOrder {

    private static final Logger logger = Logger.getLogger(PrintOrder.class.getName());
    private final long id;
    //In the Lists below String[] must have length of 3 Strings
    //1st Name, 2nd Qty, 3rd Total Price for foods
    private List<String[]> fodetails, dodetails;
    private int status;

    //Order details to be printed on the bill
    private String orderTime;
    private double totalPrice;
    private double vat;
    private double discount;
    private double cash;
    private double card;
    private double totalAmount;
    private int tid;
    private int uid;
    private String uname;
    private String pcode;
    private String systemDate;
    private String billDate;

    //SQL Queries
    private final String getOrderInfo = "SELECT ordertime, totalprice, vat, discount, cash, card, totalAmount, tid, e.firstname, uid, promocode, systemdate "
            + "FROM orders as o, employee as e "
            + "WHERE e.id=o.uid "
            + "AND o.id=?";

    private final String getFODetails = "SELECT DISTINCT fm.name, count(*) AS qty, count(*)*fod.price AS total "
            + "FROM foodmenu fm JOIN forderdetails fod WHERE "
            + " fm.id=fod.foodid AND fod.orderid=? "
            + "GROUP BY fm.name";

    private final String getDODetails = "SELECT DISTINCT dm.name, count(*) AS qty, count(*)*dod.price AS total "
            + "FROM drinkmenu dm JOIN dorderdetails dod WHERE "
            + " dm.id=dod.drinkid AND dod.orderid=? "
            + "GROUP BY dm.name";

    public PrintOrder(long id) {
        this.id = id;
    }

    public void getBillDetails() {
        PreparedStatement pStat;
        ResultSet rs;

        try {
            //Start getting Bill Details for Order
            logger.info("Start getBillDetails()");

            pStat = DBConnector.getConnection().prepareStatement(getOrderInfo);
            pStat.setLong(1, id);

            rs = pStat.executeQuery();
            logger.info(String.format("Successfully executed | %s", pStat.toString()));

            if (rs.next()) {
                //Should return only one row
                setOrderTime(rs.getString(1));
                setTotalPrice(rs.getDouble(2));
                setVat(rs.getDouble(3));
                setDiscount(rs.getDouble(4));
                setCash(rs.getDouble(5));
                setCard(rs.getDouble(6));
                setTotalAmount(rs.getDouble(7));
                setTid(rs.getInt(8));
                setUname(rs.getString(9));
                setUid(rs.getInt(10));
                setPcode(rs.getString(11));
                setSystemDate(rs.getString(12));

                getFODetails();
                getDODetails();

            } else {
                logger.warning(String.format("No detail found for Order[%s]", id));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }

    private void getFODetails() {
        PreparedStatement pStat;
        ResultSet rs;

        try {
            logger.info("Start of getFODetails()");
            pStat = DBConnector.getConnection().prepareStatement(getFODetails);
            pStat.setLong(1, id);
            rs = pStat.executeQuery();

            logger.info(String.format("Successfully executed | %s", pStat.toString()));
            while (rs.next()) {
                if (fodetails == null) {
                    fodetails = new LinkedList<>();
                }

                fodetails.add(new String[]{rs.getString("name"), rs.getString("qty"), rs.getString("total")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrintOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getDODetails() {
        PreparedStatement pStat;
        ResultSet rs;

        try {
            logger.info("Start of getFODetails()");
            pStat = DBConnector.getConnection().prepareStatement(getDODetails);
            pStat.setLong(1, id);
            rs = pStat.executeQuery();

            logger.info(String.format("Successfully executed | %s", pStat.toString()));
            while (rs.next()) {
                if (dodetails == null) {
                    dodetails = new LinkedList<>();
                }

                dodetails.add(new String[]{rs.getString("name"), rs.getString("qty"), rs.getString("total")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrintOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setCard(double card) {
        this.card = card;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public void setSystemDate(String systemDate) {
        this.systemDate = systemDate;
    }

    /**
     * Sets bill date as of NOW
     */
    public void setBillDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date(System.currentTimeMillis());
        this.billDate = sdf.format(now);
    }

    public long getId() {
        return id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getVat() {
        return vat;
    }

    public double getDiscount() {
        return discount;
    }

    public double getCash() {
        return cash;
    }

    public double getCard() {
        return card;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getTid() {
        return tid;
    }

    public int getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getPcode() {
        return pcode;
    }

    public String getSystemDate() {
        return systemDate;
    }

    public String getBillDate() {
        return billDate;
    }

    public int getStatus() {
        return status;
    }

    /**
     * Sets order printing status
     *
     * @param status (0) - not printed (1) - print completed
     */
    public void setStatus(int status) {
        this.status = status;
        if (status == 1) {
            logger.info(String.format("Successfully executed printOrder() for Order[%s]", id));
        } else {
            logger.info(String.format("Could NOT execute printOrder() for Order[%s]", id));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PrintOrder:[id:").append(getId()).append("]\n");
        sb.append("{[Table:").append(getTid()).append("]");
        sb.append("[Order time:").append(getOrderTime()).append("]");
        sb.append("[Total price:").append(getTotalPrice()).append("]");
        sb.append("[VAT:").append(getVat()).append("]");
        sb.append("[Discount:").append(getDiscount()).append("]");
        sb.append("[Total Amount:").append(getTotalAmount()).append("]");
        sb.append("[Cash:").append(getCash()).append("]");
        sb.append("[Card:").append(getCard()).append("]");
        sb.append("[Promo Code:").append(getPcode()).append("]");
        sb.append("[Employee:").append(getUname()).append("|ID:").append(getUid()).append("]");

        if (fodetails.size() > 0) {
            sb.append("[FODetails:{");
            for (String[] detail : fodetails) {
                sb.append("[").append(detail[0]).append(",").append(detail[1]).append(",").append(detail[2]).append("]");
            }
            sb.append("}]");
        }

        if (dodetails.size() > 0) {
            sb.append("[DODetails:{");
            for (String[] detail : dodetails) {
                sb.append("[").append(detail[0]).append(",").append(detail[1]).append(",").append(detail[2]).append("]");
            }
            sb.append("}]");
        }

        sb.append("}");
        return sb.toString();
    }

}
