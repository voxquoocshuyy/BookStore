/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

import huyvq.tblDiscounts.DiscountDAO;
import huyvq.tblDiscounts.DiscountDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quoc Huy
 */
@WebServlet(name = "UseDiscountController", urlPatterns = {"/UseDiscountController"})
public class UseDiscountController extends HttpServlet {

    private static final String SUCCESS = "checkOut.jsp";

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
        String url = SUCCESS;
        try {
            String discountCode = request.getParameter("txtDiscount");
            DiscountDAO dao = new DiscountDAO();
            if (discountCode != null) {
                DiscountDTO dto = dao.getDiscount(discountCode);
                if (dto != null) {
                    String discountStatus = dto.getDiscountStatus();
                    if ("active".equals(discountStatus)) {
                        int discountPercent = dto.getDiscountPercent();
                        String temp = request.getParameter("txtTotal");
                        float total = Float.parseFloat(temp);
                        float totalAfter = ((total * discountPercent) / 100);
                        request.setAttribute("TOTAL_AFTER", totalAfter);
                        request.setAttribute("NOTI", "Use discount successfully");
                        dao.updateDiscount(dto);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("NOTI", "Discount code used");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("NOTI", "Discount code not exist");
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at UseDiscountController: " + e.getMessage());
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
