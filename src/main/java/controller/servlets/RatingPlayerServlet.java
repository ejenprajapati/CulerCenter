package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import utils.StringUtils;

/**
 * Servlet implementation class RatingPlayerServlet
 */
@WebServlet("/rating")
public class RatingPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		    String username = (String) session.getAttribute("username");
		    DBController dbController= new DBController();
		    // Ensure username is not null
		    if(username != null) {
		        // Now you have the username, you can proceed with your logic
		        // Extract the voted value and player ID from the request parameters
		        String vote = (request.getParameter("voteInput"));
		        String playerId = (request.getParameter("playerId"));
		        dbController.savePlayerRating(username, playerId, vote);
		        
		        response.sendRedirect(request.getContextPath()+StringUtils.SERVLET_URL_RATE_PLAYER);
		    } else {
		        // Handle the case where the username is not found in the session
		        // For example, redirect the user to the login page
		        response.sendRedirect(request.getContextPath()+StringUtils.PAGE_URL_LOGIN);
		    }
	}

}
