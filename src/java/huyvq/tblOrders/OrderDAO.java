/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblOrders;

import huyvq.tblOrderDetails.OrderDetailDAO;
import huyvq.tblOrderDetails.OrderDetailDTO;
import huyvq.tblProducts.ProductDAO;
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
public class OrderDAO {

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
    
    public List<OrderDTO> getAllDate(Date dateOrder, String userID) throws SQLException, NamingException {
        List<OrderDTO> list = null;
        ProductDAO proDAO = new ProductDAO();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT tblOrders.orderID, tblOrderDetails.bookID, tblOrderDetails.quantity, tblOrders.dateOrder, tblOrders.userID FROM tblOrders\n"
                    + "JOIN tblOrderDetails ON tblOrders.orderID = tblOrderDetails.orderID\n"
                    + "WHERE dateOrder = ? AND userID = ?\n"
                    + "ORDER BY dateOrder DESC";   
            stm = conn.prepareStatement(sql);
            stm.setDate(1, dateOrder );
            stm.setString(2, userID);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int orderID = rs.getInt(1);
                int bookID = rs.getInt(2);
                String bookName = proDAO.getById(conn, bookID).getBookName();
                float price = proDAO.getById(conn, bookID).getPrice();
                int quantity = rs.getInt(3);
                float total = price * quantity;
//                Date dateOrder1 = rs.getDate(4);
                list.add(new OrderDTO(orderID, bookName, quantity, dateOrder, total));
            }

        } finally {
            closeConnection();
        }
        return list;
    }
    
    public List<OrderDTO> getAll(String userID) throws SQLException, NamingException {
        List<OrderDTO> list = null;
        ProductDAO proDAO = new ProductDAO();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT tblOrders.orderID, tblOrderDetails.bookID, tblOrderDetails.quantity, tblOrders.dateOrder, tblOrders.userID FROM tblOrders\n"
                    + "JOIN tblOrderDetails ON tblOrders.orderID = tblOrderDetails.orderID\n"
                    + "WHERE userID = ?\n"
                    + "ORDER BY dateOrder DESC";    
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int orderID = rs.getInt(1);
                int bookID = rs.getInt(2);
                String bookName = proDAO.getById(conn, bookID).getBookName();
                float price = proDAO.getById(conn, bookID).getPrice();
                int quantity = rs.getInt(3);
                float total = price * quantity;
                Date dateOrder1 = rs.getDate(4);
                list.add(new OrderDTO(orderID, bookName, quantity, dateOrder1, total));
            }

        } finally {
            closeConnection();
        }
        return list;
    }

    
    public boolean create(OrderDTO order, List<OrderDetailDTO> list) throws NamingException, SQLException {
        boolean check = false;
        OrderDetailDAO dao = new OrderDetailDAO();
         ProductDAO daoP = new ProductDAO();
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO tblOrders(userID, total) VALUES (?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, order.getUserID());
            stm.setFloat(2, order.getTotal());
            check = stm.executeUpdate() > 0;
            if (check) {
                int orderID = getLastID(conn);
                for (OrderDetailDTO orderDetailDTO : list) {
                    orderDetailDTO.setOrderID(orderID);
                    check = dao.createOrder(conn, orderDetailDTO);
                    if (!check) {
                        conn.rollback();
                        return check;
                    }
                    check = daoP.updateQuantity(conn, orderDetailDTO.getBookID(), orderDetailDTO.getQuantity());
                }
                if (check == true) {
                    conn.commit();
                }

            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getLastID(Connection conn) throws SQLException {
        String sql1 = "SELECT orderID AS LastID FROM tblOrders WHERE orderID = @@Identity;";
        stm = conn.prepareStatement(sql1);
        rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt("LastID");
        }
        return -1;
    }

}
