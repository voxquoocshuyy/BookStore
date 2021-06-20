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
public class DiscountError {
    String discountCodeError;
    String discountPercentError;

    public DiscountError() {
    }

    public DiscountError(String discountCodeError, String discountPercentError) {
        this.discountCodeError = discountCodeError;
        this.discountPercentError = discountPercentError;
    }

    public String getDiscountCodeError() {
        return discountCodeError;
    }

    public void setDiscountCodeError(String discountCodeError) {
        this.discountCodeError = discountCodeError;
    }

    public String getDiscountPercentError() {
        return discountPercentError;
    }

    public void setDiscountPercentError(String discountPercentError) {
        this.discountPercentError = discountPercentError;
    }
    
}
