/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import easychef.data.exceptions.UserNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * User of the system.
 *
 * @author tumee
 */
public final class User {

    private static final Logger logger = Logger.getLogger(User.class.getName());
    private int id;
    private String uname = "";
    private String password = "";
    private String firstname = "";
    private String lastname = "";
    private boolean isActive;
    private int roleid;

    private DBConnector connector;
    private PreparedStatement pStatement;
    private ResultSet rs;

    private final String getUserDetail = "SELECT id, username, password, firstname, lastname, isActive, roleid "
            + "FROM employee WHERE username=?";
    private final String userLogQuery = "INSERT INTO userlog(type, uid) VALUES(?, ?)";

    public User() {
    }

    public User(String uname) {
        setUname(uname);
    }

    public User(int id, String uname, String password, String firstname, String lastname, boolean isActive, int roleid) {
        setId(id);
        setUname(uname);
        setPassword(password);
        setFirstname(firstname);
        setLastname(lastname);
        setIsActive(isActive);
        setRoleid(roleid);
    }

    /**
     * gets user details for given user name
     *
     * @throws SQLException
     * @throws easychef.data.exceptions.UserNotFoundException
     */
    public void getUserDetails() throws SQLException, UserNotFoundException {
        pStatement = DBConnector.getConnection().prepareStatement(getUserDetail);
        pStatement.setString(1, uname);
        connector = new DBConnector();
        rs = connector.getResultSet(pStatement);
        if (rs.next()) {
            setId(rs.getInt("id"));
            setPassword(rs.getString("password"));
            setFirstname(rs.getString("firstname"));
            setLastname(rs.getString("lastname"));
            setIsActive(rs.getBoolean("isActive"));
            setRoleid(rs.getInt("roleid"));
        } else {
            throw new UserNotFoundException("Хэрэглэгчийн мэдээлэл системд алга байна.");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public boolean userLog(int log) throws SQLException {
        pStatement = DBConnector.getConnection().prepareStatement(userLogQuery);
        pStatement.setInt(1, log);
        pStatement.setInt(2, id); 
        return pStatement.execute();        
    }

}
