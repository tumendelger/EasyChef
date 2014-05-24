/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data.utils;

import com.mysql.jdbc.PreparedStatement;
import easychef.data.DBConnector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Applicationii system settings
 *
 * @author tumee
 */
public class SystemSettings {

    private final static Logger logger = Logger.getLogger(SystemSettings.class.getName());
    private final String propFileName = "configs.properties";
    private File propFile;
    private Properties props;

    //Color default values
    private String LWC = "#35AA74";
    private String MWC = "#FFB848";
    private String HWC = "#FF3F3F";
    private String CH = "#4D90FE";

    //System date    
    private StringProperty systemDate = new SimpleStringProperty("N/A");

    //Network status
    private StringProperty networkString;
    private BooleanProperty networkStatus;

    //Printer name
    private StringProperty printerName = new SimpleStringProperty("N/A");
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

    }

    public String getValue(String name) throws SQLException {
        //Load value from database
        pStat = (PreparedStatement) DBConnector.getConnection().prepareStatement(getValue);
        pStat.setString(1, name);

        //Execute statement        
        logger.log(Level.INFO, "Getting value for {0} from DB.", name);
        rs = pStat.executeQuery();

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

    private void saveProps(FileOutputStream fos) throws IOException {
        props = new Properties();
        props.setProperty("LWC", LWC);
        props.setProperty("MWC", MWC);
        props.setProperty("HWC", HWC);
        props.setProperty("CH", CH);

        props.store(fos, null);
        logger.info("Properties saved successfully.");

    }

    public StringProperty getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName.set(printerName);
        logger.log(Level.INFO, "Printer name set to: {0}", printerName);
    }

    public boolean isPrinterSet() {
        return printerSet.get();
    }

    public void setPrinterSet(boolean printerSet) {
        this.printerSet.set(printerSet);
    }

}
