<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
String errorMessage = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);

%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/login.css" />
</head>
</head>

<body>
    <div class="container">
        <div class="title">Login</div>
        <form action="<%=contextPath%>/login" method="POST">
        	
        	<c:if test="${not empty errorMessage}">
        		<div class="error-message">${errorMessage}</div>
			</c:if>
            <div class="user__details">
                <div class="input__box">
                    <label for="username" class="details">Username</label>
                    <input type="text" id="username" name="username" placeholder="Enter your username" required>
                </div>
                <div class="input__box">
                    <label for="password" class="details">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                    <span class="toggle-password" onclick="togglePasswordVisibility()">Show</span>
                </div>
            </div>
            <div class="button">
                <input type="submit" value="Login">
            </div>
            <div class="signup">
                <a href=<%=contextPath+StringUtils.PAGE_URL_REGISTER %>>Sign Up</a>
            </div>
            
            
        </form>

    </div>
    
</body>
<script>
        function togglePasswordVisibility() {
            var passwordInput = document.getElementById("password");
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
            } else {
                passwordInput.type = "password";
            }
        }
    </script>
</html>