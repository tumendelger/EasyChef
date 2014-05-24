/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

import easychef.data.DBConnector;
import easychef.data.RemoteDBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tumee
 */
public class SyncCards {

    private final String getCardDiscounts = "SELECT id, name, percentage, conditions, ctid, action FROM carddiscount WHERE actiontime>?";
    private final String insertCardDiscount = "INSERT INTO carddiscount(id, name, percentage, conditions, ctid) VALUES(?,?,?,?,?)";
    private final String updateCardDiscount = "UPDATE carddiscount SET name=?, percentage=?, conditions=? WHERE id=?";
    private final String deleteCardDiscount = "DELETE FROM carddiscount WHERE id=?";

    private final String getPromoCodes = "SELECT id, code, discount, starts, ends, isactive, pctid, action FROM promocode WHERE actiontime>?";
    private final String insertPromoCode = "INSERT INTO promocode(id, code, discount, starts, ends, isactive, pctid, action) values(?,?,?,?,?,?,?,?)";
    private final String updatePromoCode = "UPDATE promocode set discount=?, starts=?, ends=?, isacitve=?, action=? WHERE id=?";

    private final Logger logger = Logger.getLogger(SyncCards.class.getName());
    private String error_msg;
    private final String last_update;

    public SyncCards(String last_update) {
        this.last_update = last_update;
    }

    public void syncCard() throws SQLException {
        PreparedStatement pStat = RemoteDBConnector.getConnection().prepareStatement(getCardDiscounts);
        PreparedStatement localPs;

        pStat.setString(1, last_update);
        ResultSet res = pStat.executeQuery();
        while (res.next()) {
            long id = res.getLong(1);
            String name = res.getString(2);
            float per = res.getFloat(3);
            int condition = res.getInt(4);
            int catid = res.getInt(5);
            int action = res.getInt(6);
            System.out.println("Item to sync\nID: " + id + ",\nname: " + name + ",\nprecentage: "
                    + per + ",\ncondition: " + condition + ",\ncategoryid: " + catid + ",\naction: " + action);

            //Depending on action insert, update, delete
            switch (action) {
                case 0://New carddiscount item
                    localPs = DBConnector.getConnection().prepareStatement(insertCardDiscount);
                    localPs.setLong(1, id);
                    localPs.setString(2, name);
                    localPs.setFloat(3, per);
                    localPs.setInt(4, condition);
                    localPs.setInt(5, catid);
                    localPs.execute();

                    System.out.println("Added succesfully.");
                    break;
                case 1://Update
                case 2://Delete
                    localPs = DBConnector.getConnection().prepareCall(updateCardDiscount);
                    localPs.setString(1, name);
                    localPs.setFloat(2, per);
                    localPs.setInt(3, condition);
                    localPs.setLong(4, id);
                    localPs.execute();
                    break;
            }
        }
    }

    public void syncPromoCard() throws SQLException {
        PreparedStatement pStat = RemoteDBConnector.getConnection().prepareStatement(getPromoCodes);
        PreparedStatement localStat;
        pStat.setString(1, last_update);
        ResultSet rs = pStat.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            String code = rs.getString(2);
            int discount = rs.getInt(3);
            String startDate = rs.getString(4);
            String endDate = rs.getString(5);
            int isactive = rs.getInt(6);
            int pctid = rs.getInt(7);
            int action = rs.getInt(8);

            logger.info(String.format("Promocode being read --> "
                    + "\nID: %s \ncode: %s \ndiscount: %s \nStartDate: %s \nEndDate: %s \nIsActive: %s \nPCTID: %s \nAction: %s",
                    id, code, discount, startDate, endDate, isactive, pctid, action));

            switch (action) {
                case 0://New 
                    localStat = DBConnector.getConnection().prepareStatement(insertPromoCode);
                    localStat.setInt(1, id);
                    localStat.setString(2, code);
                    localStat.setInt(3, discount);
                    localStat.setString(4, startDate);
                    localStat.setString(5, endDate);
                    localStat.setInt(6, isactive);
                    localStat.setInt(7, pctid);
                    localStat.setInt(8, action);

                    localStat.execute();
                    logger.info(String.format("Successfully executed | %s", localStat.toString()));
                    break;
                case 1://Update
                case 2://Delete
                    localStat = DBConnector.getConnection().prepareStatement(updatePromoCode);
                    localStat.setInt(1, discount);
                    localStat.setString(2, startDate);
                    localStat.setString(3, endDate);
                    localStat.setInt(4, isactive);
                    localStat.setInt(5, action);

                    localStat.execute();
                    logger.info(String.format("Successfully executed | %s", localStat.toString()));
                    break;
            }

        }
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

}
