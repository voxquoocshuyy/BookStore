/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblOrders;

import java.sql.Date;

/**
 *
 * @author Quoc Huy
 */
public class OrderDTO {
    int orderID;
    String bookName;
    String userID;
    int quantity;
    Date dateOrder;
    String statusOrder;
    float total;
    public OrderDTO() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public OrderDTO(int orderID, String bookName, int quantity, Date dateOrder, float total) {
        this.orderID = orderID;
        this.bookName = bookName;
        this.quantity = quantity;
        this.dateOrder = dateOrder;
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
    public OrderDTO(String userID, float total) {
        this.userID = userID;
        this.total = total;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    
    
    public OrderDTO(int orderID, String userID, Date dateOrder, String statusOrder) {
        this.orderID = orderID;
        this.userID = userID;
        this.dateOrder = dateOrder;
        this.statusOrder = statusOrder;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
    
}
