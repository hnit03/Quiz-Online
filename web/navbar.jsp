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
            <a class="navbar-brand" href="subject">QuizNOW</a>
            <div class="navbar-nav ml-auto" >
                <c:set var="role" value="${sessionScope.ROLE}"/>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${role == 'Admin'}">
                        <li class="nav-item">
                            <form class="d-flex" action="search">
                                <input class="form-control me-2" 
                                       name="searchValue" 
                                       type="text" 
                                       placeholder="Search" 
                                       aria-label="Search"
                                       value="${param.searchValue}">
                                <select class="form-select form-control" name="cboStatus" style="width: 50%">
                                    <option value="">Status</option>
                                    <option value="0"
                                            <c:if test="${param.cboStatus eq '0'}">
                                                selected
                                            </c:if>
                                            >Active</option>
                                    <option value="1"
                                            <c:if test="${param.cboStatus eq '1'}">
                                                selected
                                            </c:if>
                                            >Deactive</option>
                                </select>
                                <select class="form-select form-control" name="cboCategory" style="width: 50%">
                                    <option value="Subject"
                                            <c:if test="${param.cboCategory eq 'Subject'}">
                                                selected
                                            </c:if>
                                            >Subject</option>
                                    <option value="Question" 
                                            <c:if test="${param.cboCategory eq 'Question'}">
                                                selected
                                            </c:if>
                                                >Question</option>
                                </select>
                                <input class="btn btn-outline-success" type="submit" value="Search"></input>
                            </form>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="subject">Home</a>
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
