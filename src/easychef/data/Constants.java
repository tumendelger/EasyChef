/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

/**
 * Constant values used with EasyChef Application
 *
 * @author tumee
 */
public final class Constants {

    /**
     * Restricts new creation
     */
    private Constants() {
        // Restrict new creation
    }

    //Database related constants
    public static final String USE_UTF8 = "useUnicode=true&characterEncoding=utf-8";
    public static final String Local_DB_URL = "jdbc:mysql://localhost/";
    public static final String Remote_DB_URL = "jdbc:mysql://localhost/";
    public static final String DB_NAME = "easysystems";
    public static final String Remote_DB_NAME = "remote";
    public static final String DB_USER = "easysystems";
    public static final String DB_PASSWORD = "JmJYpmqy2cPXsKar";

    //User role constants
    public static final int MANEGER_ROLE_ID = 1;
    public static final int CHEF_ROLE_ID = 2;
    public static final int SUPER_ADMIN_ROLE_ID = 4;
    public static final int ADMIN_ROLE_ID = 5;

    //User log constants
    public static final int USER_LOGGED_IN = 1;
    public static final int USER_LOGGED_OUT = 0;

    //Network related constants
    public static final int CLIENT_PORT = 5000;
    public static final int SERVER_PORT = 7200;

    //Audio files
    public static final String NEW_ORDER = "resources\\sounds\\new-order.wav";
    public static final String CANCEL_ORDER = "resources\\sounds\\cancel-order.wav";
    public static final String PRINT_BILL = "resources\\sounds\\print-done.wav";
    public static final String DELIVER_ORDER = "resources\\sounds\\deliver-order.wav";

    //Server status
    public static final String SERVER_UP = "Ажиллаж байна";
    public static final String SERVER_DOWN = "Унтарсан байна";
    public static final String START_SERVER = "Сервер асаах";
    
    //Printers
    public static final String PRINTER_OK = "Холбогдсон байна";
    public static final String PRINTER_NOT_FOUND = "Олдсонгүй";
    
}
