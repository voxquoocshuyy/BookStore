<%-- 
    Document   : adminUpdate
    Created on : Jun 16, 2021, 10:27:29 AM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <title>Admin Update</title>
        <c:if test="${sessionScope.LOGIN_USER.role eq 'user'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
    </head>
    <body class="container">
        <a href="AdminViewController"><i class="fa-solid fa-left"></i>Back to continue</a><br>
        <form action="MainController" method="POST">
            <input type="hidden" name="bookID" value="${requestScope.BOOK_UPDATE.bookID}">

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Book Name(*) :</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="bookName"  size="50" value="${requestScope.BOOK_UPDATE.bookName}"/>
                    <font color="red">${requestScope.ERROR.bookNameError}</font>
                </div>             
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Price(*) :</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="price" size="15" value="${requestScope.BOOK_UPDATE.price}"/>
                    <font color="red">${requestScope.ERROR.priceError}</font>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Quantity(*) :</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="quantity" size="15" value="${requestScope.BOOK_UPDATE.quantity}"/>
                    <font color="red">${requestScope.ERROR.quantityError}</font>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Description(*) :</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="description" size="50"  value="${requestScope.BOOK_UPDATE.description}"/>
                    <font color="red">${requestScope.ERROR.descriptionError}</font>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Author(*) :</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="author" size="50" value="${requestScope.BOOK_UPDATE.author}"/>
                    <font color="red">${requestScope.ERROR.authorError}</font>
                </div>
            </div>
                
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Category Name(*) :</label>
                <div class="col-sm-10">
                    <select class="custom-select" name="category">
                        <c:forEach items="${requestScope.LIST_CATE}" var="list">
                            <option value="${list.categoryID}">${list.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>  
            </div>
            <input  class="btn btn-dark" type="submit" name="action" value="Update Book">
        </form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    </body>
</html>
