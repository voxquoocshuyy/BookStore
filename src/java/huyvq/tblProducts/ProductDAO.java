/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblProducts;

import huyvq.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Quoc Huy
 */
public class ProductDAO {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<ProductDTO> getAll(String bookName, String price, String categoryName) throws SQLException, NamingException {
        List<ProductDTO> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT bookID, bookName, price, quantity, description, author, status, tblCategories.categoryName FROM tblProducts\n"
                    + "JOIN tblCategories ON tblProducts.categoryID = tblCategories.categoryID\n"
                    + "WHERE bookName LIKE ? AND quantity > 0 AND status = 'active' \n";
            if (price != null) {
                sql += "AND price LIKE '%" + price + "%'\n";
            }
            if (categoryName != null) {
                sql += "AND tblCategories.categoryName LIKE '%" + categoryName + "%'\n";
            }
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + bookName + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int bookID = rs.getInt(1);
                String bookName1 = rs.getString(2);
                float price1 = rs.getFloat(3);
                int quantity = rs.getInt(4);
                String description = rs.getString(5);
                String author = rs.getString(6);
                String status = rs.getString(7);
                String categoryName1 = rs.getString(8);
                list.add(new ProductDTO(bookID, bookName1, price1, quantity, description, author, status, categoryName1));
            }

        } finally {
            closeConnection();
        }
        return list;
    }

    public ProductDTO getBookByID(int bookID) throws NamingException, SQLException {
        ProductDTO book = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT bookID, bookName, price, quantity, description, author, tblCategories.categoryID FROM tblProducts\n"
                    + "JOIN tblCategories ON tblProducts.categoryID = tblCategories.categoryID\n"
                    + "WHERE bookID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, bookID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int bookID1 = rs.getInt(1);
                String bookName = rs.getString(2);
                float price = rs.getFloat(3);
                int quantity = rs.getInt(4);
                String description = rs.getString(5);
                String author = rs.getString(6);
                String categoryID = rs.getString(7);
                book = new ProductDTO(bookID1, bookName, price, quantity, description, author, categoryID);
            }
        } finally {
            closeConnection();
        }
        return book;
    }
    
    public ProductDTO getById(Connection conn, int bookID) throws NamingException, SQLException {
        String sql = "SELECT bookName, price FROM tblProducts WHERE bookID = ?";
        stm = conn.prepareStatement(sql);
        stm.setInt(1, bookID);
        rs = stm.executeQuery();
        if (rs.next()) {
            String name = rs.getString(1);
            float price = rs.getFloat(2);
            return new ProductDTO(bookID, name, price, 0);
        }

        return null;
    }

    public boolean deleteBook(int bookID) throws NamingException, SQLException {
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblProducts SET status = 'Inactive' WHERE bookID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, bookID);
            return stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
    }
    
    public boolean updateQuantity(Connection conn,int bookID, int quantity) throws NamingException, SQLException {
            String sql = "update tblProducts Set quantity = quantity - ? WhERE bookID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(2, bookID);
            stm.setInt(1, quantity);
            return stm.executeUpdate() > 0;
       
    }

    public boolean create(ProductDTO book) throws NamingException, SQLException {
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblProducts(bookName, price, quantity, description, author, categoryID, image) VALUES (?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, book.getBookName());
            stm.setFloat(2, book.getPrice());
            stm.setInt(3, book.getQuantity());
            stm.setString(4, book.getDescription());
            stm.setString(5, book.getAuthor());
            stm.setString(6, book.getCategoryID());
            stm.setString(7, book.getImages());
            return stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
    }

    public boolean update(ProductDTO book) throws NamingException, SQLException {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblProducts SET bookName = ?, price = ?, quantity = ?, description = ?, author = ?, categoryID = ? WHERE bookID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, book.getBookName());
            stm.setFloat(2, book.getPrice());
            stm.setInt(3, book.getQuantity());
            stm.setString(4, book.getDescription());
            stm.setString(5, book.getAuthor());
            stm.setString(6, book.getCategoryID());
            stm.setInt(7, book.getBookID());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
