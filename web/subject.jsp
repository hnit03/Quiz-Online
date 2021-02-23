<%-- 
    Document   : subject
    Created on : Jan 29, 2021, 5:30:08 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <div class="row">
                <c:set var="subjectList" value="${requestScope.SUBJECT}"/>
                <c:if test="${not empty subjectList}">
                    <c:forEach var="dto" items="${subjectList}">
                        <div class="col-lg-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${dto.subjectName}</h5>
                                    <p class="card-text">Time: ${dto.time}</p>
                                    <form action="takeQuiz" method="POST">
                                        <input type="hidden" name="subjectID" value="${dto.subjectID}" />
                                        <input type="submit" value="Take Quiz" class="btn btn-outline-success"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </body>
</html>
