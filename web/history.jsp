<%-- 
    Document   : history
    Created on : Feb 10, 2021, 9:03:49 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <c:set var="listHisory" value="${requestScope.HISTORY}"/>
            <c:if test="${not empty listHisory}">
                <table class="table" >
                    <thead>
                        <tr>
                    <form action="searchHistory">
                        <th colspan="3">
                            <input type="text" name="searchValue" value="${param.searchValue}" class="form-control"/>
                        </th>
                        <th>
                            <input type="submit" value="Search" class="btn btn-success"/>
                        </th>
                    </form>
                    </tr>
                    <tr>
                        <th scope="col">Subject Name</th>
                        <th scope="col">Number Of Correct Answer</th>
                        <th scope="col">Total Question</th>
                        <th scope="col">Total Point</th>
                        <th scope="col">Create Date</th>
                    </tr>



                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${listHisory}">
                            <tr>
                                <th>${dto.sdto.subjectName}</th>
                                <td>${dto.numOfCorrect}</td>
                                <td>${dto.totalQuestion}</td>
                                <td>${dto.totalPoint}</td>
                                <td>${dto.createDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty listHisory}">
                <h2 class="text-center"> No history</h2>
            </c:if>
        </div>
    </body>
</html>
