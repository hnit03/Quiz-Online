<%-- 
    Document   : search
    Created on : Feb 13, 2021, 5:57:51 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <c:set var="question" value="${requestScope.RESULT_QUESTION}"/>
            <c:if test="${not empty question}">
                <h1>Question</h1>
                <c:forEach var="dto" items="${question}" varStatus="counter">
                    <div class="row mb-2">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Question: ${dto.content}</h5>
                                    <p>Answer 1: ${dto.answer1}</p>
                                    <p>Answer 2: ${dto.answer2}</p>
                                    <p>Answer 3: ${dto.answer3}</p>
                                    <p>Answer 4: ${dto.answer4}</p>
                                    <div class="row">
                                        <div class="col-lg-9"></div>
                                        <div class="col-lg-3 text-center">
                                            <c:url var="update" value="get">
                                                <c:param name="questionID" value="${dto.questionID}"/>
                                            </c:url>
                                            <a href="${update}" class="btn btn-success">Update</a>

                                            <c:url var="delete" value="delete">
                                                <c:param name="questionID" value="${dto.questionID}"/>
                                            </c:url>
                                            <a href="${delete}" 
                                               <c:if test="${dto.statusID == 1}">
                                                   class="btn btn-warning disabled"
                                               </c:if>
                                               <c:if test="${dto.statusID != 1}">
                                                   class="btn btn-warning"
                                               </c:if>
                                               />Delete</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <nav aria-label="Page navigation example">
                    <c:set var="pageNo" value="${requestScope.PAGENO}"/>  
                    <c:set var="pageMax" value="${requestScope.PAGEMAX_QUESTION}"/>
                    <c:if test="${pageMax > 1}">
                        <form action="search">
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
                                <input type="hidden" name="pageNo" value="${pageNo}" />
                                <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                <input type="hidden" name="cboCategory" value="${param.cboCategory}" />
                                <input type="hidden" name="cboStatus" value="${param.cboStatus}" />
                            </ul>
                        </form>
                    </c:if>
                </nav>
            </c:if>
            <c:set var="subject" value="${requestScope.RESULT_SUBJECT}"/>
            <c:if test="${not empty subject}">
                <h1>Subject</h1>
                <div class="row">
                    <c:forEach var="dto" items="${subject}" varStatus="counter">
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
                </div>
                <nav aria-label="Page navigation example">
                    <c:set var="pageNo" value="${requestScope.PAGENO}"/>                   
                    <c:set var="pageMax" value="${requestScope.PAGEMAX_SUBJECT}"/>
                    <c:if test="${pageMax > 1}">
                        <form action="search">
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
                                <input type="hidden" name="pageNo" value="${pageNo}" />
                                <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                <input type="hidden" name="cboCategory" value="${param.cboCategory}" />
                                <input type="hidden" name="cboStatus" value="${param.cboStatus}" />
                            </ul>
                        </form>
                    </c:if>
                </nav>
            </c:if>
            <c:if test="${empty subject && empty question}">
                <h2 class="text-center mt-5">Not found</h2>
            </c:if>
        </div>
    </body>
</html>
