/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.remote;

;
import easychef.data.DBConnector;
import easychef.data.RemoteDBConnector;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tumee
 */


public class SyncSystemData {

    private final Logger logger = Logger.getLogger(SyncSystemData.class.getName());

    //Employee related queries
    private final String getEmployees = "SELECT id, username, password, firstname, lastname, isactive, roleid, action FROM employee WHERE actiontime>?";
    private final String insertEmployee = "INSERT INTO employee(id, username, password, firstname, lastname, isactive, roleid, action) VALUES(?,?,?,?,?,?,?,?)";
    private final String updateEmployee = "UPDATE employee SET username=?, password=?, firstname=?, lastname=?, isactive=?, roleid=?, action=? WHERE id=?";

    //Members related queries
    private final String getMembers = "SELECT id, firstname, lastname, username, password, register, mobile, email, isactive, numbervisit, "
            + "totalspent, cardnumber, bonus, cdid, action FROM members WHERE actiontime>?";
    private final String insertMember = "INSERT INTO members(id, firstname, lastname, username, password, register, mobile, email, isactive, "
            + "numbervisit, totalspent, cardnumber, bonus, cdid, action) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String updateMember = "UPDATE members SET "
            + "firstname=?, lastname=?, username=?, password=?, "
            + "register=?, mobile=?, email=?, isactive=? "
            + "numbervisit=?, totalspent=?, bonus=?, action=? "
            + "WHERE id=?";

    //Sites
    private final String getSites = "SELECT id, name, action FROM sites WHERE actiontime>?";
    private final String insertSite = "INSERT INTO sites(id, name) VALUES(?,?)";
    private final String updateSite = "UPDATE sites SET name=?, action=? WHERE id=?";

    //Tables related queries
    private final String getTables = "SELECT id, tableid, isreserved, isfree, totalperson, merge, action FROM tables WHERE actiontime>?";
    private final String insertTable = "INSERT INTO tables (id, tableid, isreserved, isfree, totalperson, merge) VALUES(?,?,?,?,?,?)";
    private final String updateTable = "UPDATE tables SET isreserved=?,  isfree=?, totalperson=?, merge=?, action=? WHERE id=?";

    private PreparedStatement remotePS, localPS;
    private ResultSet res;

    private final String last_update;

    public SyncSystemData(String last_update) {
        this.last_update = last_update;
    }

