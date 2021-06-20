/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblDiscounts;

/**
 *
 * @author Quoc Huy
 */
public class DiscountDTO {
    int discountID;
    String discountCode;
    int discountPercent;
    String discountStatus; 

    public DiscountDTO(String discountCode, int discountPercent) {
        this.discountCode = discountCode;
        this.discountPercent = discountPercent;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(String discountStatus) {
        this.discountStatus = discountStatus;
    }

    public DiscountDTO(int discountID, String discountCode, int discountPercent, String discountStatus) {
        this.discountID = discountID;
        this.discountCode = discountCode;
        this.discountPercent = discountPercent;
        this.discountStatus = discountStatus;
    }

    public DiscountDTO() {
    }
    
}
