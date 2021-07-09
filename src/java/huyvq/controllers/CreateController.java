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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


/**
 *
 * @author Quoc Huy
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String SUCCESS = "createSuccess.html";
    private static final String ERROR = "create.jsp";

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
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    log("error at upload file:" + e);
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String itemName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            itemName = item.getName();
                            String realPath = "C:\\Users\\Quoc Huy\\Documents\\NetBeansProjects\\J3.L.P0002\\web\\images\\" + itemName;
                            log("RealPath: " + realPath);
                            File savedFile = new File(realPath);
                            if (!savedFile.exists()) {
                                item.write(savedFile);
                            }
                        } catch (Exception e) {
                            log("error at upload file:" + e);
                        }
                    }
                }

                String bookName = (String) params.get("bookName");
                String description = (String) params.get("description");
                String author = (String) params.get("author");
                String categoryID = (String) params.get("category");
                ProductError error = new ProductError();
                boolean flag = true;
                float price = 0;
                int quantity = 0;

                if (bookName.isEmpty() || (bookName.length() < 2 || bookName.length() > 50)) {
                    error.setBookNameError("Book Name must be [2-50] characters");
                    flag = false;
                }
                try {
                    String temp1 = (String) params.get("price");
                    price = Float.parseFloat(temp1);
                } catch (NumberFormatException e) {
                    error.setPriceError("Price is not correct format");
                    flag = false;
                }
                try {
                    String temp2 = (String) params.get("quantity");
                    quantity = Integer.parseInt(temp2);
                } catch (NumberFormatException e) {
                    error.setQuantityError("Quantity is not correct format");
                    flag = false;
                }
                if (description.isEmpty() || (description.length() < 2 || description.length() > 50)) {
                    error.setDescriptionError("Description must be [2-50]");
                    flag = false;
                }
                if (author.isEmpty() || (author.length() < 2 || author.length() > 50)) {
                    error.setAuthorError("Author must be [2-50]");
                    flag = false;
                }
                if (flag) {
                    ProductDAO dao = new ProductDAO();
                    ProductDTO book = new ProductDTO(bookName, price, quantity, description, author, categoryID, itemName);
                    dao.create(book);
                    url = SUCCESS;
                } else {
                    CategoryDAO daoCate = new CategoryDAO();
                    List<CategoryDTO> list = daoCate.getAll();
                    request.setAttribute("LIST_CATE", list);
                    request.setAttribute("ERROR", error);
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            log("Error at CreatController: " + e.getMessage());
            e.printStackTrace();
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
