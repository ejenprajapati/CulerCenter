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
 * Servlet implementation class AddPlayerServlet
 */
@WebServlet("/addPlayer")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class AddPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPlayerServlet() {}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect(request.getContextPath()+StringUtils.PAGE_ADD_PLAYER);
    }
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		DBController dbController= new DBController();
    		String player_name = request.getParameter("player_name");
    		
    		String player_number= request.getParameter("player_number");
    		String age= request.getParameter("age");
    		String position= request.getParameter("position");
    		String nationality= request.getParameter("nationality");
    		String appearances= request.getParameter("appearance");
    		String goals= request.getParameter("goals");
    		String assists= request.getParameter("assists");
    		Part imagePart = request.getPart("image");
    		
    		if (dbController.isJerseyNumberTaken(player_number)) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, "Jersey Number is already taken.");
	            request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_PLAYER).forward(request, response);
	            return; // Stop further processing
	        }
    		// Data Validation
    		
    		PlayerModel player= new PlayerModel(player_number, player_name, age, nationality,
					position, appearances, goals, assists, imagePart);
    		
    		int result = dbController.registerPlayer(player);
    		
    			if (result == 1) {
    			
    			// Get the image file name from the student object (assuming it was extracted earlier)
    			String fileName = player.getImageUrlFromPart();

    			// Check if a filename exists (not empty or null)
    			if (!fileName.isEmpty() && fileName != null) {
    			  // Construct the full image save path by combining the directory path and filename
    			  String savePath = StringUtils.IMAGE_DIR_PLAYERS;
    			  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
    			}

				/*
				 * request.setAttribute(StringUtils.MESSAGE_SUCCESS,
				 * StringUtils.MESSAGE_SUCCESS_REGISTER);
				 */
    			response.sendRedirect(request.getContextPath() + "/rate_player");
    		} else if (result == 0) {
				/*
				 * request.setAttribute(StringUtils.MESSAGE_ERROR,
				 * StringUtils.MESSAGE_ERROR_REGISTER);
				 * request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request,
				 * response);
				 */
    		} else {
				/*
				 * request.setAttribute(StringUtils.MESSAGE_ERROR,
				 * StringUtils.MESSAGE_ERROR_SERVER);
				 * request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request,
				 * response);
				 */
    		}
    	}

}
