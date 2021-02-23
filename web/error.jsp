<%-- 
    Document   : error
    Created on : Feb 17, 2021, 8:02:09 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
         <jsp:include page="navbar.jsp"/>
        <div class="container">
            <h1 class="text-center">OPPS, SOME THING WENT WRONG</h1>
            <p class="text-center">Sorry, the page you were looking for does not exist!</p>
            <div class="text-center">
                <a href="subject" class="btn btn-danger">Home Page</a>
            </div>
        </div>
    </body>
</html>
