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
public class DrinkOrderDetail {

    private long doid;
    private int dID;
    private Integer tableID;
    private String drinkName;
    private String dorderTime;
    private Integer waitMinutes;
    private Boolean cancelByW;
    private Boolean cancelByCh;

    public DrinkOrderDetail() {
    }

    public DrinkOrderDetail(long doiD, int dID) {
        this.doid = doiD;
        this.dID = dID;
    }

    public long getDoid() {
        return doid;
    }
    
    public void setDoid(long doid) {
        this.doid = doid;
    }

    public int getdID() {
        return dID;
    }

    public void setdID(int dID) {
        this.dID = dID;
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDorderTime() {
        return dorderTime;
    }

    public void setDorderTime(String dorderTime) {
        this.dorderTime = dorderTime;
    }

    public Integer getWaitMinutes() {
        return waitMinutes;
    }

    public void setWaitMinutes(Integer waitMinutes) {
        this.waitMinutes = waitMinutes;
    }

    public Boolean isCancelByW() {
        return cancelByW;
    }

    public void setCancelByW(Boolean cancelByW) {
        this.cancelByW = cancelByW;
    }

    public Boolean isCancelByCh() {
        return cancelByCh;
    }

    public void setCancelByCh(Boolean cancelByCh) {
        this.cancelByCh = cancelByCh;
    }

}
