<%-- 
    Document   : history.jsp
    Created on : Jun 20, 2021, 9:19:18 AM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>Shopping History</title>
    </head >
    <body class="container">
        <c:if test="${not empty sessionScope.LOGIN_USER}">
            <h1>Hello User ${sessionScope.LOGIN_USER.name}</h1>
        </c:if>             
            <a href="UserViewController"><- Back To Home To Continue</a><br>
        <form action="MainController" method="POST">
            
            Search Date History <input type="date" name="txtDateOrder" value="${param.txtDateOrder}"/>
            <input class="btn btn-dark" type="submit" name="action" value="Search history">
            <font color="red">${requestScope.NOTI}</font><br>
            <c:if test="${empty requestScope.HISTORY}">
                <font color="red">No record</font><br>
            </c:if>
            <c:if test="${not empty requestScope.HISTORY}">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Book Name</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">Date order</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.HISTORY}" varStatus="counter">              
                            <tr>
                                <td scope="row">${counter.count}</td>
                                <td>${dto.bookName}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.total}</td>
                                <td>${dto.dateOrder}</td>
                            </tr>
                        </c:forEach>        
                    </tbody>
                </table>
            </c:if>
        </form>
    </body>
</html>
