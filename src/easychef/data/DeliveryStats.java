/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import com.mysql.jdbc.PreparedStatement;
import static easychef.data.DeliveryStats.STATISTIC_TYPE.CANCEL_N_C;
import static easychef.data.DeliveryStats.STATISTIC_TYPE.CANCEL_W_C;
import static easychef.data.DeliveryStats.STATISTIC_TYPE.HIGH_WAIT;
import static easychef.data.DeliveryStats.STATISTIC_TYPE.LOW_WAIT;
import static easychef.data.DeliveryStats.STATISTIC_TYPE.MED_WAIT;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author tumee
 */
public class DeliveryStats {

    private static final Logger logger = Logger.getLogger(DeliveryStats.class.getName());

    //properties to show statistics    
    private IntegerProperty lowWaitOrders = new SimpleIntegerProperty(0);
    private IntegerProperty medWaitOrders = new SimpleIntegerProperty(0);
    private IntegerProperty highWaitOrders = new SimpleIntegerProperty(0);

    private IntegerProperty cOrdersWithCost = new SimpleIntegerProperty(0);
    private IntegerProperty cOrdersNoCost = new SimpleIntegerProperty(0);

    private final String system_date;

    //Init stats for a system date
    private final String initStats = "INSERT INTO dailystatistics(stat_type, noOfOrders, effectivedate) VALUES (?,0,?)";

    private final String getStatistic = "SELECT stat_type, noOfOrders FROM dailystatistics "
            + "WHERE stat_type=? " //Statistics type i.e: low wait or med wait etc.,
            + "AND effectivedate=?";    //Effective date for this statistics, should be systemdate

    private final String updateStatistics = "UPDATE dailystatistics SET "
            + "noOfOrders=? " //Value to be updated
            + "WHERE stat_type=? " //what kind of stat is this. Low Wait(1), Med Wait(2), High Wait(3), Cancel with cost(4) or Cancel without cost(5)
            + "AND effectivedate=? ";   //System effective date

    /**
     * Default empty constructor initializes values Check database for current
     * values for a effective date If not exists initialize for the day
     *
     * @param date Effective system date format should be 'YYYY-MM-DD'
     */
    public DeliveryStats(String date) {

        this.system_date = date;
    }

    /**
     * Initializes daily statistics for a effective system date
     *
     */
    public void initDailyStatistics() {
        //TO DO
        //Check if values for current effective date is available in the system
        //If exists get values
        //If NOT initialize
        checkStat(LOW_WAIT);
        checkStat(MED_WAIT);
        checkStat(HIGH_WAIT);
        checkStat(CANCEL_N_C);
        checkStat(CANCEL_W_C);

    }

