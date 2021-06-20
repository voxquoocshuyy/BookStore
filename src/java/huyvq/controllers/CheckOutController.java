/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

import huyvq.cart.CartObject;
import huyvq.tblOrderDetails.OrderDetailDTO;
import huyvq.tblOrders.OrderDAO;
import huyvq.tblOrders.OrderDTO;
import huyvq.tblProducts.ProductDAO;
import huyvq.tblProducts.ProductDTO;
import huyvq.tblUsers.UsersDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Quoc Huy
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "checkOutSuccessfully.html";
    private static final String LOGIN = "login.jsp";
    private static final String VIEWCART = "viewCart.jsp";
    private static final String ERRORCHECKOUT = "checkOut.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UsersDTO user = (UsersDTO) session.getAttribute("LOGIN_USER");
                CartObject cart = (CartObject) session.getAttribute("CART");
                String userID = user.getUserID();
                String temp = request.getParameter("txtTotal");
                float total = Float.parseFloat(temp);
                if (userID == null) {
                    url = LOGIN;
                }
                if (cart == null) {
                    url = VIEWCART;
                }
                if (userID != null && cart != null) {
                    OrderDTO dto = new OrderDTO(userID, total);
                    OrderDAO dao = new OrderDAO();
                    List<OrderDetailDTO> list = new ArrayList<>();
                    for (ProductDTO book : cart.getCart().values()) {
                        OrderDetailDTO dto1 = new OrderDetailDTO();
                        dto1.setBookID(book.getBookID());
                        dto1.setQuantity(book.getQuantity());
                        list.add(dto1);
                    }
                    ProductDAO daoP = new ProductDAO();
                    List<ProductDTO> listP = daoP.getAll("", "", "");
                    boolean errorQuantity = false;
                    for (OrderDetailDTO orderDetailDTO : list) {
                        if (listP.contains(new ProductDTO(orderDetailDTO.getBookID()))) {
                            int quantityInDB = listP.get(listP.indexOf(new ProductDTO(orderDetailDTO.getBookID()))).getQuantity();
                            System.out.println("DB:" + quantityInDB);
                            int quantityInOrder = orderDetailDTO.getQuantity();
                            System.out.println("Order:" + quantityInOrder);
                            if (quantityInDB < quantityInOrder || quantityInDB == 0) {
                                errorQuantity = true;
                                request.setAttribute("NOTI", "Check Out Error");
                                break;
                            }
                        } else {
                            errorQuantity = true;
                            request.setAttribute("NOTI", "Check Out Error");
                        }
                    }
                    if (!errorQuantity) {
                        boolean temp1 = dao.create(dto, list);
                        if (temp1 == true) {
                            session.removeAttribute("CART");
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    } else {
                        url = ERRORCHECKOUT;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("Error at CheckOutController : " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
