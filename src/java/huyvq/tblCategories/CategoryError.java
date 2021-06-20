/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblCategories;

/**
 *
 * @author Quoc Huy
 */
public class CategoryError {
    String cateIDError;
    String cateNameError;

    public CategoryError() {
    }

    public CategoryError(String cateIDError, String cateNameError) {
        this.cateIDError = cateIDError;
        this.cateNameError = cateNameError;
    }

    public String getCateIDError() {
        return cateIDError;
    }

    public void setCateIDError(String cateIDError) {
        this.cateIDError = cateIDError;
    }

    public String getCateNameError() {
        return cateNameError;
    }

    public void setCateNameError(String cateNameError) {
        this.cateNameError = cateNameError;
    }
    
}
