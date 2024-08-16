package controller.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.PlayerModel;
import utils.StringUtils;

/**
 * Servlet implementation class ModifyPlayerServlet
 */
@WebServlet("/modify")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class ModifyPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		String deleteId = request.getParameter(StringUtils.DELETE_ID);
		DBController dbController = new DBController();
		if (updateId != null && !updateId.isEmpty()) {
			
			  try {
			        // Fetch the session from the database using the provided session ID
			        PlayerModel player = dbController.getPlayerByNumber(updateId);
			        
			        // Set attributes to the request for use in JSP
			        request.setAttribute("player", player);
			       
			        System.out.print(player.getImageUrlFromPart());
			        request.getRequestDispatcher(StringUtils.PAGE_URL_UPDATE).forward(request, response);
			       
			        // Forward the request to the JSP page for rendering
			        //req.getRequestDispatcher(StringUtils.PAGE_URL_ADD_UPDATE).forward(req, resp);
			    } catch (ClassNotFoundException e) {
			        e.printStackTrace();
			        // Handle the exception appropriately
			    }
			//System.out.println(updateId);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String updateId = request.getParameter("player_number");
		
		
		String deleteId = request.getParameter(StringUtils.DELETE_ID);
		
		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
			  
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
		
		
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String player_number=req.getParameter("player_number");
		String player_name=req.getParameter("player_name");
		String age=req.getParameter("age");
		String nationality= req.getParameter("nationality");
		String position=req.getParameter("position");
		String appearance=req.getParameter("appearance");
		String goals=req.getParameter("goals");
		String assist=req.getParameter("assists");
		Part imagePart = req.getPart("image");
		PlayerModel player= new PlayerModel(player_number, player_name, age, nationality,
					position, appearance, goals, assist, imagePart);
		DBController dbController = new DBController();
		dbController.updatePlayerDetails(player);
		resp.sendRedirect(req.getContextPath() + "/rate_player");
		 
		    
		  
	}
		

	/* @Override */
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBController dbController = new DBController();
		System.out.println("delete triggered");
		if (dbController.deletePlayerInfo(req.getParameter(StringUtils.DELETE_ID)) == 1) {
			req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_RATE_PLAYER);
		} else {
			req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
			resp.sendRedirect(req.getContextPath() +StringUtils.SERVLET_URL_RATE_PLAYER);
		}
	}
}
