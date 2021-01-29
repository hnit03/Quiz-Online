<%-- 
    Document   : register
    Created on : Jan 28, 2021, 10:40:08 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Register</h1>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <form action="registerAcc" method="POST">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" class="form-control" 
                                   id="email" placeholder="name@example.com" required
                                   name="txtEmail" value="${param.txtEmail}">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password:</label>
                            <input type="password" class="form-control" id="password" placeholder="Password" required
                                   name="txtPassword" value="${param.txtPassword}">
                        </div> 
                        <div class="mb-3">
                            <label for="Password" class="form-label">Password:</label>
                            <input type="password" class="form-control" 
                                   id="email" placeholder="Password" required
                                   name="txtConfirmPassword" value="${param.txtConfirmPassword}">
                        </div>
                        <div class="mb-3">
                            <label for="fullname" class="form-label">Fullname:</label>
                            <input type="text" class="form-control" 
                                   id="email" placeholder="Fullname" required
                                   name="txtFullname" value="${param.txtFullname}">
                        </div>
                        <input type="submit" class="btn btn-danger form-control mb-1" name="btnAction" value="Register"/>
                        <button type="button" class="btn btn-warning form-control">Reset</button>
                    </form>
                    <hr>
                    <a href="signin" class="btn btn-success form-control">Login</a>
                </div>
                <div class="col-lg-4"></div>
            </div>
        </div>
    </body>
</html>
