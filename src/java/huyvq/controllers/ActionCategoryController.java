/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

import huyvq.tblCategories.CategoryDAO;
import huyvq.tblCategories.CategoryDTO;
import huyvq.tblCategories.CategoryError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quoc Huy
 */
@WebServlet(name = "ActionCategoryController", urlPatterns = {"/ActionCategoryController"})
public class ActionCategoryController extends HttpServlet {

    private static final String SUCCESS = "ManageCategoryController";

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
            CategoryDAO dao = new CategoryDAO();
            String cateID = request.getParameter("categoryID");
            String cateName = request.getParameter("categoryName");
            CategoryDTO cate = new CategoryDTO(cateID, cateName);
            String button = request.getParameter("action");
            CategoryError error = new CategoryError();
            boolean flag = true;
            log(button);
            if ("Delete".equals(button)) {
                dao.deleteCate(cateID);
                request.setAttribute("NOTI", "Delete category successfully");
                url = SUCCESS;
            }
            if ("Update".equals(button)) {
                if (cateName.isEmpty() || (cateName.length() > 50 || cateName.length() < 5)) {
                    error.setCateNameError("Category Name must be [5-50] characters");
                    flag = false;
                }
                if (flag) {
                    dao.update(cate);
                    request.setAttribute("NOTI", "Update category successfully");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", error);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at ActionCategoryController: " + e.getMessage());
            if (e.getMessage().contains("REFERENCE")) {
                request.setAttribute("NOTI", "Delete category fail");
            }
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
