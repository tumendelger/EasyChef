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
public final class Constants {

    private Constants() {
        // Restrict new creation
    }

    public static final String DB_URL = "jdbc:mysql://localhost/";
    public static final String DB_NAME = "easysystems";
    public static final String DB_USER = "easysystems";
    public static final String DB_PASSWORD = "JmJYpmqy2cPXsKar";
    public static final int MANEGER_ROLE_ID = 1;
    public static final int CHEF_ROLE_ID = 2;
    public static final int SUPER_ADMIN_ROLE_ID = 4;
    public static final int ADMIN_ROLE_ID = 5;
    public static final int USER_LOGGED_IN = 1;
    public static final int USER_LOGGED_OUT = 0;

}
