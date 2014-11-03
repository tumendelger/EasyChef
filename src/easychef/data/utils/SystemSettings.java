/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data.utils;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import easychef.data.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Applicationii system settings
 *
 * @author tumee
 */
public class SystemSettings {

    private final static Logger logger = Logger.getLogger(SystemSettings.class.getName());

    //Color default values
    private String LWC = "#35AA74";
    private String MWC = "#FFB848";
    private String HWC = "#FF3F3F";
    private String CH = "#4D90FE";

    //System date    
    private StringProperty systemDate = new SimpleStringProperty("N/A");

    //Network status
    private StringProperty serverAddress = new SimpleStringProperty("");
    private BooleanProperty networkStatus;
    private int serverPort = 7800;

    //Printer name
    private StringProperty printerName = new SimpleStringProperty("N/A");
    private IntegerProperty printerWidth = new SimpleIntegerProperty();
    private BooleanProperty printerSet;

    //SQL Queries
    private final String getValue = "SELECT parameter FROM system_settings "
            + "WHERE name=?";
    private final String updateValue = "UPDATE system_settings SET "
            + "parameter=? "
            + "WHERE name=?";

    private ResultSet rs;
    private PreparedStatement pStat;

    public SystemSettings() {

    }

    public void loadSettings() throws SQLException {
        logger.info("Reading values from DB.");

        logger.info("Getting SYSTEM DATE.");
        String value = getValue("SYSTEMDATE");
        if (value != null) {
            setSystemDate(value);
        }

        logger.info("Getting Low Wait Color value.");
        value = getValue("Color_LW");
        if (value != null) {
            setLWC(value);
        }

        logger.info("Getting Medium Wait Color value.");
        value = getValue("Color_MW");
        if (value != null) {
            setMWC(value);
        }

        logger.info("Getting High Wait Color value.");
        value = getValue("Color_HW");
        if (value != null) {
            setHWC(value);
        }

        logger.info("Getting Food Change color value.");
        value = getValue("Color_CH");
        if (value != null) {
            setCH(value);
        }

        logger.info("Getting Printer name.");
        value = getValue("PRINTER");
        if (value != null) {
            setPrinterName(value);
        }

        logger.info("Getting Printer name.");
        value = getValue("PRINTER_WIDTH");
        if (value != null) {
            setPrinterWidth(Integer.parseInt(value));
        }

        logger.info("Getting Server Address.");
        value = getValue("KITCHEN_IP");
        if (value != null) {
            setServerAddress(value);
        }

        logger.info("Getting Server Port.");
        value = getValue("PORT");
        if (value != null) {
            setServerPort(Integer.parseInt(value));
        }

    }

    public String getValue(String name) throws SQLException {
        //Load value from database
        pStat = (PreparedStatement) DBConnector.getConnection().prepareStatement(getValue);
        pStat.setString(1, name);

        //Execute statement        
        logger.log(Level.INFO, "Getting value for {0} from DB.", name);
        rs = pStat.executeQuery();
        logger.log(Level.INFO, "Executing : {0}", pStat.toString());

        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }
    }

    public String getLWC() {
        return LWC;
    }

    public void setLWC(String LWC) {
        this.LWC = LWC;
        logger.info(String.format("Low wait color set to: %s", LWC));
    }

    public String getMWC() {
        return MWC;
    }

    public void setMWC(String MWC) {
        this.MWC = MWC;
        logger.info(String.format("Medium wait color set to: %s", MWC));
    }

    public String getHWC() {
        return HWC;
    }

    public void setHWC(String HWC) {
        this.HWC = HWC;
        logger.info(String.format("Medium wait color set to: %s", HWC));
    }

    public String getCH() {
        return CH;
    }

    public void setCH(String CH) {
        this.CH = CH;
        logger.info(String.format("Food changes color set to: %s", CH));
    }

    public StringProperty getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(String systemDate) {
        this.systemDate.set(systemDate);
        logger.log(Level.INFO, "System date set to: {0}", systemDate);
    }

    public StringProperty getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName.set(printerName);
        logger.log(Level.INFO, "Printer name set to: {0}", printerName);
    }

    public void setPrinterWidth(int width) {
        this.printerWidth.set(width);
        logger.log(Level.INFO, "Printer width set to: {0}", printerWidth);
    }

    public IntegerProperty getPrinterWidth() {
        return this.printerWidth;
    }

    public StringProperty getServerAddress() {
        return this.serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress.set(serverAddress);
        logger.log(Level.INFO, "Server IP set to: {0}", serverAddress);
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void setServerPort(int port) {
        this.serverPort = port;
        logger.log(Level.INFO, "Server port set to: {0}", port);
    }

    public int savePrinter() {
        int res = 0;
        try {
            pStat = (PreparedStatement) DBConnector.getConnection().prepareStatement(updateValue);
            pStat.setString(1, this.printerName.get());
            pStat.setString(2, "PRINTER");
            res = pStat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SystemSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public boolean isPrinterSet() {
        return printerSet.get();
    }

    public void setPrinterSet(boolean printerSet) {
        this.printerSet.set(printerSet);
    }

    public int setKitchenIpPort(String IP, int port) {
        int affected = 0;
        String query;
        try {
            Statement Stat = (Statement) DBConnector.getConnection().createStatement();
            query = String.format("UPDATE system_settings SET parameter='%s' WHERE name='KITCHEN_IP'", IP);
            logger.info(String.format("Executing : %s", query));
            affected += Stat.executeUpdate(query);
            query = String.format("UPDATE system_settings SET parameter='%s' WHERE name='PORT'", String.valueOf(port));
            logger.info(String.format("Executing : %s", query));
            affected += Stat.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(SystemSettings.class.getName()).log(Level.SEVERE, null, ex);
        }

        return affected;
    }

    public int updatePrinterWidth() {
        int res = 0;
        try {
            pStat = (PreparedStatement) DBConnector.getConnection().prepareStatement(updateValue);
            pStat.setString(1, String.valueOf(this.printerWidth.get()));
            pStat.setString(2, "PRINTER_WIDTH");
            res = pStat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SystemSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

}
