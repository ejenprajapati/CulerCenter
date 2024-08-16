package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.CulerModel;

import utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController dbController = new DBController();

		/* ArrayList<PlayerModel> players = dbController.getAllPlayersInfo(); */

        int userCount=dbController.getUserCount();
        int playerCount=dbController.getPlayerCount();
        int interactions=dbController.getTotalInteractions();
        
        
        HttpSession session = request.getSession(false);
        // Don't create a new session if it doesn't exist
		String username = (String) session.getAttribute("username"); // Assuming you've stored the username in the session

		// Check if the username matches the admin username
		if (username != null && username.equals("admin")) {
			ArrayList<CulerModel> culer = dbController.getAllCulersInfo();
			request.setAttribute(StringUtils.CULER_LISTS, culer);
			request.setAttribute("userCount", userCount);
	        request.setAttribute("playerCount", playerCount);
	        request.setAttribute("interactions", interactions);


	        // Forward the request to the JSP
	        request.getRequestDispatcher("/pages/adminDashboard.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_HOME);
		}
        // Set players list as an attribute in the request
        
    }
}
