<%@page import="model.CulerModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Players</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/header.css" />
	<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/rateplayer.css" />
</head>
<body>
<jsp:include page="header.jsp" />
<h2>Welcome to our Home Page!</h2>
<div class = "main-container">
	<div class = "add-container">
	<form method="GET" action="<%=contextPath%>/search">
	

	<input id="search" name="search" type="text" placeholder="Search" required>
							<button type="submit">Search</button>
		
						</form>
	</div>

	<div class="player-info">
		<div class="users">

			<c:if test="${empty playerLists}">
				<p>No players found.</p>
			</c:if>

			<c:if test="${not empty playerLists}">
				<c:forEach var="player" items="${playerLists}">
					<div class="card">
						<img src="<%=contextPath%>/resource/images/players/${player.imageUrlFromPart}"
							class="card-img-top" alt="...">
						<div class="card-body">
							<h4 class="card-title">${player.player_name}
								</h4>
							<h5 class="card-text">${player.position}</h5>
						</div>
						<form id="voteForm" method="POST" action="<%=contextPath%>/rating">
						<input type="hidden" name="playerId" value="${player.player_number}"/>
						    <div class="button-container">
						        <select id="voteInput" name="voteInput">
						            <option value="1">1</option>
						            <option value="2">2</option>
						            <option value="3">3</option>
						            <option value="4">4</option>
						            <option value="5">5</option>
						            <option value="6">6</option>
						            <option value="7">7</option>
						            <option value="8">8</option>
						            <option value="9">9</option>
						            <option value="10">10</option>
						        </select>
						        <button class="vote-button" type="submit">Vote</button>
						    </div>
						</form>


								
						
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
	</div>
</body>
<script>
function confirmDelete(player_number) {
	if (confirm("Are you sure you want to delete this player: " + player_number
			+ "?")) {
		document.getElementById("deleteForm-" + player_number).submit();
	}
	 

}
</script>
</html>