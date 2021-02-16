<%-- 
    Document   : login
    Created on : Feb 3, 2021, 8:08:24 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container mt-5">
            <h1 class="text-center">Login</h1>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <form action="login" method="POST">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" class="form-control" 
                                   id="email" placeholder="name@example.com" 
                                   name="txtEmail" value="${param.txtEmail}">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password:</label>
                            <input type="password" class="form-control" id="password" placeholder="Password"
                                   name="txtPassword" value="">
                        </div> 
                        <c:set var="error" value="${requestScope.LOGIN_FAILED}"/>
                        <c:if test="${not empty error}">
                            <div class="text-center">
                                <p style="color: red;font-weight: bold">${error}</p>
                            </div>
                        </c:if>
                        <input type="submit" class="btn btn-danger form-control mb-1" name="btnAction" value="Login"/>
                        <button type="button" class="btn btn-warning form-control">Reset</button>
                    </form>
                    <hr>
                    <a href="register" class="btn btn-success form-control">Register</a>
                </div>
                <div class="col-lg-4"></div>
            </div>
        </div>
    </body>
</html>
