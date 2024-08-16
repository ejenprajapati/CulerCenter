<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
String errorMessage = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Registration Form</title>
  <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/register.css" />
</head>

<body>
    <div class="container">
  <div class="title">Registration</div>
  <form action="<%=contextPath%>/registerculer" method="POST" enctype="multipart/form-data"onsubmit="return validateForm()">
    <c:if test="${not empty errorMessage}">
    <div class="error-message">${errorMessage}</div>
</c:if>
    <div class="user__details">
      <div class="input__box">
        <span class="details">Full Name</span>
        <input id="fullName" name="fullName" type="text" placeholder="E.g: Akash Lama" required>
      </div>
      <div class="input__box">
        <span class="details">Username</span>
        <input id="username" name="username" type="text" placeholder="akash_lama12" required>
      </div>
      <div class="input__box">
        <span class="details">Email</span>
        <input id="email" name="email" type="email" placeholder="akashlama@gmail.com" required>
      </div>
      <div class="input__box">
        <span class="details">Phone Number</span>
        <input id="phoneNumber" name="phoneNumber" type="tel" pattern="[0-9]{10}" placeholder="9812345678" required>
      </div>
      
<div class="input__box">
                    <span class="details">Date of Birth</span>
                    <input id="birthday" name="birthday" type="date" required>
                </div>
                <div class="input__box">
                    <span class="details">Profile Picture</span>
                    <input type="file" accept="image/*" id="image" name="image">
                </div>
                <div class="input__box">
        <span class="details">Password</span>
        <input id="password" name="password" type="password" placeholder="********" required>
      </div>
      <div class="input__box">
        <span class="details">Confirm Password</span>
        <input id="retypePassword" name="retypePassword" type="password" placeholder="********" required>
      </div>
    </div>
    <div class="gender__details">
      <label for="gender">Gender:</label> <select id="gender"
						name="gender" required>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
      
    </div>
    <div class="button">
      <input type="submit" value="Register">
    </div>
    <div class="login">
                <a href=<%=contextPath+StringUtils.PAGE_URL_LOGIN %>>Login</a>
            </div>
  </form>
</div>

</body>
<script>
    function validateForm() {
        var password = document.getElementById("password").value;
        var retypePassword = document.getElementById("retypePassword").value;

        if (password !== retypePassword) {
            alert("Passwords do not match");
            return false; // Prevent form submission
        }

        return true; // Allow form submission
    }
</script>

</html>