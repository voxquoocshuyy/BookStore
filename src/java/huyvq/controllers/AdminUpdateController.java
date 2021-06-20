/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.controllers;

import huyvq.tblCategories.CategoryDAO;
import huyvq.tblCategories.CategoryDTO;
import huyvq.tblProducts.ProductDAO;
import huyvq.tblProducts.ProductDTO;
import huyvq.tblProducts.ProductError;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AdminUpdateController", urlPatterns = {"/AdminUpdateController"})
public class AdminUpdateController extends HttpServlet {

    private static final String ERROR = "adminUpdate.jsp";
    private static final String SUCCESS = "AdminViewController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String temp = request.getParameter("bookID");
        int bookID = Integer.parseInt(temp);
        String bookName = request.getParameter("bookName");   
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        String categoryID = request.getParameter("category");
        ProductError error = new ProductError();
        boolean flag = true;
        float price = 0;
        int quantity = 0;
        try {
            if (bookName.isEmpty() || (bookName.length() < 2 || bookName.length() > 50)) {
                error.setBookNameError("Book Name must be [2-50] characters");
                flag = false;
            }
            try {
                String temp1 = request.getParameter("price");
                price = Float.parseFloat(temp1);
            } catch (NumberFormatException e) {
                error.setPriceError("Price is not correct format");  
                flag = false;
            }
            try {
                String temp2 = request.getParameter("quantity");
                quantity = Integer.parseInt(temp2);
            } catch (NumberFormatException e) {
                error.setQuantityError("Quantity is not correct format"); 
                flag = false;
            }
            if(description.isEmpty() || (description.length() < 2 || description.length() > 50)){
                error.setDescriptionError("Description must be [2-50]");
                flag = false;
            }
            if(author.isEmpty() || (author.length() < 2 || author.length() > 50)){
                error.setAuthorError("author must be [2-50]");
                flag = false;
            }
            ProductDAO dao = new ProductDAO();
            if(flag){               
                ProductDTO book = new ProductDTO(bookID, bookName, price, quantity, description, author, categoryID);
                dao.update(book);
                url = SUCCESS;
                request.setAttribute("NOTI", "Update book successfully");
            }else{
                CategoryDAO daoCate = new CategoryDAO();
                List<CategoryDTO> list = daoCate.getAll();
                request.setAttribute("LIST_CATE", list);
                ProductDTO bookBefore = dao.getBookByID(bookID);
                request.setAttribute("BOOK_UPDATE", bookBefore);
                request.setAttribute("ERROR", error);
                url = ERROR;
            }
            
        } catch (Exception e) {
            log("Error at AdminUpdateController: " + e.getMessage());
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
