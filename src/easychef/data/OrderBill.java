/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Tumendelger
 */
public class OrderBill {

    private MessageFormat Header, Footer;
    private String[] header;
    private String[] footer;
    private long orderID;
    private int tableid;
    private double totalAmount;
    private double VAT;
    private double discount;
    private String memberid;
    private String[] order_details;
    private char[] name;
    private char[] quantity;
    private char[] total;
    private ResultSet resultSet;
    private final String getDetailString = "SELECT o.id, tid, uid, mid, totalprice, totalAmount, vat, discount, m.bonus, m.totalspent "
            + "FROM orders AS o, members AS m WHERE o.id=? and o.mid=m.id";

    private final String getFoodDetailsString = "SELECT DISTINCT f.name, f.price, count(*) AS qty, count(*)*f.price AS total FROM"
            + " foodmenu AS f JOIN forderdetails AS fod "
            + " WHERE fod.orderid=? AND fod.cancelbyw=0 "
            + " AND fod.cancelbych=0 "
            + " AND f.id=fod.foodid "
            + " GROUP BY f.name";

    private final String getDrinkDetailsString = "SELECT DISTINCT d.name, d.price, count(*) AS qty, count(*)*d.price AS total FROM"
            + " drinkmenu AS d JOIN dorderdetails AS dod "
            + " WHERE dod.orderid=? AND dod.cancelbyw=0 "
            + " AND dod.cancelbych=0 "
            + " AND d.id=dod.drinkid "
            + " GROUP BY d.name";

    private final String getHeaders = "SELECT name, parameter FROM system_settings "
            + "WHERE name LIKE 'HEADER%' ORDER BY name ASC";

    private final String getFooters = "SELECT name, parameter FROM system_settings "
            + "WHERE name LIKE 'FOOTER%' ORDER BY name ASC";

    private PreparedStatement pStmt;
    private JTextPane textToPrint;
    private StyledDocument doc;
    private SimpleAttributeSet right, left, center, justified;
    private PrintRequestAttributeSet attr_set;

    private static final Logger logger = Logger.getLogger(OrderBill.class.getName());

    public OrderBill() {
    }

    /**
     *
     * @param orderID ID of the order being printed
     * @param width Printer supported paper width
     */
    public OrderBill(long orderID, int width) {
        this.orderID = orderID;
        name = new char[24];
        quantity = new char[8];
        total = new char[5];
        Header = new MessageFormat("{0}");
        Footer = new MessageFormat("{0}");
        header = new String[7];
        footer = new String[10];
        textToPrint = new JTextPane();
        textToPrint.setSize((width == PaperWidth.EIGHTY_MM) ? PaperWidth.EIGHTY_PIXEL_TO_MM : PaperWidth.SIXTY_PIXEL_TO_MM, 400);
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
        attr_set.add(new MediaPrintableArea(1, 1, width, PaperWidth.PAPER_LENGTH, Size2DSyntax.MM));
    }

    public void getOrderDetails() throws SQLException, OrderNotFoundException {
        pStmt = DBConnector.getConnection().prepareStatement(getDetailString);
        pStmt.setLong(1, orderID);
        resultSet = pStmt.executeQuery();
        if (resultSet.next()) {
            setTableid(resultSet.getInt("tid"));
            setTotalAmount(resultSet.getDouble("totalprice"));
            setVAT(resultSet.getDouble("vat"));
            setDiscount(resultSet.getDouble("discount"));
            setMemberid(resultSet.getInt("mid") == 1 ? " " : resultSet.getString("mid"));
        } else {
            throw new OrderNotFoundException(String.format("Системд %s ID-тай захиалга байхгүй байна", orderID));
        }
    }

