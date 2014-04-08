/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author tumee
 */
@XmlRootElement(namespace = "order-ready")
@XmlType(propOrder={"id", "tableId"})
public class OrderReady extends MessageType {

    private String id;
    private int tableId;

    public String getId() {
        return id;
    }

    @XmlElement
    public void setId(String id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    @XmlElement
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

}
