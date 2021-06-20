<%-- 
    Document   : adminView
    Created on : Jun 15, 2021, 10:36:55 PM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>Admin View</title>
        <c:if test="${sessionScope.LOGIN_USER.role eq 'user'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
    </head>
    <body class="container">
        <h1>Hello Admin ${sessionScope.LOGIN_USER.name}</h1>
        <form action="MainController" method="POST">
            <c:url var="logout" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <a href="${logout}">Logout</a><br>
            <a href="ManageCategoryController">Manage category</a><br>
            <a href="AdminViewCategoryController">Create new book</a><br>
            <a href="createDiscount.jsp">Create discount code</a><br>
            <font color="red"> ${requestScope.NOTI}</font><br>
            Search Name Book <input type="text" name="txtNameBook" value="${param.txtNameBook}"/>
            Search Category Book <input type="text" name="txtCategoryName" value="${param.txtCategoryName}"/>
            Search Price Book <input type="text" name="txtPrice" value="${param.txtPrice}"/>
            <input class="btn btn-dark" type="submit" name="action" value="Search "><br>
        </form>
        <c:if test="${empty requestScope.LIST_BOOK}">
            No Record
        </c:if>
        <c:if test="${not empty requestScope.LIST_BOOK}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Book Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Description</th>
                        <th scope="col">Author</th>
                        <th scope="col">Category Book</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty requestScope.LIST_BOOK}">
                        No Record
                    </c:if>
                    <c:forEach var="dto" items="${requestScope.LIST_BOOK}" varStatus="counter">
                    <form action="MainController" method="POST">
                        <tr>
                            <td scope="row">${counter.count}</td>
                        <input type="hidden" name="bookID" value="${dto.bookID}" />
                        <td>${dto.bookName}</td>
                        <td>${dto.price}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.description}</td>
                        <td>${dto.author}</td>
                        <td>${dto.categoryName}</td>
                        <td>
                            <input class="btn btn-dark" type="submit" name="action" value="Delete">
                            <input class="btn btn-dark" type="submit" name="action" value="Update">
                        </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
