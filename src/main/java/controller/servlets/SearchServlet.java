package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.PlayerModel;
import utils.StringUtils;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBController controller = new DBController();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchValue= request.getParameter("search");
		ArrayList<PlayerModel> players = controller.getSearchPlayerInfo(searchValue);
		request.setAttribute(StringUtils.PLAYERS_LISTS, players);
		HttpSession session = request.getSession(false); // Don't create a new session if it doesn't exist
		String username = (String) session.getAttribute("username"); // Assuming you've stored the username in the session

		// Check if the username matches the admin username
		if (username != null && username.equals("admin")) {
			request.getRequestDispatcher(StringUtils.PAGE_URL_RATE_PLAYER).forward(request, response);
		} else {
			request.getRequestDispatcher(StringUtils.PAGE_URL_RATE_PLAYER_USER).forward(request, response);
		}
		
		
		
	}

}