    public void getFoodOrders() throws SQLException {
        pStmt = DBConnector.getConnection().prepareStatement(getFoodDetailsString);
        pStmt.setLong(1, orderID);
        resultSet = pStmt.executeQuery();

        //getting last of the result set to find how many rows fetched
        resultSet.last();
        int length = resultSet.getRow();
        order_details = new String[length];

        //setting result set back to beginning
        resultSet.beforeFirst();

        //start reading fetched datas
        while (resultSet.next()) {
            try {
                String foodline = resultSet.getString("name").trim();
                String priceLine = String.format("%s x %s = %s", resultSet.getString("qty").trim(),
                        resultSet.getString("price").trim(), resultSet.getString("total").trim());
                insertString(foodline + "\n", left);
                insertString(priceLine + "\n", right);
            } catch (BadLocationException ex) {
                Logger.getLogger(OrderBill.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getDrinkOrders() throws SQLException {
        pStmt = DBConnector.getConnection().prepareStatement(getDrinkDetailsString);
        pStmt.setLong(1, orderID);
        resultSet = pStmt.executeQuery();

        //getting last of the result set to find how many rows fetched
        resultSet.last();
        int length = resultSet.getRow();
        order_details = new String[length];

        //setting result set back to beginning
        resultSet.beforeFirst();

        //start reading fetched datas
        while (resultSet.next()) {
            try {
                String foodline = resultSet.getString("name").trim();
                String priceLine = String.format("%s x %s = %s", resultSet.getString("qty").trim(),
                        resultSet.getString("price").trim(), resultSet.getString("total").trim());
                insertString(foodline + "\n", left);
                insertString(priceLine + "\n", right);
            } catch (BadLocationException ex) {
                Logger.getLogger(OrderBill.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getHeaders() throws SQLException {
        try {
            pStmt = DBConnector.getConnection().prepareStatement(getHeaders);
            resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                StyleConstants.setFontSize(center, 25);
                StyleConstants.setBold(center, true);
                insertString(resultSet.getString("parameter") + "\n", center);
            }

            StyleConstants.setFontSize(left, 10);
            StyleConstants.setBold(left, false);
            insertString(String.format("Захиалгын дугаар : %s", orderID) + "\n", left);
            insertString(String.format("Огноо : %s\tЦаг : %s", new SimpleDateFormat("yyyy.MM.dd").format(System.currentTimeMillis()), new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis())) + "\n", left);
            insertString("===========================================" + "\n", left);

            name = "Хоол                ".toCharArray();
            quantity = "Тоо ширхэг".toCharArray();
            total = "Үнийн Дүн".toCharArray();

            insertString(String.format("%s", String.valueOf(name)) + String.format(" \t\t\t%s  %s", String.valueOf(quantity), String.valueOf(total)) + "\n", justified);
        } catch (BadLocationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void getFooters() throws SQLException {
        try {
            insertString("===========================================\n", right);
            insertString(String.format("Нийт дүн : %s", totalAmount) + "\n", right);
            insertString(String.format("НӨАТ : %s", VAT) + "\n", right);
            insertString(String.format("Хямдрал : %s ", discount) + "\n", right);
            insertString(String.format("Гишүүн  : %s", memberid) + "\n", right);
            StyleConstants.setBold(right, true);
            insertString(String.format("Төлбөл зохих дүн : %s", (totalAmount + VAT - discount)) + "\n", right);
            StyleConstants.setBold(right, false);
            insertString("===========================================\n", right);

            pStmt = DBConnector.getConnection().prepareStatement(getFooters);
            resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
                insertString(resultSet.getString("parameter").trim(), center);
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

    public void print(String[] list) {
        for (String item : list) {
            textToPrint.setText(textToPrint.getText() + System.lineSeparator() + item);
        }
    }

    //Getters and Setters
    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String[] getFooter() {
        return footer;
    }

    public void setFooter(String[] footer) {
        this.footer = footer;
    }

    public int getTableid() {
        return tableid;
    }

    public void setTableid(int tableid) {
        this.tableid = tableid;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getDiscount() {
        return discount;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public class PaperWidth {

        public static final int EIGHTY_MM = 80;
        public static final int EIGHTY_PIXEL_TO_MM = 302;
        public static final int SIXTY_MM = 60;
        public static final int SIXTY_PIXEL_TO_MM = 227;
        public static final int PAPER_LENGTH = 230;
    }

    public class OrderNotFoundException extends Exception {

        public OrderNotFoundException(String msg) {
            super(msg);
        }
    }

}
