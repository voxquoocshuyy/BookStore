/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

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
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    private static final String ERROR = "error.html";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String USERVIEW = "UserViewController";
    private static final String ADMINVIEW = "AdminViewController";
    private static final String ADMINDELETE = "AdminDeleteController";
    private static final String ADMINACTION = "AdminActionController";
    private static final String ADMINUPDATE = "AdminUpdateController";
    private static final String ADMINCREATE = "CreateController";
    private static final String ADMINCREATECATEGORY = "CreateCategoryController";
    private static final String ADDTOCART = "AddToCartServlet";
    private static final String REMOVEINCART = "RemoveInCartController";
    private static final String UPDATEINCART = "UpdateInCartController";
    private static final String CHECKOUT = "checkOut.jsp";
    private static final String PAID = "CheckOutController";
    private static final String CREATEDISCOUNT = "CreateDiscountController";
    private static final String DISCOUNT = "UseDiscountController";
    private static final String HISTORY = "HistoryController";
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
        String action = request.getParameter("action");
        log(action);
        try {
            if("Login".equals(action)){
                url = LOGIN;
            }
            if("Logout".equals(action)){
                url = LOGOUT;
            }
            if("Search".equals(action)){
                url = USERVIEW;
            }
            if("Search ".equals(action)){
                url = ADMINVIEW;
            }
            if("Delete".equals(action)){
                url = ADMINDELETE;
            }
            if("Update".equals(action)){
                url = ADMINACTION;
            }
            if("Create".equals(action)){
                url = ADMINCREATE;
            }
            if("Update Book".equals(action)){
                url = ADMINUPDATE;
            }
            if("Create category".equals(action)){
                url = ADMINCREATECATEGORY;
            }
            if("Add to cart".equals(action)){
                url = ADDTOCART;
            }
            if("Remove".equals(action)){
                url = REMOVEINCART;
            }
            if("Update cart".equals(action)){
                url = UPDATEINCART;
            }
            if("Check out".equals(action)){
                url = CHECKOUT;
            }
            if("Paid".equals(action)){
                url = PAID;
            }
            if("Create discount".equals(action)){
                url = CREATEDISCOUNT;
            }
            if("Use".equals(action)){
                url = DISCOUNT;
            }
            if("Search history".equals(action)){
                url = HISTORY;
            }
            
        } catch (Exception e) {
            log("Error at MainController: " + e.getMessage());
        }finally{
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
