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
public abstract class MessageType {

    public static final String LOGIN = "Login";
    public static final String KEEP_ALIVE = "Keep-Alive";
    public static final String ORDER = "Order";
    public static final String ORDER_READY = "Order-Ready";
    public static final String PRINT_ORDER = "Print-Order";
    public static final String MERGE_TABLE = "Merge-Table";
}
