/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.net;

/**
 *
 * @author tumee
 */
public final class Message {

    private long id;
    private msgType mtype;

    public Message() {
        id = 0;
    }

    public Message(msgType msgType, long l) {
        setMtype(msgType);
        setId(l);
    }

    public long getId() {
        return id;
    }

    public msgType getMtype() {
        return mtype;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMtype(msgType mtype) {
        this.mtype = mtype;
    }

    public enum msgType {

        ALL,
        PRINT,
        CANCEL,
        C_CANCEL_WITH_COST,   //Chef cancelled order with cost
        C_CANCEL_NO_COST,   // Chef cancelled with no cost
        ORDER,
        UPDATE,
        UPDATE_DRINK,
        DELIVER,
        SHUTDOWN
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(getMtype()).append(": ");
        sb.append(getId()).append("]");
        return sb.toString();
    }
}
