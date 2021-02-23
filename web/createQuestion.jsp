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
                                   name="content" value="${param.content}"
                                   required>
                        </div>
                        <c:forEach begin="0" end="3" varStatus="counter">
                            <div class="mb-3">
                                <label class="form-label">Answer:</label>
                                <input type="text" class="form-control" placeholder="Answer"
                                       name="answer" value="${param.answer}"
                                       id="answer${counter.count}"
                                       onchange="addOption(${counter.count})"
                                       required>
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
                            <select name="cboAnswerCorrect" class="form-control" id="select">
                            </select>
                            <script>
                                function addOption(id) {
                                    var text = document.getElementById("answer" + id).value;
                                    console.log(text);
                                    var x = document.getElementById("select");
                                    var size = x.options.length;
                                    var c = document.createElement("option");
                                    c.text = text;
                                    c.value = text;
                                    x.options.add(c, size - 1);
                                    var length = parseInt(document.getElementById("number")); 
                                    if (x.options.length > length) {
                                        x.options.remove(id - 1);
                                        x.options.add(c, id - 1);
                                    }
                                }
                            </script>

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
