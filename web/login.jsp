<%-- 
    Document   : login
    Created on : May 30, 2021, 9:15:47 AM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>Login</title>
    </head>
    
    <body class="container">
        <font color="red">${requestScope.NOTI}</font>
        <form action="MainController" method="POST">
            <div style="margin-top: 50px; margin-left: 400px" >
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">User ID</label> 
                <div class="col-sm-10">
                    <input type="text" name="userID"/><br>
                </div>             
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password</label> 
                <div class="col-sm-10">
                    <input type="password" name="password"/><br>
                </div>             
            </div>
            <input class="btn btn-dark" type="submit" name="action" value="Login"/>
            <input class="btn btn-dark" type="reset" value="Reset"/><br>
            </div>  
        </form>
    </body>
</html>
