/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import java.awt.Font;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
    private String memberid;
    private int uid;
    private String uname;
    private String pcode;
    private String systemDate;
    private String billDate;
    private int width;
    private char[] name;
    private char[] quantity;
    private char[] total;

    //SQL Queries
    private PreparedStatement pStmt;
    private ResultSet resultSet;
    private final String getOrderInfo = "SELECT ordertime, totalprice, vat, discount, cash, card, totalAmount, tid, "
            + "e.firstname, uid, promocode, systemdate, mid, m.bonus, m.totalspent "
            + "FROM orders as o, employee as e, members as m "
            + "WHERE e.id=o.uid  "
            + "AND o.mid=m.id "
            + "AND o.id=?";

    private final String getFODetails = "SELECT DISTINCT fm.name, count(*) AS qty, fod.price, count(*)*fod.price AS total "
            + "FROM foodmenu fm JOIN forderdetails fod WHERE "
            + " fm.id=fod.foodid AND fod.orderid=? "
            + "GROUP BY fm.name";

    private final String getDODetails = "SELECT DISTINCT dm.name, count(*) AS qty, dod.price, count(*)*dod.price AS total "
            + "FROM drinkmenu dm JOIN dorderdetails dod WHERE "
            + " dm.id=dod.drinkid AND dod.orderid=? "
            + "GROUP BY dm.name";

    private final String getHeaders = "SELECT name, parameter FROM system_settings "
            + "WHERE name LIKE 'HEADER%' ORDER BY name ASC";

    private final String getFooters = "SELECT name, parameter FROM system_settings "
            + "WHERE name LIKE 'FOOTER%' ORDER BY name ASC";

    //For printing to paper
    private JTextPane textToPrint;
    private StyledDocument doc;
    private SimpleAttributeSet right, left, center, justified;
    private PrintRequestAttributeSet attr_set;

    public PrintOrder(long id, int width) {
        this.id = id;
        this.width = width;
        textToPrint = new JTextPane();
        textToPrint.setSize((width == OrderBill.PaperWidth.EIGHTY_MM) ? OrderBill.PaperWidth.EIGHTY_PIXEL_TO_MM : OrderBill.PaperWidth.SIXTY_PIXEL_TO_MM, 400);
        textToPrint.setDoubleBuffered(true);
        textToPrint.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        doc = textToPrint.getStyledDocument();
        right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ParagraphConstants.ALIGN_LEFT);
        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ParagraphConstants.ALIGN_CENTER);
        justified = new SimpleAttributeSet();
        StyleConstants.setAlignment(justified, StyleConstants.ParagraphConstants.ALIGN_JUSTIFIED);
        attr_set = new HashPrintRequestAttributeSet();
        attr_set.add(new Copies(1));
        attr_set.add(new MediaPrintableArea(1, 1, this.width, OrderBill.PaperWidth.PAPER_LENGTH, Size2DSyntax.MM));
    }

    public void getBillDetails() {

        try {
            //Start getting Bill Details for Order
            logger.info("Start getBillDetails()");

            pStmt = DBConnector.getConnection().prepareStatement(getOrderInfo);
            pStmt.setLong(1, id);

            resultSet = pStmt.executeQuery();
            logger.info(String.format("Successfully executed | %s", pStmt.toString()));

            if (resultSet.next()) {
                //Should return only one row
                setOrderTime(resultSet.getString(1));
                setTotalPrice(resultSet.getDouble(2));
                setVat(resultSet.getDouble(3));
                setDiscount(resultSet.getDouble(4));
                setCash(resultSet.getDouble(5));
                setCard(resultSet.getDouble(6));
                setTotalAmount(resultSet.getDouble(7));
                setTid(resultSet.getInt(8));
                setUname(resultSet.getString(9));
                setUid(resultSet.getInt(10));
                setPcode(resultSet.getString(11));
                setSystemDate(resultSet.getString(12));
                setMemberid(resultSet.getInt("mid") == 1 ? " " : resultSet.getString("mid"));

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

                fodetails.add(new String[]{rs.getString("name"), rs.getString("qty"), rs.getString("price"), rs.getString("total")});
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

                dodetails.add(new String[]{rs.getString("name"), rs.getString("qty"), rs.getString("price"), rs.getString("total")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrintOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void prepareBill() throws SQLException {
        prepareHeaders();
        prepareBody();
        prepareFooters();
    }

    public void prepareHeaders() throws SQLException {
        try {
            pStmt = DBConnector.getConnection().prepareStatement(getHeaders);
            resultSet = pStmt.executeQuery();
            //First line bigger
            resultSet.next();
            StyleConstants.setFontSize(center, 25);
            StyleConstants.setBold(center, true);
            insertString(resultSet.getString("parameter") + "\n", center);
            //Second line small
            while (resultSet.next()) {
                StyleConstants.setFontSize(center, 12);
                StyleConstants.setBold(center, false);
                insertString(resultSet.getString("parameter") + "\n\n", center);
            }

            StyleConstants.setFontSize(left, 10);
            StyleConstants.setBold(left, false);
            insertString(String.format("Захиалгын дугаар : %s", id) + "\n", left);
            insertString(String.format("Огноо : %s - %s", new SimpleDateFormat("yyyy.MM.dd").format(System.currentTimeMillis()), new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis())) + "\n", left);
            insertString(String.format("Үйлчлэгч: %s \n", uname), left);
            insertString("===========================================" + "\n", left);

//            name = "Хоол".toCharArray();
            if (width == PaperWidth.EIGHTY_MM) {
                insertString("Х/Нэр\t\tШирхэг\t\tДүн\n", center);
            }

            if (width == PaperWidth.SIXTY_MM) {
                insertString("Х/Нэр \tШирхэг \tДүн\n", center);
            }
//            insertString("\n", right);
//            quantity = "Тоо ширхэг".toCharArray();
//            total = "Үнийн Дүн".toCharArray();
//
//            insertString(String.format("%s", String.valueOf(name)) + String.format(" \t\t\t%s  %s", String.valueOf(quantity), String.valueOf(total)) + "\n", justified);
        } catch (BadLocationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void prepareBody() {
        if (fodetails.size() > 0) {
            for (String[] detail : fodetails) {
//                sb.append("[").append(detail[0]).append(",").append(detail[1]).append(",").append(detail[2]).append(",").append(detail[3]).append("]");
                try {
                    String foodline = detail[0];
                    String priceLine = String.format("%s x %s = %s", detail[1],
                            detail[2], detail[3]);
                    insertString(foodline + "\n", left);
                    insertString(priceLine + "\n", right);
                } catch (BadLocationException ex) {
                    Logger.getLogger(OrderBill.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (dodetails.size() > 0) {

            for (String[] detail : dodetails) {
//                sb.append("[").append(detail[0]).append(",").append(detail[1]).append(",").append(detail[2]).append(",").append(detail[3]).append("]");
                try {
                    String foodline = detail[0];
                    String priceLine = String.format("%s x %s = %s", detail[1],
                            detail[2], detail[3]);
                    insertString(foodline + "\n", left);
                    insertString(priceLine + "\n", right);
                } catch (BadLocationException ex) {
                    Logger.getLogger(OrderBill.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void prepareFooters() throws SQLException {
        try {
            insertString("===========================================\n", right);
            insertString(String.format("Нийт дүн : %s", totalPrice) + "\n", right);
            insertString(String.format("НӨАТ : %s", vat) + "\n", right);
            insertString(String.format("Хямдрал : %s ", discount) + "\n", right);
            insertString(String.format("Гишүүн  : %s", memberid) + "\n", right);
            StyleConstants.setBold(right, true);
            insertString(String.format("Төлбөл зохих дүн : %s", totalAmount) + "\n", right);
            StyleConstants.setBold(right, false);
            insertString("===========================================\n", right);

            pStmt = DBConnector.getConnection().prepareStatement(getFooters);
            resultSet = pStmt.executeQuery();

            //Print footers
            //First line bold and big
            resultSet.next();
            StyleConstants.setFontSize(center, 20);
            StyleConstants.setBold(center, true);
            insertString(resultSet.getString("parameter") + "\n", center);

            //Second Line small
            while (resultSet.next()) {
                StyleConstants.setFontSize(center, 12);
                StyleConstants.setBold(center, false);
                insertString(resultSet.getString("parameter") + "\n", center);
            }

        } catch (BadLocationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void insertString(String line, SimpleAttributeSet align) throws BadLocationException {
        // insert lines with given alignment options
        int prevDocLen = doc.getLength();
        doc.insertString(doc.getLength() + 1, line, align);
        doc.setParagraphAttributes(prevDocLen + 1, doc.getLength(), align, false);
    }

    //Suppose printer is found already
    public void print(String printerName) throws PrinterException {
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, null);
        for (PrintService printer : printServices) {
            if (printer.getName().equalsIgnoreCase(printerName)) {
                FutureTask<Boolean> printTask = new FutureTask<>(() -> textToPrint.print(null, null, false, printer, attr_set, true));
                Executor executor = null;
                executor.execute(printTask);
                break;
            }
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

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getStatus() {
        return status;
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
                sb.append("[").append(detail[0]).append(",").append(detail[1]).append(",").append(detail[2]).append(",").append(detail[3]).append("]");
            }
            sb.append("}]");
        }

        if (dodetails.size() > 0) {
            sb.append("[DODetails:{");
            for (String[] detail : dodetails) {
                sb.append("[").append(detail[0]).append(",").append(detail[1]).append(",").append(detail[2]).append(",").append(detail[3]).append("]");
            }
            sb.append("}]");
        }

        sb.append("}");
        return sb.toString();
    }

    public class OrderNotFoundException extends Exception {

        public OrderNotFoundException(String msg) {
            super(msg);
        }
    }
    
    
    public class PaperWidth {

        public static final int EIGHTY_MM = 80;
        public static final int EIGHTY_PIXEL_TO_MM = 302;
        public static final int SIXTY_MM = 60;
        public static final int SIXTY_PIXEL_TO_MM = 227;
        public static final int PAPER_LENGTH = 400;
    }
}
