<%-- 
    Document   : result
    Created on : Feb 3, 2021, 11:19:03 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <c:set var="numOfCorrect" value="${requestScope.NUM_OF_CORRECT}"/>
                    <c:set var="totalQuestion" value="${sessionScope.NUM_QUESTION}"/>
                    <p>Question: ${numOfCorrect}/${totalQuestion}</p>
                    <h5>Mark: ${requestScope.TOTAL_POINT}</h5>
                    <a href="subject" class="btn btn-primary">Finish To Review</a>
                </div>
                <div class="col-lg-9">
                    <c:set var="result" value="${requestScope.RESULT}"/>
                    <c:if test="${not empty result}">
                        <c:forEach var="question" items="${result}">
                            <c:set var="answerChoosen" value="answer${question.key.questionID}"/>
                            <c:out value="${answerChoosen}"/>
                            <div class="card">
                                <div class="card-body ml-3">
                                    <h5 class="card-title">${question.key.content}</h5>
                                    <input class="form-check-input" type="radio" name="answer" id="flexRadioDefault1" disabled
                                           <c:if test="${param.answerChoosen eq question.key.answer1}">
                                               checked
                                           </c:if>
                                           >
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        ${question.key.answer1}
                                    </label>
                                    <br>

                                    <input class="form-check-input" type="radio" name="answer" id="flexRadioDefault2" disabled
                                           <c:if test="${param.answerChoosen eq question.key.answer2}">
                                               checked
                                           </c:if>
                                           >
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        ${question.key.answer2}
                                    </label>
                                    <br>

                                    <input class="form-check-input" type="radio" name="answer" id="flexRadioDefault3" disabled
                                           <c:if test="${param.answerChoosen eq question.key.answer3}">
                                               checked
                                           </c:if>
                                           >
                                    <label class="form-check-label" for="flexRadioDefault3">
                                        ${question.key.answer3}
                                    </label>
                                    <br>

                                    <input class="form-check-input" type="radio" name="answer" id="flexRadioDefault4" disabled
                                           <c:if test="${param.answerChoosen eq question.key.answer4}">
                                               checked
                                           </c:if>
                                           >
                                    <label class="form-check-label" for="flexRadioDefault4">
                                        ${question.key.answer4}
                                    </label>
                                </div>
                                <div class="alert alert-danger" role="alert">
                                    ${question.value}
                                </div>
                                <div class="alert alert-success" role="alert" style="margin-bottom: 0px">
                                    The correct answer: ${question.key.answerCorrect}
                                </div>
                            </div>

                        </c:forEach>
                    </c:if>

                </div>
            </div>


        </div>
    </body>
</html>
