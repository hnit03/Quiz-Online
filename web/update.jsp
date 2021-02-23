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
                            <c:set var="answerList" value="${dto.answerList}"/>
                            <c:if test="${not empty answerList}">
                                <c:forEach var="answer" items="${answerList}" varStatus="counter">
                                    <div class="mb-3">
                                        <label class="form-label">Answer:</label>
                                        <input type="text" class="form-control" placeholder="Answer"
                                               name="answer" value="${answer.answerContent}"
                                               id="answer${counter.count}"
                                               onchange="addOption(${counter.count})">
                                    </div>
                                </c:forEach>
                                <div id="answerDiv"></div>
                                <span onclick="addAnswer()" class="btn btn-outline-secondary form-control">+</span>
                                <p id="number" style="display: none;">5</p>
                                <script>
                                    function addAnswer() {
                                        var id = document.getElementById("number").innerHTML;
                                        var number = parseInt(id);
                                        var add = document.getElementById("answerDiv");
                                        var div = document.createElement("DIV");
                                        div.className = "mb-3";
                                        div.innerHTML = "<label class=\"form-label\">Answer</label><input type=\"text\" class=\"form-control\" placeholder=\"Answer\"" +
                                                "name=\"answer\" value=\"\"" +
                                                "id=\"answer" + number + "\" onchange=\"addOption("
                                                + number
                                                + ")\"" +
                                                "required>";
                                        add.appendChild(div);
                                        document.getElementById("number").innerHTML = number + 1;
                                    }
                                </script>
                                <div class="mb-3">
                                    <label class="form-label">Answer Correct:</label>
                                    <select class="form-control" name="answerCorrect" id="select">
                                        <c:forEach var="answer" items="${answerList}" varStatus="counter">
                                            <option value="${answer.answerID}" id="${counter.count}"
                                                    <c:if test="${answer.type}">
                                                        selected
                                                    </c:if>
                                                    >${answer.answerContent}</option>
                                        </c:forEach>
                                    </select>
                                </div> 
                                <script>
                                    function addOption(id) {
                                        console.log(id);
                                        var text = document.getElementById("answer" + id).value;
                                        var x = document.getElementById("select");
                                        var c = document.createElement("option");
                                        c.text = text;
                                        c.value = text;
                                        x.options.remove(id - 1);
                                        x.options.add(c, id - 1);

                                    }
                                </script>
                            </c:if>

                            <div class="mb-3">
                                <label class="form-label">Status:</label>
                                <select name="cboStatus" class="form-control">
                                    <option value="0" 
                                            <c:if test="${dto.statusID == 0}">
                                                selected
                                            </c:if>
                                            >Active</option>
                                    <option value="1"
                                            <c:if test="${dto.statusID == 1}">
                                                selected
                                            </c:if>
                                            >Deactive</option>
                                </select>
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
