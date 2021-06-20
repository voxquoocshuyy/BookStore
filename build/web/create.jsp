<%-- 
    Document   : create
    Created on : Jun 16, 2021, 12:07:50 AM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>Create Book</title>
    </head>
    <body class="container">
        <form action="MainController" method="POST">    
            <a href="AdminViewController"><-Back to continue</a>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Book Name(*) :</label> 
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="bookName"  size="50" value="${param.bookName}"/>
                    <font color="red">${requestScope.ERROR.bookNameError}</font>
                </div>             
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Price(*) :</label> 
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="price" size="15" value="${param.price}"/>
                    <font color="red">${requestScope.ERROR.priceError}</font></br>
                </div>             
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Quantity(*) :</label> 
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="quantity" size="15" value="${param.quantity}"/>
                    <font color="red">${requestScope.ERROR.quantityError}</font></br>
                </div>             
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Description(*) :</label> 
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="description" size="50"  value="${param.description}"/>
                    <font color="red">${requestScope.ERROR.descriptionError}</font></br>
                </div>             
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Author(*) :</label> 
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="author" size="50" value="${param.author}"/>
                    <font color="red">${requestScope.ERROR.authorError}</font></br>
                </div>             
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Category Name(*) :</label>
                <div class="col-sm-10">
                    <select class="form-control" name="category">
                        <c:forEach items="${requestScope.LIST_CATE}" var="list">
                            <option value="${list.categoryID}">${list.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>             
            </div>

            <input class="btn btn-dark" type="submit" name="action" value="Create">
            <input class="btn btn-dark" type="reset" value="Reset"> 
        </form>
    </body>
</html>
