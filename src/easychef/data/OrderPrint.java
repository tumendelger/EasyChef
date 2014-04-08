/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package easychef.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tumee
 */
@XmlRootElement(namespace="print-order")
public class OrderPrint extends MessageType{
    private long id;
    private int status;

    public OrderPrint() {
    }

    public long getId() {
        return id;
    }

    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    /**
     * Sets order printing status
     * @param status (0) - not printed (1) - print completed
     */
    @XmlElement
    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
