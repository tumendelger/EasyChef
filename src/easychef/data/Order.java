/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easychef.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Tumendelger
 */
@XmlRootElement(namespace="Order")
@XmlType(propOrder = {"oid", "orderTime", "totalPrice", "vat", "discount", "cash", "card", "isPaid", "totalAmount", "tid", "mid", "uid", "listOfFoods", "listOfDrinks"})
public class Order extends MessageType {

    /**
     * action values 'New, Update, Cancel, Payment '
     */
    private String action;
    private Long oid;
    private String orderTime;
    private Double totalPrice;
    private Double vat;
    private Double discount;
    private Double cash;
    private Double card;
    private Integer isPaid;
    private Double totalAmount;
    private int tid;
    private int mid;
    private int uid;

    private ArrayList<FoodOrderDetail> listOfFoods;
    private ArrayList<DrinkOrderDetail> listOfDrinks;
    public Order() {
    }

    public Order(String action, Long oid, String orderTime, Double totalPrice, Double vat, Double discount, Double cash, Double card, Integer isPaid, Double totalAmount, int tid, int mid, int uid) {
        this.action = action;
        this.oid = oid;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
        this.vat = vat;
        this.discount = discount;
        this.cash = cash;
        this.card = card;
        this.isPaid = isPaid;
        this.totalAmount = totalAmount;
        this.tid = tid;
        this.mid = mid;
        this.uid = uid;
    }

    public String getAction() {
        return action;
    }

    @XmlAttribute
    public void setAction(String action) {
        this.action = action;
    }

    public Long getOid() {
        return oid;
    }

    @XmlElement
    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getOrderTime() {
        return orderTime;
    }

    @XmlElement
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    @XmlElement
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getVat() {
        return vat;
    }

    @XmlElement
    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getDiscount() {
        return discount;
    }

    @XmlElement
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getCash() {
        return cash;
    }

    @XmlElement
    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getCard() {
        return card;
    }

    @XmlElement
    public void setCard(Double card) {
        this.card = card;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    @XmlElement
    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    @XmlElement
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTid() {
        return tid;
    }

    @XmlElement
    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getMid() {
        return mid;
    }

    @XmlElement
    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getUid() {
        return uid;
    }

    @XmlElement
    public void setUid(int uid) {
        this.uid = uid;
    }

    public ArrayList<FoodOrderDetail> getListOfFoods() {
        return listOfFoods;
    }

    @XmlElementWrapper(name = "Kitchen")
    @XmlElement(name = "Food")
    public void setListOfFoods(ArrayList<FoodOrderDetail> listOfDetails) {
        this.listOfFoods = listOfDetails;
    }

    public ArrayList<DrinkOrderDetail> getListOfDrinks() {
        return listOfDrinks;
    }

    @XmlElementWrapper(name = "Tek")
    @XmlElement(name = "Drink")
    public void setListOfDrinks(ArrayList<DrinkOrderDetail> listOfDrinks) {
        this.listOfDrinks = listOfDrinks;
    }
    
    

}
