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
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                    <c:set var="dto" value="${sessionScope.RESULT}"/>
                    
                    <table class="table table-info">
                        <thead>
                            <tr>
                                <th scope="col">State</th>
                                <th scope="col">Marks </th>
                                <th scope="col">Grade</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${dto.createDate}</td>
                                <td>${dto.numOfCorrect}/${dto.totalQuestion}</td>
                                <td>${dto.totalPoint}</td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="text-center">
                        <h2>Highest grade: ${dto.totalPoint}</h2>
                        <a href="subject" class="btn btn-primary">Finish To Review</a>
                    </div>
                </div>
                <div class="col-lg-2"></div>
            </div>


        </div>
    </body>
</html>
