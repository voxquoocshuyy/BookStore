/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

import huyvq.cart.CartObject;
import huyvq.tblProducts.ProductDAO;
import huyvq.tblProducts.ProductDTO;
import huyvq.tblUsers.UsersDTO;
import java.io.IOException;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private static final String ERROR = "error.html";
    private static final String SUCCESS = "UserViewController";
    private static final String LOGIN = "login.jsp";

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
                if (user != null) {
                    int quantity = 1;
                    String temp1 = request.getParameter("txtBookID");
                    int bookID = Integer.parseInt(temp1);
                    String bookName = request.getParameter("txtBookName");
                    String temp = request.getParameter("txtPrice");
                    float price = Float.parseFloat(temp);
                    ProductDTO book = new ProductDTO(bookID, bookName, price, quantity);
                    CartObject cart = (CartObject) session.getAttribute("CART");

                    if (cart == null) {
                        cart = new CartObject();
                    }
                    ProductDAO dao = new ProductDAO();
                    List<ProductDTO> list = dao.getAll("", "", "");
                    if (list != null) {
                        for (ProductDTO productDTO : list) {
                            if (productDTO.getBookID() == bookID) {
                                int quantityAfter = 0;
                                if (cart.getCart() != null) {
                                    if (cart.getCart().containsKey(productDTO.getBookID() + "")) {
                                        quantityAfter = cart.getCart().get(productDTO.getBookID() + "").getQuantity();
                                    }
                                }
                                if (productDTO.getQuantity() - quantityAfter > 0) {
                                    cart.add(book);
                                    session.setAttribute("CART", cart);
                                    request.setAttribute("NOTI", "Add book to cart successfully");
                                } else {
                                    request.setAttribute("NOTI", "Add book to cart fail");
                                }

                                break;
                            }
                        }
                    }

                    url = SUCCESS;

                } else {
                    url = LOGIN;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("Error at AddToCartServlet : " + e.getMessage());
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
