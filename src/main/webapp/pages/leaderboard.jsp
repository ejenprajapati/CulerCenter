<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Leaderboard</title>
    <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/leaderboard.css" />
	  <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/header.css" />
	<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/footer.css" />
</head>
<body>
<jsp:include page="header.jsp" />
    <h1>Leaderboard</h1>
    <table border="1">
    
        <tr>
            <th>Player Number</th>
            
            <th>Player Name</th>
            <th>Age</th>
            <th>Position</th>
            <th>Average Rating</th>
            
        </tr>
        <c:forEach var="player" items="${leaderboard}">
            <tr>
                <td>${player.playerNumber}</td>
                <td>${player.playerName}</td>
                <td>${player.age}</td>
                <td>${player.position}</td>
                <td>${player.averageRating}</td>
            </tr>
        </c:forEach>
    </table>
</body>
<jsp:include page="footer.jsp" />
</html>