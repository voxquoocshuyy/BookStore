/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

import huyvq.cart.CartObject;
import huyvq.tblProducts.ProductDAO;
import huyvq.tblProducts.ProductDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author Quoc Huy
 */
@WebServlet(name = "UserViewController", urlPatterns = {"/UserViewController"})
public class UserViewController extends HttpServlet {

    private final static String ERROR = "error.html";
    private final static String SUCCESS = "userView.jsp";

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
        String nameBook = request.getParameter("nameBook");
        String categoryName = request.getParameter("categoryName");
        String price = request.getParameter("price");
        if (price == "") {
            price = null;
        }
        if (categoryName == "") {
            categoryName = null;
        }
        if (nameBook == null) {
            nameBook = "";
        }
        try {
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list = dao.getAll(nameBook, price, categoryName);
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart != null) {
                for (ProductDTO productDTO : list) {
                    int quantityLucDau = productDTO.getQuantity();
                    if (cart.getCart() != null) {
                        if (cart.getCart().containsKey(productDTO.getBookID() + "")) {
                            int quantityOfProductInCart = cart.getCart().get(productDTO.getBookID() + "").getQuantity();
                            productDTO.setQuantity(quantityLucDau - quantityOfProductInCart);
                        }
                    }

                }

            }
            request.setAttribute("LIST_BOOK", list);
            url = SUCCESS;
        } catch (Exception e) {
         
            log("Error at UserViewController: " + e.getMessage());
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
