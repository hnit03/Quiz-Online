<%-- 
    Document   : manage
    Created on : Feb 6, 2021, 3:11:13 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <c:if test="${not empty requestScope.CREATE_SUBJECT_SUCCESS}">
            <script>
                alert(${requestScope.CREATE_SUBJECT_SUCCESS});
            </script>
        </c:if>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <div class="row mb-2">
                <div class="col-lg-12">
                    <a class="btn btn-primary" href="createQuestion"><i class="fas fa-plus"></i> Create</a>
                </div>
            </div>
            <c:set var="questionList" value="${requestScope.LIST_QUESTION}"/>
            <c:if test="${not empty questionList}">
                <c:forEach var="dto" items="${questionList}" varStatus="counter">
                    <div class="row mb-2">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Question ${counter.count}: ${dto.content}</h5>
                                    <p>Answer 1: ${dto.answer1}</p>
                                    <p>Answer 2: ${dto.answer2}</p>
                                    <p>Answer 3: ${dto.answer3}</p>
                                    <p>Answer 4: ${dto.answer4}</p>
                                    <input type="submit" value="Update" class="btn btn-success"/>
                                    <input type="submit" value="Delete" class="btn btn-warning"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </body>
</html>
