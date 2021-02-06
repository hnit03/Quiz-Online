<%-- 
    Document   : navbar
    Created on : Jan 29, 2021, 5:30:28 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nav Bar</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg mb20" style="background: white;">
            <a class="navbar-brand" href="StartUpServlet">QuizNOW</a>
            <div class="navbar-nav ml-auto" >
                <c:set var="role" value="${sessionScope.ROLE}"/>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="StartUpServlet">Home</a>
                    </li>

                    <c:if test="${empty sessionScope.FULLNAME}">
                        <li class="nav-item">
                            <a class="nav-link" href="signin">Login</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.FULLNAME}">
                        <c:if test="${role != 'Admin'}">
                            <li class="nav-item">
                                <a class="nav-link" href="history">
                                    <i class="fas fa-history"></i>
                                </a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <span class="nav-link">Welcome, ${sessionScope.FULLNAME}</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Logout</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </body>
</html>