    /**
     * Fetch database values for given statistics type i.e:low_wait orders,
     * high_wait_orders, cancelled order with or without costs etc.,
     *
     * @param STAT_TYPE
     */
    private void checkStat(STATISTIC_TYPE STAT_TYPE) {
        try {
            logger.info(String.format("Checking %s statistics values.", STAT_TYPE.getName()));
            PreparedStatement pStat = (PreparedStatement) DBConnector.getConnection().prepareStatement(getStatistic);
            pStat.setInt(1, STAT_TYPE.value);
            pStat.setString(2, system_date);

            ResultSet rs = pStat.executeQuery();

            if (rs.next()) {
                logger.info(String.format("Values found for statistics for type: %s. Getting result set for", STAT_TYPE.getName()));

                //Should contain 1 row only
                logger.info(String.format("Reading rows for statistic: %s", STAT_TYPE.getName()));
                switch (STAT_TYPE) {
                    case LOW_WAIT:
                        setLowWaitOrders(rs.getInt("noOfOrders"));
                        break;
                    case MED_WAIT:
                        setMedWaitOrders(rs.getInt("noOfOrders"));
                        break;
                    case HIGH_WAIT:
                        setHighWaitOrders(rs.getInt("noOfOrders"));
                        break;
                    case CANCEL_W_C:
                        setcOrdersWithCost(rs.getInt("noOfOrders"));
                        break;
                    case CANCEL_N_C:
                        setcOrdersNoCost(rs.getInt("noOfOrders"));
                        break;
                }

                //Close pStat
                pStat.close();
            } else {//No Stat found init stat
                logger.info(String.format("No stat values found for type: %s", STAT_TYPE.getName()));
                pStat = (PreparedStatement) DBConnector.getConnection().prepareStatement(initStats);
                pStat.setInt(1, STAT_TYPE.getValue());
                pStat.setString(2, system_date);
                pStat.execute();
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Increases number of LowWait orders by 1
     *
     */
    public void increaseLowWait() {
        Platform.runLater(() -> {
            lowWaitOrders.set(lowWaitOrders.get() + 1);
            update(LOW_WAIT);
        });

    }

    /**
     * Increases number of MediumWait orders by 1
     *
     */
    public void increaseMedWait() {
        Platform.runLater(() -> {
            medWaitOrders.set(medWaitOrders.get() + 1);
            update(MED_WAIT);
        });
    }

    /**
     * Increases number of HighWait orders by 1
     *
     */
    public void increaseHighWait() {
        Platform.runLater(() -> {
            highWaitOrders.set(highWaitOrders.get() + 1);
            update(HIGH_WAIT);
        });
    }

    /**
     * Increases number of cancelOrdersWithCost by 1
     *
     */
    public void increaseOrdersWithCost() {
        Platform.runLater(() -> {
            cOrdersWithCost.set(cOrdersWithCost.get() + 1);
            update(CANCEL_W_C);
        });
    }

    /**
     * Increases number of cancelOrdersWithoutCost by 1
     *
     */
    public void increaseOrdersWithNoCost() {
        Platform.runLater(() -> {
            cOrdersNoCost.set(cOrdersNoCost.get() + 1);
            update(CANCEL_N_C);
        });
    }

    /**
     * Updates statistics in the DB for given type with current value
     *
     * @param type
     */
    private void update(STATISTIC_TYPE type) {
        try {
            PreparedStatement pStat = (PreparedStatement) DBConnector.getConnection().prepareStatement(updateStatistics);
            switch (type) {
                case LOW_WAIT:
                    pStat.setInt(1, getLowWaitOrders().get());
                    break;
                case MED_WAIT:
                    pStat.setInt(1, getMedWaitOrders().get());
                    break;
                case HIGH_WAIT:
                    pStat.setInt(1, getHighWaitOrders().get());
                    break;
                case CANCEL_W_C:
                    pStat.setInt(1, getcOrdersWithCost().get());
                    break;
                case CANCEL_N_C:
                    pStat.setInt(1, getcOrdersNoCost().get());
                    break;
            }
            pStat.setInt(2, type.value);
            pStat.setString(3, system_date);
            pStat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryStats.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return lowWaitOrders
     */
    public IntegerProperty getLowWaitOrders() {
        return lowWaitOrders;
    }

    /**
     *
     * @param lowWaitOrders sets lowWaitOrders
     */
    public void setLowWaitOrders(int lowWaitOrders) {
        this.lowWaitOrders.set(lowWaitOrders);
    }

    /**
     *
     * @return mediumWaitOrders
     */
    public IntegerProperty getMedWaitOrders() {
        return medWaitOrders;
    }

    /**
     *
     * @param medWaitOrders sets mediumWaitOrders
     */
    public void setMedWaitOrders(int medWaitOrders) {
        this.medWaitOrders.set(medWaitOrders);
    }

    /**
     *
     * @return highWaitOrders
     */
    public IntegerProperty getHighWaitOrders() {
        return highWaitOrders;
    }

    /**
     *
     * @param highWaitOrders highWaitOrders
     */
    public void setHighWaitOrders(int highWaitOrders) {
        this.highWaitOrders.set(highWaitOrders);
    }

    /**
     *
     * @return canceled ordersWithCost
     */
    public IntegerProperty getcOrdersWithCost() {
        return cOrdersWithCost;
    }

    /**
     *
     * @param cOrdersWithCost set canceled ordersWithCost
     */
    public void setcOrdersWithCost(int cOrdersWithCost) {
        this.cOrdersWithCost.set(cOrdersWithCost);
    }

    /**
     *
     * @return canceled ordersWithoutCost
     */
    public IntegerProperty getcOrdersNoCost() {
        return cOrdersNoCost;
    }

    /**
     *
     * @param cOrdersNoCost set canceled ordersWithoutCost
     */
    public void setcOrdersNoCost(int cOrdersNoCost) {
        this.cOrdersNoCost.set(cOrdersNoCost);
    }

    /**
     * Enum class for order types
     */
    public enum STATISTIC_TYPE {

        LOW_WAIT("LOW WAIT ORDERS", 1),
        MED_WAIT("MEDIUM WAIT ORDERS", 2),
        HIGH_WAIT("HIGH WAIT ORDERS", 3),
        CANCEL_W_C("CANCEL WITH MATERIAL COST", 4),
        CANCEL_N_C("CANCEL WITHOUT MATERIAL COST", 5);

        private int value;
        private String name;

        STATISTIC_TYPE(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

    }

}
