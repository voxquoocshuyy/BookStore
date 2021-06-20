<%-- 
    Document   : createCategory
    Created on : Jun 16, 2021, 4:02:40 PM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>Create category</title>
        <c:url var="logout" value="MainController">
            <c:param name="action" value="Logout"></c:param>
        </c:url>
    </head>
    <body class="container">
        <h1>Hello Admin ${sessionScope.LOGIN_USER.name}</h1>
        <a href="ManageCategoryController"><-Back to continue</a><br>
        <form action="MainController" method="POST">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Category ID(*) :</label> 
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="categoryID" value="${param.categoryID}">
                    <font color="red">${requestScope.ERROR.cateIDError}</font>
                </div>             
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Category Name(*) :</label> 
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="categoryName" value="${param.categoryName}">
                    <font color="red">${requestScope.ERROR.cateNameError}</font>
                </div>             
            </div>
            <input class="btn btn-dark" type="submit" name="action" value="Create category">
        </form>
    </body>
</html>
