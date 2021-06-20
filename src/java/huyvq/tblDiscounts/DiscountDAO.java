/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblDiscounts;

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
public class DiscountDAO {

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

    public boolean create(DiscountDTO dto) throws NamingException, SQLException {
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblDiscounts(discountCode, discountPercent) VALUES (?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getDiscountCode());
            stm.setInt(2, dto.getDiscountPercent());
            return stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
    }

    public DiscountDTO getDiscount(String discountCode) throws SQLException, NamingException {
        DiscountDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT discountID, discountCode, discountPercent, discountStatus FROM tblDiscounts WHERE discountCode = ? ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, discountCode);
            rs = stm.executeQuery();
            while (rs.next()) {
                int discountID = rs.getInt(1);
                String discountCode1 = rs.getString(2);
                int discountPercent = rs.getInt(3);
                String discountStatus = rs.getString(4);
                dto = new DiscountDTO(discountID, discountCode1, discountPercent, discountStatus);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<DiscountDTO> getAllDiscount() throws SQLException, NamingException {
        List<DiscountDTO> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT discountID, discountCode, discountPercent, discountStatus FROM tblDiscounts WHERE discountStatus = 'active'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int discountID = rs.getInt(1);
                String discountCode1 = rs.getString(2);
                int discountPercent = rs.getInt(3);
                String discountStatus = rs.getString(4);

                list.add(new DiscountDTO(discountID, discountCode1, discountPercent, discountStatus));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean updateDiscount(DiscountDTO dis) throws NamingException, SQLException {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblDiscounts SET discountStatus = 'inactive' WHERE discountCode = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dis.getDiscountCode());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
