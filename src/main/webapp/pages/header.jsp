<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="utils.StringUtils"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
    String contextPath = request.getContextPath();
%>
 <nav>
        <div class="logo">
            <img src="<%=contextPath%>/resource/images/logo.jpg" alt="Logo Image">
        </div>
        <div class="hamburger">
            <div class="line1"></div>
            <div class="line2"></div>
            <div class="line3"></div>
        </div>
        <ul class="nav-links">
            <li><a href="<%=contextPath%>/home">Home</a></li>
            <li><a href="<%=contextPath%>/rate_player">Rate Players</a></li>
            <li><a href="<%=contextPath%>/leaderboard">Leaderboard</a></li>            
            <li><a href="#">About Us</a></li>
            <li><form action="<%
                    // Conditionally set the action URL based on user session
                    if (currentUser != null) {
                        out.print(contextPath + StringUtils.SERVLET_URL_LOGOUT);
                    } else {
                        out.print(contextPath + StringUtils.PAGE_URL_LOGIN);
                    }
                %>" method="post">
                    <input class="login-button" type="submit" value="<%
                        // Conditionally set the button label based on user session
                        if (currentUser != null) {
                            out.print(StringUtils.LOGOUT);
                            
                        } else {
                            out.print(StringUtils.LOGIN);
                        }
                    %>"/>
                </form></li> <%
                    // Conditionally set the action URL based on user session
                    if (currentUser == null) {
                    %>  	
                    	<li><form action="<%=contextPath+StringUtils.PAGE_URL_REGISTER%>" method="post"><input class="login-button" name="signup" type="submit" value="Signup"></form></li>
                   <% } else{ %><li><form action="<%=contextPath+StringUtils.SERVLET_URL_PROFILE%>" method="get">
                   <input class="login-button" name="profile" type="submit" value="profile"></form></li><%}
          %>
        </ul>
    </nav>
<script>
const hamburger = document.querySelector(".hamburger");
const navLinks = document.querySelector(".nav-links");
const links = document.querySelectorAll(".nav-links li");

hamburger.addEventListener('click', ()=>{
   //Animate Links
    navLinks.classList.toggle("open");
    links.forEach(link => {
        link.classList.toggle("fade");
    });

    //Hamburger Animation
    hamburger.classList.toggle("toggle");
});
</script>
