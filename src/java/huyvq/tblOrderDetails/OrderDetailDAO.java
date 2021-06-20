/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblOrderDetails;

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
public class OrderDetailDAO {
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public boolean createOrder(Connection conn ,OrderDetailDTO order) throws NamingException, SQLException{           
            String sql = "INSERT INTO tblOrderDetails(orderID, bookID, quantity) VALUES (?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, order.getOrderID());
            stm.setInt(2, order.getBookID());
            stm.setInt(3, order.getQuantity());
            return stm.executeUpdate() > 0;
    }
}
