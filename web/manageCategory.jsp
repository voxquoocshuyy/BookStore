<%-- 
    Document   : manageCategory
    Created on : Jun 16, 2021, 3:11:25 PM
    Author     : Quoc Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
        <title>Manage Category</title>
        <c:if test="${sessionScope.LOGIN_USER.role eq 'user'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
    </head>
    <body class="container">
        <h1>Hello Admin ${sessionScope.LOGIN_USER.name}</h1>
        <a href="AdminViewController"><-Back to continue</a><br>
        <a href="createCategory.jsp">Create new category book</a><br>
        <font color="red"> ${requestScope.NOTI}</font><br>
        <c:if test="${empty requestScope.LIST_CATE}">
            No Record
        </c:if>
        <c:if test="${not empty requestScope.LIST_CATE}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Category ID</th>
                        <th scope="col">Category Name</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <font color="red">${requestScope.ERROR.cateNameError}</font>
                    <c:forEach var="dto" items="${requestScope.LIST_CATE}" varStatus="counter">
                    <form action="ActionCategoryController" method="POST">                 
                        <tr>
                            <td  scope="row">${counter.count}</td>
                            <td><input class="form-control" type="text" name="categoryID" value="${dto.categoryID}" readonly /></td>
                            <td><input class="form-control" type="text" name="categoryName" value="${dto.categoryName}" />
                            </td>
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
