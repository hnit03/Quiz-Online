<%-- 
    Document   : searchHistory
    Created on : Feb 16, 2021, 3:01:18 PM
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
            <c:set var="listHisory" value="${requestScope.HISTORY_RESULT}"/>

            <table class="table" style="height: 980px;">
                <thead>
                    <tr>
                <form action="searchHistory">
                    <th colspan="3">
                        <input type="text" name="searchValue" value="${param.searchValue}" class="form-control"/>
                    </th>
                    <th>
                        <input type="submit" value="Search" class="btn btn-success form-control"/>
                    </th>
                </form>
                </tr>
                <tr>
                    <th scope="col">Subject Name</th>
                    <th scope="col">Number Of Correct Answer</th>
                    <th scope="col">Total Point</th>
                    <th scope="col">Create Date</th>
                </tr>
                </thead>
                <tbody >
                    <c:if test="${not empty listHisory}">
                        <c:forEach var="dto" items="${listHisory}">
                            <tr>
                                <th>${dto.sdto.subjectName}</th>
                                <td>${dto.numOfCorrect}</td>
                                <td>${dto.totalPoint}</td>
                                <td>${dto.createDate}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty listHisory}">
                        <tr class="text-center">
                            <th colspan="4" style="font-size:  36px;">Not found</th>
                        </tr>
                    </c:if>
                </tbody>

            </table>
            <c:if test="${not empty listHisory}">
                <nav aria-label="Page navigation example">
                    <c:set var="pageNo" value="${requestScope.PAGENO}"/>  
                    <c:set var="pageMax" value="${requestScope.PAGEMAX}"/>
                    <c:if test="${pageMax > 1}">
                        <form action="searchHistory">
                            <ul class="pagination justify-content-end">
                                <c:if test="${pageNo <= 1}">
                                    <li class="page-item disabled">
                                        <input type="submit" value="Previous" name="page" class="page-link"/>
                                    </li>   
                                </c:if>
                                <c:if test="${pageNo > 1}">
                                    <li class="page-item">
                                        <input type="submit" value="Previous" name="page" class="page-link"/>
                                    </li>   
                                </c:if>
                                <c:if test="${pageNo < pageMax}">
                                    <li class="page-item">
                                        <input type="submit" value="Next" name="page" class="page-link"/>
                                    </li>   
                                </c:if>   
                                <c:if test="${pageNo == pageMax}">
                                    <li class="page-item disabled">
                                        <input type="submit" value="Next" name="page" class="page-link"/>
                                    </li>   
                                </c:if>
                                <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                <input type="hidden" name="cboCategory" value="${param.cboCategory}" />
                                <input type="hidden" name="pageNo" value="${pageNo}" />
                            </ul>
                        </form>
                    </c:if>
                </nav>
            </c:if>
        </div>
    </body>
</html>
