/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblUsers;

import huyvq.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Quoc Huy
 */
public class UsersDAO {

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
    
    public UsersDTO checkLogin(String userID, String password) throws SQLException, NamingException{
        UsersDTO user = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT userID, password, name, status, role FROM tblUsers WHERE userID = ? AND password = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, password);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String status = rs.getString("status");
                String role = rs.getString("role");
                user = new UsersDTO(userID, password, name, status, role);
            }
        }finally{
            closeConnection();
        }
        return user;
    }
}
