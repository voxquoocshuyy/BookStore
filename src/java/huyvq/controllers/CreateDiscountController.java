/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

import huyvq.tblDiscounts.DiscountDAO;
import huyvq.tblDiscounts.DiscountDTO;
import huyvq.tblDiscounts.DiscountError;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quoc Huy
 */
@WebServlet(name = "CreateDiscountController", urlPatterns = {"/CreateDiscountController"})
public class CreateDiscountController extends HttpServlet {
    private static final String ERROR = "createDiscount.jsp";
    private static final String SUCCESS = "AdminViewController";
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
            String discountCode = request.getParameter("txtDiscountCode");
            int discountPercent =0;
            DiscountError error = new DiscountError();
            boolean flag = true;
            if (discountCode.isEmpty() ||  (discountCode.length() <5 || discountCode.length() > 50)) {
                error.setDiscountCodeError("Discount Code must be [5-10] characters");
                flag = false;
            }
            try {
                String temp = request.getParameter("txtDiscountPercent");
                discountPercent = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                error.setDiscountPercentError("Discount percent is not correct format"); 
                flag = false;
            }
            if (flag) {
                DiscountDTO dto = new DiscountDTO(discountCode, discountPercent);
                DiscountDAO dao = new DiscountDAO();
                dao.create(dto);
                request.setAttribute("NOTI", "Create discount successfully");
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", error);
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at CreateDiscountController: " + e.getMessage());
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
