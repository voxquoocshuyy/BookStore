/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblCategories;

import huyvq.utils.DBUtils;
import java.sql.Connection;
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
public class CategoryDAO {
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void closeConnection() throws SQLException {
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

    public List<CategoryDTO> getAll() throws NamingException, SQLException {
        List<CategoryDTO> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT categoryID, categoryName FROM tblCategories";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                String id = rs.getString(1);
                String name = rs.getString(2);
                list.add(new CategoryDTO(id, name));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public CategoryDTO getById(String id) throws NamingException, SQLException {
        conn = DBUtils.getConnection();
        String sql = "SELECT categoryName FROM tblCategories WHERE categoryID = ?";
        stm = conn.prepareStatement(sql);
        stm.setString(1, id);
        rs = stm.executeQuery();
        if (rs.next()) {
            String name = rs.getString(1);
            return new CategoryDTO(id, name);
        }
        return null;
    }
    
    public boolean deleteCate(String cateID) throws NamingException, SQLException{
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE tblCategories WHERE categoryID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, cateID);
            return stm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
    }
    
    public boolean createCate(CategoryDTO cate) throws NamingException, SQLException{
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblCategories(categoryID, categoryName) VALUES (?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, cate.getCategoryID());
            stm.setString(2, cate.getCategoryName());
            return stm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
    }
    
    public boolean update(CategoryDTO cate) throws NamingException, SQLException{
        boolean check =false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblCategories SET categoryName = ? WHERE categoryID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, cate.getCategoryName());
            stm.setString(2, cate.getCategoryID());
            check = stm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
}
