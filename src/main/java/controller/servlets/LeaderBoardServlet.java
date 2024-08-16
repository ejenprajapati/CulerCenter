package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.LeaderboardModel;


/**
 * Servlet implementation class LeaderBoardServlet
 */
@WebServlet("/leaderboard")
public class LeaderBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   DBController dbController= new DBController();
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<LeaderboardModel> leaderboard= dbController.getLeaderboard();
		request.setAttribute("leaderboard", leaderboard);
//		for(LeaderboardModel items:leaderboard) {
//			System.out.println(items.getPlayerName());
//		}

        // Forward the request to the JSP page
        request.getRequestDispatcher("/pages/leaderboard.jsp").forward(request, response);
	}

}
