/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblOrderDetails;

/**
 *
 * @author Quoc Huy
 */
public class OrderDetailDTO {
    int orderID;
    int bookID;
    int quantity;
    float total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderID, int bookID, int quantity, float total) {
        this.orderID = orderID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
