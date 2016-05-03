package domain;

import java.util.Date;

/**
 * Created by RZ on 4/21/16.
 */
public class Orders {
    private int id;
    private int table_id;
    private Date orderDate;
    private double totalPrice;
    private double totalVipPrice;
    private int orderStatus;

    public double getTotalVipPrice() {
        return totalVipPrice;
    }

    public void setTotalVipPrice(double totalVipPrice) {
        this.totalVipPrice = totalVipPrice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
