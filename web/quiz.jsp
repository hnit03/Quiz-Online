<%-- 
    Document   : quiz
    Created on : Jan 30, 2021, 9:22:47 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <c:set var="listQuestion" value="${requestScope.QUESTION_LIST}"/>
            <c:set var="sdto" value="${requestScope.SUBJECT}"/>
            <c:if test="${not empty listQuestion && not empty sdto}">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${sdto.subjectName}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${sdto.cdto.categoryName}</h6>
                                <p class="card-text">Time: <span id="time">${sdto.time}</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <form name="exam" action="checkAnswer" method="post">
                            <c:forEach var="question" items="${listQuestion}" varStatus="counter">
                                <div class="card mb-3">
                                    <div class="card-body ml-3">
                                        <h5 class="card-title">Question ${counter.count}: ${question.content}</h5>
                                        <input class="form-check-input" type="radio" name="answer${question.questionID}" id="flexRadioDefault1" value="${question.answer1}">
                                        <label class="form-check-label" for="flexRadioDefault1">
                                            ${question.answer1}
                                        </label>
                                        <br>
                                        <input class="form-check-input" type="radio" name="answer${question.questionID}" id="flexRadioDefault2" value="${question.answer2}">
                                        <label class="form-check-label" for="flexRadioDefault2">
                                            ${question.answer2}
                                        </label>
                                        <br>
                                        <input class="form-check-input" type="radio" name="answer${question.questionID}" id="flexRadioDefault3" value="${question.answer3}">
                                        <label class="form-check-label" for="flexRadioDefault3">
                                            ${question.answer3}
                                        </label>
                                        <br>
                                        <input class="form-check-input" type="radio" name="answer${question.questionID}" id="flexRadioDefault4" value="${question.answer4}">
                                        <label class="form-check-label" for="flexRadioDefault4">
                                            ${question.answer4}
                                        </label>
                                    </div>

                                </div>

                                <input type="hidden" name="questionID" value="${question.questionID}" />

                            </c:forEach>
                            <br>
                            <div class="row">
                                <div class="col-lg-10"></div>
                                <div class="col-lg-2">
                                    <input type="hidden" name="subjectID" value="${requestScope.SUBJECTID}" />
                                    <input type="submit" value="Finish" class="btn btn-success mb-3" style="width: 100%"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty listQuestion && not empty sdto}">
                <div class="text-center">
                    <div class="">
                        <h5 class="">${sdto.subjectName}</h5>
                        <h6 class="mb-2 text-muted">${sdto.cdto.categoryName}</h6>
                        <a href="subject" class="card-link">Back To Home Page</a>
                    </div>
                </div>
            </c:if>
        </div>
        <script>
            var time = document.getElementById("time").innerHTML;
            var min = parseInt(time.split(":", 1));
            var sec = parseInt(time.split(":").pop());
            var examTime = min * 60 + sec;
            function startTimer(duration, display) {
                var timer = duration;
                var minutes, seconds;
                var form = document.forms.exam;

                var t = setInterval(function () {
                    minutes = parseInt(timer / 60, 10);
                    seconds = parseInt(timer % 60, 10);
                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    display.textContent = minutes + ":" + seconds;

                    if (--timer <= 0) {
                        timer = duration;

                        if (!isNaN(t))
                            clearInterval(t);

                        // submit the form...
                        form.submit();
                    }
                }, 1000);
            }

            window.onload = function () {
                var display = document.querySelector('#time');
                startTimer(examTime, display);
            };
        </script>
    </body>
</html>
