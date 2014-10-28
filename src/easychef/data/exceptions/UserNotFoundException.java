/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data.exceptions;

/**
 * Custom exception class when specified User is not exists in the DB
 *
 * @author tumee
 */
public class UserNotFoundException extends Exception {

    /**
     * Sets custom message as a exception message
     *
     * @param msg
     */
    public UserNotFoundException(String msg) {
        super(msg);
    }

}
