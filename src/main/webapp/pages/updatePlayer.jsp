<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Player Update Form</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/addUpdate.css" />
 
</head>
<body>
<div class="container">
    <div class="title">Player Details Update</div>
    <form action="<%=contextPath%>/modify" method="POST" enctype="multipart/form-data">
        <div class="player__details">
            <div class="input__box">
                <span class="details">Name</span>
                <input id="player_name" name="player_name" type="text" value="${player.getPlayer_name()}" placeholder="Full Name" required>
            </div>
            <div class="input__box">
                <span class="details">Jersey Number</span>
                <input id="player_number" type="text" name="player_number" value="${player.getPlayer_number()}"readonly>
            </div>
            <div class="input__box">
                <span class="details">Age</span>
                <input id="age" name="age" type="number" value="${player.getAge()}" placeholder="Age" required>
            </div>
            <div class="input__box">
                <span class="details">Nationality</span>
                <input id="nationality" name="nationality" type="text" value="${player.getNationality()}" placeholder="Country" required>
            </div>
            <div class="input__box">
                <span class="details">Position</span>
                <input id="position" name="position" type="text" value="${player.getPosition()}" placeholder="Position" required>
            </div>
            <div class="input__box">
                <span class="details">Appearances</span>
                <input id="appearance" name="appearance" type="number" value="${player.getAppearance()}" placeholder="Appearences" required>
            </div>
            <div class="input__box">
                <span class="details">Goals</span>
                <input id="goals" name="goals" type="number" value="${player.getGoals()}" placeholder="Goals" required>
            </div>
            <div class="input__box">
                <span class="details">Assists</span>
                <input id="assists" name="assists" type="number" value="${player.getAssist()}"  placeholder="Assists" required>
            </div>
            <div class="input__box">
                <span class="details">Image URL</span>
                <input type="file" accept="image/*" id="image" name="image" value="${player.getImageUrlFromPart()}" >
            </div>
        </div>
        <div class="button">
            <input type="submit" value="Update">
        </div>
    </form>
</div>
</body>
</html>