    public void syncEmployees() {
        try {
            //read data from remote
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getEmployees);
            remotePS.setString(1, last_update);
            res = remotePS.executeQuery();

            //sync with local database
            while (res.next()) {
                int id = res.getInt(1);
                String uname = res.getString(2);
                String pwd = res.getString(3);
                String fname = res.getString(4);
                String lname = res.getString(5);
                int isactive = res.getInt(6);
                int role = res.getInt(7);
                int action = res.getInt(8);

                System.out.println("Employee to be synced --> \n" + "id: " + id
                        + "\nuname: " + uname
                        + "\npass: " + pwd
                        + "\nfname: " + fname
                        + "\nlname: " + lname
                        + "\nisactive: " + isactive
                        + "\nrole: " + role
                        + "\naction: " + action);

                switch (action) {
                    case 0://New insert
                        localPS = DBConnector.getConnection().prepareStatement(insertEmployee);
                        localPS.setLong(1, id);
                        localPS.setString(2, uname);
                        localPS.setString(3, pwd);
                        localPS.setString(4, fname);
                        localPS.setString(5, lname);
                        localPS.setInt(6, isactive);
                        localPS.setInt(7, role);
                        localPS.setInt(8, action);

                        localPS.execute();
                        logger.log(Level.INFO, "Succesfully executed | {0}", localPS.toString());

                        break;

                    case 1://Update existing
                    case 2://delete existing
                        localPS = (PreparedStatement) DBConnector.getConnection().prepareStatement(updateEmployee);
                        localPS.setString(1, uname);
                        localPS.setString(2, pwd);
                        localPS.setString(3, fname);
                        localPS.setString(4, lname);
                        localPS.setInt(5, isactive);
                        localPS.setInt(6, role);
                        localPS.setInt(7, action);
                        localPS.setInt(8, id);

                        localPS.execute();
                        logger.log(Level.INFO, "Successfully executed | {0}", localPS.toString());
                        break;

                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void syncMembers() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getMembers);
            remotePS.setString(1, last_update);

            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String fname = res.getString(2);
                String lname = res.getString(3);
                String uname = res.getString(4);
                String pwd = res.getString(5);
                String reg = res.getString(6);
                String mobile = res.getString(7);
                String email = res.getString(8);
                int isactive = res.getInt(9);
                int nvisits = res.getInt(10);
                double totalSpent = res.getDouble(11);
                String cardnum = res.getString(12);
                double bonus = res.getDouble(13);
                int cdid = res.getInt(14);
                int action = res.getInt(15);

                switch (action) {
                    case 0://New member insert
                        localPS = DBConnector.getConnection().prepareStatement(insertMember);
                        localPS.setInt(1, id);
                        localPS.setString(2, fname);
                        localPS.setString(3, lname);
                        localPS.setString(4, uname);
                        localPS.setString(5, pwd);
                        localPS.setString(6, reg);
                        localPS.setString(7, mobile);
                        localPS.setString(8, email);
                        localPS.setInt(9, isactive);
                        localPS.setInt(10, nvisits);
                        localPS.setDouble(11, totalSpent);
                        localPS.setString(12, cardnum);
                        localPS.setDouble(13, bonus);
                        localPS.setInt(14, cdid);
                        localPS.setInt(15, action);

                        localPS.execute();
                        logger.log(Level.INFO, "Successfully executed | {0}", localPS.toString());
                        break;
                    case 1://Update members
                    case 2://Delete members
                        localPS = DBConnector.getConnection().prepareStatement(updateMember);
                        localPS.setString(1, fname);
                        localPS.setString(2, lname);
                        localPS.setString(3, uname);
                        localPS.setString(4, pwd);
                        localPS.setString(5, reg);
                        localPS.setString(6, mobile);
                        localPS.setString(7, email);
                        localPS.setInt(8, isactive);
                        localPS.setInt(9, nvisits);
                        localPS.setDouble(10, totalSpent);
                        localPS.setDouble(11, bonus);
                        localPS.setInt(12, action);

                        int n = localPS.executeUpdate();
                        logger.log(Level.INFO, String.format("Successfully executed | %s | Updated row cnt: %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void syncSites() {
        try {
            remotePS = RemoteDBConnector.getConnection().prepareStatement(getSites);
            remotePS.setString(1, last_update);
            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                int action = res.getInt(3);

                System.out.println(String.format("ID: %s\nName: %s\nAction: %s", id, name, action));

                switch (action) {
                    case 0://New
                        localPS = DBConnector.getConnection().prepareStatement(insertSite);
                        localPS.setInt(1, id);
                        localPS.setString(2, name);

                        System.out.println(localPS.toString());

                        localPS.execute();
                        logger.log(Level.INFO, "Successfully executed | {0}", localPS.toString());
                        break;
                    case 1://Update
                    case 2://Delete
                        localPS = DBConnector.getConnection().prepareStatement(updateSite);
                        localPS.setString(1, name);
                        localPS.setInt(2, action);
                        localPS.setInt(3, id);

                        int n = localPS.executeUpdate();
                        logger.log(Level.INFO, String.format("Successfully executed | %s | Updated row cnt: %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void syncTables() {
        try {
            remotePS = (PreparedStatement) RemoteDBConnector.getConnection().prepareStatement(getTables);
            remotePS.setString(1, last_update);

            logger.info(String.format("Getting [tables] from the remote server | %s", remotePS.toString()));

            res = remotePS.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                int tableid = res.getInt(2);
                int isreserved = res.getInt(3);
                int isfree = res.getInt(4);
                int totalPerson = res.getInt(5);
                int merge = res.getInt(6);
                int action = res.getInt(7);

                switch (action) {
                    case 0://New
                        localPS = (PreparedStatement) DBConnector.getConnection().prepareStatement(insertTable);
                        localPS.setInt(1, id);
                        localPS.setInt(2, tableid);
                        localPS.setInt(3, isreserved);
                        localPS.setInt(4, isfree);
                        localPS.setInt(5, totalPerson);
                        localPS.setInt(6, merge);

                        localPS.execute();
                        logger.log(Level.INFO, "Successfully executed | {0}", localPS.toString());

                        break;
                    case 1://Update
                    case 2://Delete
                        localPS = (PreparedStatement) DBConnector.getConnection().prepareStatement(updateTable);
                        localPS.setInt(1, isreserved);
                        localPS.setInt(2, isfree);
                        localPS.setInt(3, totalPerson);
                        localPS.setInt(4, merge);
                        localPS.setInt(5, action);
                        localPS.setInt(6, id);

                        int n = localPS.executeUpdate();
                        logger.log(Level.INFO, String.format("Successfully executed | %s | Updated row cnt: %s", localPS.toString(), n));
                        break;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
