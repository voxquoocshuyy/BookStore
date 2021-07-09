<%-- 
    Document   : userView
    Created on : Jun 15, 2021, 8:56:33 PM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>User View</title>
    </head>
    <body class="container">
        <c:if test="${not empty sessionScope.LOGIN_USER}">
            <h1>Hello User ${sessionScope.LOGIN_USER.name}</h1>
            <form action="MainController" method="POST">
                <c:url var="logout" value="MainController">
                    <c:param name="action" value="Logout"></c:param>
                </c:url>
                <a href="${logout}">Logout</a><br>
            </form>
        </c:if>    
        <form action="MainController" method="POST">
            <a href="viewCart.jsp">View cart</a><br>
            <c:if test="${not empty sessionScope.LOGIN_USER}">
                <a href="GetAllHistoryController">Shopping history </a><br>
            </c:if>
            <font color="red"> ${requestScope.NOTI}</font><br>
            Search Name Book <input class="form-control"  type="text" name="nameBook" value="${param.nameBook}"/>
            Search Category Book <input class="form-control"  type="text" name="categoryName" value="${param.categoryName}"/>
            Search Price Book <input class="form-control"  type="text" name="price" value="${param.price}"/>
            <input class="btn btn-dark" type="submit" name="action" value="Search">
        </form>
            <c:if test="${empty requestScope.LIST_BOOK}">
            <font color="red">No record</font><br>
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
                        <input type="hidden" name="txtBookID" value="${dto.bookID}" />
                        <td><input class="form-control" name="txtBookName" value="${dto.bookName}" readonly/></td>
                        <td><input class="form-control" name="txtPrice" value="${dto.price}" readonly/></td>
                        <td><input class="form-control" name="txtQuantity" value="${dto.quantity}"readonly /></td>
                        <td><input class="form-control" value="${dto.description}" readonly </td>
                        <td><input class="form-control" value="${dto.author}" readonly </td>
                        <td><input class="form-control" value="${dto.categoryName}" readonly </td>
                        <td><input class="btn btn-dark" type="submit" name="action" value="Add to cart"</td>
                        </tr>
                    </form> 
                </c:forEach>
                    
            </tbody>
        </table>
    </c:if>
</body>
</html>
