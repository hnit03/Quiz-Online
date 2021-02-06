<%-- 
    Document   : createQuestion
    Created on : Feb 6, 2021, 6:49:57 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <h1 class="text-center">Create New Question</h1>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <form action="create" method="POST">
                        <div class="mb-3">
                            <label class="form-label">Question Content:</label>
                            <input type="text" class="form-control" 
                                   placeholder="Question Content" 
                                   name="content" value="${param.content}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">First Answer:</label>
                            <input type="text" class="form-control" placeholder="First Answer"
                                   name="answer1" value="${param.answer1}">
                        </div> 
                        <div class="mb-3">
                            <label class="form-label">Second Answer:</label>
                            <input type="text" class="form-control" placeholder="Second Answer"
                                   name="answer2" value="${param.answer2}">
                        </div> 
                        <div class="mb-3">
                            <label class="form-label">Third Answer:</label>
                            <input type="text" class="form-control" placeholder="Third Answer"
                                   name="answer3" value="${param.answer3}">
                        </div> 
                        <div class="mb-3">
                            <label class="form-label">Fourth Answer:</label>
                            <input type="text" class="form-control" placeholder="Fourth Answer"
                                   name="answer4" value="${param.answer4}">
                        </div> 
                        <div class="mb-3">
                            <label class="form-label">Answer Correct:</label>
                            <input type="text" class="form-control" placeholder="Answer Correct"
                                   name="answerCorrect" value="${param.answerCorrect}">
                        </div> 
                        <input type="submit" class="btn btn-danger form-control mb-1" name="btnAction" value="Create"/>
                        <input type="hidden" name="subjectID" value="${requestScope.SUBJECTID}" />
                        <button type="button" class="btn btn-warning form-control">Reset</button>
                    </form>
                </div>
                <div class="col-lg-4"></div>
            </div>
        </div>
    </body>
</html>
