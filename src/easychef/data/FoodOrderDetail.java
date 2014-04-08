/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

/**
 *
 * @author Tumendelger
 */
public final class FoodOrderDetail {

    private long foid;
    private int fID;
    private Integer tableID;
    private String foodName;
    private String dorderTime;
    private Integer waitMinutes;
    private Boolean cancelByW;
    private Boolean cancelByCh;

    public FoodOrderDetail() {
    }

    /**
     *
     * @param foid
     * @param fid
     */
    public FoodOrderDetail(long foid, int fid) {
        this.foid = foid;
        this.fID = fid;
    }

    public long getFoid() {
        return foid;
    }

    public void setFoid(long foid) {
        this.foid = foid;
    }

    public int getfID() {
        return fID;
    }

    public void setfID(int fID) {
        this.fID = fID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDorderTime() {
        return dorderTime;
    }

    public void setDorderTime(String orderTime) {
        this.dorderTime = orderTime;
    }

    public int getWaitMinutes() {
        return waitMinutes;
    }

    public void setWaitMinutes(int waitMinutes) {
        this.waitMinutes = waitMinutes;
    }

    public boolean getCancelByW() {
        return cancelByW;
    }

    public void setCancelByW(boolean cancelByW) {
        this.cancelByW = cancelByW;
    }

    public boolean getCancelByCh() {
        return cancelByCh;
    }

    public void setCancelByCh(boolean cancelByCh) {
        this.cancelByCh = cancelByCh;
    }

}
