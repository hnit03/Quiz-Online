<%-- 
    Document   : adminPage
    Created on : Feb 4, 2021, 9:01:55 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <c:if test="${not empty requestScope.CREATE}">
            <script>
                alert(${requestScope.CREATE});
            </script>
        </c:if>
        <div class="container">
            <div class="row">
                <div class="col-lg-3" style="border: 1px solid dimgray">

                    <form action="createSubject" class="my-2 mx-2">
                        <h4 class="text-center">Create New Subject</h2>
                            <label class="form-label">Subject Name</label>
                            <input type="text" name="subjectName" value="${param.subjectName}" placeholder="Subject Name" class="form-control mb-2"/>
                            <label class="form-label">Minute</label>
                            <input type="text" name="minute" value="${param.minute}" placeholder="Minute" class="form-control mb-2" />
                            <label class="form-label">Second</label>
                            <input type="text" name="second" value="${param.second}" placeholder="Second" class="form-control mb-2" />
                            <input type="submit" value="Create"  class="form-control btn btn-success"/>
                    </form>
                </div>
                <div class="col-lg-9">
                    <div class="row">
                        <c:set var="subjectList" value="${requestScope.SUBJECT}"/>
                        <c:if test="${not empty subjectList}">
                            <c:forEach var="dto" items="${subjectList}">
                                <div class="col-lg-4">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title">${dto.subjectName}</h5>
                                            <c:url var="manage" value="manage">
                                                <c:param name="subjectID" value="${dto.subjectID}"/>
                                            </c:url>
                                            <a href="${manage}" class="card-link">Manage</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
