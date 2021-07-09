/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblProducts;

import java.sql.Date;

/**
 *
 * @author Quoc Huy
 */
public class ProductDTO {
    int bookID;
    String bookName;
    float price;
    int quantity;
    String description;
    String author;
    String status;
    Date dateAdd;
    String categoryName;
    String categoryID;
    String images;
    public ProductDTO() {
    }

    public ProductDTO(int bookID) {
        this.bookID = bookID;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    

    public ProductDTO(int bookID, String bookName, float price, int quantity, String description, String author, String status, String categoryName) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.author = author;
        this.status = status;
        this.categoryName = categoryName;
    }

    public ProductDTO(int bookID, String bookName, float price, int quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
    }
    
    public ProductDTO(int bookID, String bookName, float price, int quantity, String description, String author, String categoryID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.author = author;
        this.categoryID = categoryID;
    }

    public ProductDTO(String bookName, float price, int quantity, String description, String author, String categoryID, String images) {
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.author = author;
        this.categoryID = categoryID;
        this.images = images;
    }



    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }


    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }
    
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float frice) {
        this.price = frice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductDTO other = (ProductDTO) obj;
        if (this.bookID != other.bookID) {
            return false;
        }
        return true;
    }

    
    
}
