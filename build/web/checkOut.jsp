<%-- 
    Document   : checkOut
    Created on : Jun 18, 2021, 9:49:24 AM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>Check Out</title>
    </head>
    <body class="container">
        <form action="MainController" method="POST">
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
                    Discount code: <input type="text" name="txtDiscount">
                    <input class="btn btn-dark" type="submit" name="action" value="Use">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Book Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="sum" value="0"></c:set>
                            <c:forEach items="${sessionScope.CART.getCart().values()}" var="dto" varStatus="counter">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                <input type="hidden" name="txtBookID" value="${dto.bookID}"/>
                                <td><input class="form-control" type="text" name="txtBookName" value="${dto.bookName}" readonly/></td>
                                <td><input class="form-control" type="text" name="txtPrice" value="${dto.price}" readonly /></td>
                                <td><input class="form-control" type="text" name="txtQuantity" value="${dto.quantity}" readonly/></td>
                                <td>${dto.price*dto.quantity}</td>
                                    <c:set var="sum" value="${sum + (dto.price*dto.quantity)}"/>  
                                </tr>
                            </form>
                        </c:forEach>

                        <c:if test="${empty requestScope.TOTAL_AFTER}">
                            <tr>
                                <th colspan="4" style="text-align: center">Total Order</th>
                                <th>${sum}
                                    <input type="hidden" name="txtTotal" value="${sum}"/>
                                </th>
                            </tr> 
                        </c:if>
                        <c:if test="${not empty requestScope.TOTAL_AFTER}">
                            <tr>
                                <th colspan="4">Total Order After Use Discount</th>
                                <th>${requestScope.TOTAL_AFTER}
                                    <input type="hidden" name="txtTotal" value="${requestScope.TOTAL_AFTER}"/>
                                </th>
                            </tr>
                        </c:if>

                        </tbody>
                    </table>
                </c:if>
            </c:if>
                    <input class="btn btn-dark" type="submit" name="action" value="Paid"><br><br>
            <c:set var="USD" value="${paypal / 23000}"/>  
            <script src="https://www.paypal.com/sdk/js?client-id=AcAFM4JjI-FeKIwzFLUzCUJRMzbkEd5zxYpA_zc6NHiWlpOe8QQtp6Eo8QUlJ0VutXnhGLV60JNtF8N9"></script>
            <div id="paypal-button-container" style="width: 50px"></div>
            <script>
                paypal.Buttons({
                    // Set up the transaction
                    createOrder: function (data, actions) {
                        return actions.order.create({
                            purchase_units: [{
                                    amount: {
                                        value: '<fmt:formatNumber value="${USD}" maxFractionDigits="2"/>'.replace(',','.')
                                    }
                                }]
                        });
                    },
                    // Finalize the transaction
                    onApprove: function (data, actions) {
                        return actions.order.capture().then(function (details) {
                            // Show a success message to the buyer                        
                            var url = "CheckOutController";
                            var params = '?btAction=Check out&txtTotal=${paypal}' ;
                            location.replace(url + params);
                        });
                    },           
                    style: {
                        size: 'small',
                        color: 'blue',
                        shape: 'pill',
                        label: 'pay',
                        height: 40
                    }
                }).render('#paypal-button-container');
                //This function displays Smart Payment Buttons on your web page.
            </script>
        </form>
    </body>
</html>
