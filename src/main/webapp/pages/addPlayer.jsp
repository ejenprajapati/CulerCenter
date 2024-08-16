<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath();
String errorMessage = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Player Registration Form</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/addUpdate.css" />
 
</head>
<body>
<div class="container">
    <div class="title">Player Registration</div>
    <form action="<%=contextPath%>/addPlayer" method="post" enctype="multipart/form-data">
		 <c:if test="${not empty errorMessage}">
		    <div class="error-message">${errorMessage}</div>
		</c:if>
        <div class="player__details">
            <div class="input__box">
                <span class="details">Name</span>
                <input id="player_name" name="player_name" type="text"  placeholder="Full Name" required>
            </div>
            <div class="input__box">
                <span class="details">Jersey Number</span>
                <input id="player_number" type="text" name="player_number" placeholder="0-99" pattern={0-99}{10} required>
            </div>
            <div class="input__box">
                <span class="details">Age</span>
                <input id="age" name="age" type="number" placeholder="Age" required>
            </div>
            <div class="input__box">
                <span class="details">Nationality</span>
                <input id="nationality" name="nationality" type="text"  placeholder="Country" required>
            </div>
            <div class="input__box">
                <span class="details">Position</span>
                <input id="position" name="position" type="text"  placeholder="Position" required>
            </div>
            <div class="input__box">
                <span class="details">Appearances</span>
                <input id="appearance" name="appearance" type="number"  placeholder="Appearences" required>
            </div>
            <div class="input__box">
                <span class="details">Goals</span>
                <input id="goals" name="goals" type="number" placeholder="Goals" required>
            </div>
            <div class="input__box">
                <span class="details">Assists</span>
                <input id="assists" name="assists" type="number"  placeholder="Assists" required>
            </div>
            <div class="input__box">
                <span class="details">Image URL</span>
                <input type="file" accept="image/*" id="image" name="image" >
            </div>
        </div>
        <div class="button">
            <input type="submit" value="Add">
        </div>
    </form>
</div>
</body>
</html>
