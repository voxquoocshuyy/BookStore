<%-- 
    Document   : viewCart
    Created on : Jun 17, 2021, 3:21:23 PM
    Author     : Quoc Huy
--%>

<%@page import="huyvq.tblProducts.ProductDTO"%>
<%@page import="huyvq.cart.CartObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>View Cart</title>
    </head>
    <body class="container">
        <c:if test="${not empty sessionScope.LOGIN_USER}">
            <h1>Welcome ${sessionScope.LOGIN_USER.name}</h1> 
        </c:if> 
        <a href="UserViewController"><- Back To Home To Continue</a><br>
        <font color="red"> ${requestScope.NOTI} </font><br>
        <c:if test="${empty sessionScope.CART}">
            <font color="red">There are no books in cart</font><br>
        </c:if>
        <c:if test="${not empty sessionScope.CART}">
            <c:if test="${empty sessionScope.CART.getCart()}">
                <font color="red">There are no books in cart</font><br>
            </c:if>
            <c:if test="${not empty sessionScope.CART.getCart()}">
                <form action="MainController" method="POST">
                    <table class="table table-bordered">
                        <thead>
                        <input class="btn btn-dark" type="submit" name="action" value="Check out"><br>
                        
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Book Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:set var="sum" value="0"></c:set>
                            <c:forEach items="${sessionScope.CART.getCart().values()}" var="dto" varStatus="counter">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td scope="row">${counter.count}</td>
                                <input type="hidden" name="txtBookID" value="${dto.bookID}"/>
                                <td><input class="form-control" type="text" name="txtBookName" value="${dto.bookName}" readonly/></td>
                                <td><input class="form-control" type="text" name="txtPrice" value="${dto.price}" readonly /></td>
                                <td><input class="form-control" type="text" name="txtQuantity" value="${dto.quantity}"/></td>
                                <td>${dto.price*dto.quantity}</td>
                                    <c:set var="sum" value="${sum + (dto.price*dto.quantity)}"></c:set>
                                <input type="hidden" name="txtTotal" value="${sum}"/>
                                <th>
                                    <input class="btn btn-dark" type="submit" name="action" value="Remove">
                                    <input class="btn btn-dark" type="submit" name="action" value="Update cart">
                                </th>
                                </tr>
                            </form>
                        </c:forEach>
                        <tr>
                            <th colspan="4">Total Order</th>
                            <th colspan="2">${sum}</th>          

                        </tr>   
                        
                        </tbody>
                    </table>
                </form>
            </c:if>
        </c:if>
    </body>
</html>
