/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

/**
 *
 * @author tumee
 */
public class Login extends MessageType {

    private int uid;
    private String pwd = "";
    private int role;
    private int status;

    private String insertUserLogin = "INSERT INTO ";
    private String getUID = "SELECT id FROM employee WHERE username = ?";

    public Login() {
    }

    public Login(int uid, int role, String pwd, int status) {
        this.uid = uid;
        this.role = role;
        this.pwd = pwd;
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
