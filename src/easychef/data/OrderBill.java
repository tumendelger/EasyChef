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
    private String getDetailString = "SELECT o.id, tid, uid, mid, totalprice, totalAmount, vat, discount, m.bonus, m.totalspent "
            + "FROM orders AS o, members AS m WHERE o.id=? and o.mid=m.id";

    private String getFoodDetailsString = "SELECT DISTINCT f.name, f.price, count(*) AS qty, count(*)*f.price AS total FROM"
            + " foodmenu AS f JOIN forderdetails AS fod "
            + " WHERE fod.orderid=? AND fod.cancelbyw=0 "
            + " AND fod.cancelbych=0 "
            + " AND f.id=fod.foodid "
            + " GROUP BY f.name";

    private String getDrinkDetailsString = "SELECT DISTINCT d.name, d.price, count(*) AS qty, count(*)*d.price AS total FROM"
            + " drinkmenu AS d JOIN dorderdetails AS dod "
            + " WHERE dod.orderid=? AND dod.cancelbyw=0 "
            + " AND dod.cancelbych=0 "
            + " AND d.id=dod.drinkid "
            + " GROUP BY d.name";

    private String getHeaders = "SELECT name, parameter FROM system_settings "
            + "WHERE name LIKE 'HEADER%' ORDER BY name ASC";

    private String getFooters = "SELECT name, parameter FROM system_settings "
            + "WHERE name LIKE 'FOOTER%' ORDER BY name ASC";

    private PreparedStatement pStmt;
    private JTextPane textToPrint;
    private StyledDocument doc;
    private SimpleAttributeSet right, left, center, justified;
    private PrintRequestAttributeSet attr_set;

    private final Logger logger = Logger.getLogger(OrderBill.class.getName());

    public OrderBill() {
    }

    /**
     *
     * @param orderID ID of the order being printed
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

    public void getOrderDetails() {

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

    public void insertString(String line, SimpleAttributeSet align) throws BadLocationException {
        // insert lines with given alignment options
        int prevDocLen = doc.getLength();
        doc.insertString(doc.getLength() + 1, line, align);
        doc.setParagraphAttributes(prevDocLen + 1, doc.getLength(), align, false);
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

}
