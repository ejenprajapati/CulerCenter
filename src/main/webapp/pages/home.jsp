<%-- <%@page import="model.StudentModel"%> --%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Culer Center - Home</title>
    <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/header.css" />
	<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/home.css" />
     <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/footer.css" />
</head>

<body>
<jsp:include page="header.jsp" />

<div class="banner">
        
        <div class="content">
            <h1>Welcome Culer.</h1>
            <p>You can show your support to your favorite player of FC Barcelona.</p>
            <div>
            <form  method="GET" action="<%=contextPath%>/rate_player">
                <button type="submit"><span></span>Vote</button>
                </form>
            </div>
        </div>
    </div>
</body>



<jsp:include page="footer.jsp" />
</html>