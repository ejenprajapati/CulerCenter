
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
    <meta charset="UTF-8">
    <title>User Profile</title>
     <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/footer.css" />
	 <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/header.css" />
	 <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/profile.css" /> <!-- Assuming you have a separate CSS file -->
</head>
<body>
<jsp:include page="header.jsp" />	        
       
   <div class="container h-100">
  <div class="row h-100 align-items-center justify-content-center">
    <div class="main">
      <div class="info">
        <img src="resource/images/user/${culerModel.imageUrlFromPart}"  class="prof rounded-circle img-fluid" />
        <h1>${culerModel.full_Name}</h1>
        <hr>
      </div>
      <div class="body">
       <ul class="fa-ul">
       	<li><span class="fa-li"><i class="fas fa-heart"></i></span>Email: ${culerModel.email}</li>        
         <li><span class="fa-li"><i class="fas fa-heart"></i></span>Birthday: ${culerModel.birthday}</li>
         <li><span class="fa-li"><i class="fas fa-heart"></i></span>Phone Number: ${culerModel.phoneNumber}</li>
        </ul>
        <form method="GET" action="<%=contextPath + StringUtils.SERVLET_URL_UPDATE_PROFILE%>">
	<input type="hidden" name="<%=StringUtils.UPDATE_ID%>" value="${culerModel.username}" />
	<button type="submit">Update</button>
  </form>
      
      </div>
      
   
    </div>
  </div>
  
</div>
</body>
</html>
