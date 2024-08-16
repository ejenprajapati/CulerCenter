<%@page import="model.CulerModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- Boxicons -->
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<!-- My CSS -->
	<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/adminDashboard.css" />
         <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/header.css" />

	<title>AdminHub</title>
</head>
<body>
<jsp:include page="header.jsp" />


	<!-- CONTENT -->
	<section id="content">
		
		<main>
			<div class="head-title">
				<div class="left">
					<h1>Dashboard</h1>
					
				</div>
				
			</div>

			<ul class="box-info">
				<li>
					<i class='bx bxs-calendar-check' ></i>
					<span class="text">
						<h3>${requestScope.playerCount}</h3>
						<p>Total Number of Player</p>
					</span>
				</li>
				<li>
					<i class='bx bxs-group' ></i>
					<span class="text">
						<h3>${requestScope.userCount}</h3>
						<p>Total Number of Users</p>
					</span>
				</li>
				<li>
					<i class='bx bxs-dollar-circle' ></i>
					<span class="text">
						<h3>${requestScope.interactions}</h3>
						<p>Total Interactions on Website</p>
					</span>
				</li>
			</ul>


		
		</main>
		<!-- MAIN -->
	</section>
	<!-- CONTENT -->
	<div class="player-info">
		<div class="users">

			<c:if test="${empty culerLists}">
				<p>No students found.</p>
			</c:if>

			<c:if test="${not empty culerLists}">
				<c:forEach var="culer" items="${culerLists}">
					<div class="card">
						<img src="<%=contextPath%>/resource/images/user/${culer.imageUrlFromPart}"
							class="card-img-top" alt="...">
						<div class="card-body">
							<h4 class="card-title">${culer.full_Name}
								</h4>
							<h5 class="card-text">${culer.email}</h5>
						</div>
						


								
						<%-- <form method="GET"
							action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_PLAYER%>">
							<input type="hidden" name="<%=StringUtils.UPDATE_ID %>" value="${player.player_number}" />
							<button type="submit">Update</button>
						</form>
						<form id="deleteForm-${player.player_number}" method="POST"
							action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_PLAYER %>">
							<input type="hidden" name="<%=StringUtils.DELETE_ID %>" value="${player.player_number}" />
							<button 
								onclick="confirmDelete('${player.player_number}')">Delete</button>
						</form> --%>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>

	<script src="script.js"></script>
</body>
</html>