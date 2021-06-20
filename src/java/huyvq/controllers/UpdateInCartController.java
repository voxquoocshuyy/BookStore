/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

import huyvq.cart.CartObject;
import huyvq.tblProducts.ProductDAO;
import huyvq.tblProducts.ProductDTO;
import huyvq.tblProducts.ProductError;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UpdateInCartController", urlPatterns = {"/UpdateInCartController"})
public class UpdateInCartController extends HttpServlet {

    private static final String ERROR = "error.html";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String temp = request.getParameter("txtBookID");
            int bookID = Integer.parseInt(temp);
            log("bookID : "+ bookID);
            String temp1 = request.getParameter("txtQuantity");
            int quantity = Integer.parseInt(temp1);
            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.getBookByID(bookID);
            int quantityInDB = dto.getQuantity();
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (quantity > quantityInDB) {
                request.setAttribute("NOTI", "Not enough books");
                url = SUCCESS;
            } else {
                cart.update(bookID, quantity);
                session.setAttribute("CART", cart);
                request.setAttribute("NOTI", "Update quantity book from cart successfully! ");
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at UpdateInCartController : " + e.getMessage());
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
