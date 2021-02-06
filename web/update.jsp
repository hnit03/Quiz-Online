<%-- 
    Document   : update
    Created on : Feb 6, 2021, 7:36:29 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <h1 class="text-center">Update</h1>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <c:set var="dto" value="${requestScope.QUESTION}"/>
                    <c:if test="${not empty dto}">
                        <form action="update">
                            <div class="mb-3">
                                <label class="form-label">Question Content:</label>
                                <input type="text" class="form-control" 
                                       placeholder="Question Content" 
                                       name="content" value="${dto.content}">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">First Answer:</label>
                                <input type="text" class="form-control" placeholder="First Answer"
                                       name="answer1" value="${dto.answer1}">
                            </div> 
                            <div class="mb-3">
                                <label class="form-label">Second Answer:</label>
                                <input type="text" class="form-control" placeholder="Second Answer"
                                       name="answer2" value="${dto.answer2}">
                            </div> 
                            <div class="mb-3">
                                <label class="form-label">Third Answer:</label>
                                <input type="text" class="form-control" placeholder="Third Answer"
                                       name="answer3" value="${dto.answer3}">
                            </div> 
                            <div class="mb-3">
                                <label class="form-label">Fourth Answer:</label>
                                <input type="text" class="form-control" placeholder="Fourth Answer"
                                       name="answer4" value="${dto.answer4}">
                            </div> 
                            <div class="mb-3">
                                <label class="form-label">Answer Correct:</label>
                                <input type="text" class="form-control" placeholder="Answer Correct"
                                       name="answerCorrect" value="${dto.answerCorrect}">
                            </div> 
                            <input type="submit" class="btn btn-danger form-control mb-1" name="btnAction" value="Update"/>
                            <input type="hidden" name="questionID" value="${dto.questionID}" />
                            <button type="button" class="btn btn-warning form-control">Reset</button>
                        </form>
                    </c:if>
                </div>
                <div class="col-lg-4"></div>
            </div>
        </div>
    </body>
</html>
