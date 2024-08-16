package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.CulerModel;

import utils.StringUtils;

/**
 * Servlet implementation class ModifyPlayerServlet
 */
@WebServlet("/updateProfile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("Update Servlet");
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		System.out.print(updateId);
		String deleteId = request.getParameter(StringUtils.DELETE_ID);
		DBController dbController = new DBController();
		if (updateId != null && !updateId.isEmpty()) {
			
			  
			        // Fetch the session from the database using the provided session ID
			        CulerModel user = dbController.getCulerDetails(updateId);
			        
			        // Set attributes to the request for use in JSP
			        request.setAttribute("culer", user);
			       
			        System.out.print(user.getImageUrlFromPart());
			        request.getRequestDispatcher(StringUtils.PAGE_URL_UPDATE_USER).forward(request, response);
			       
			        // Forward the request to the JSP page for rendering
			        //req.getRequestDispatcher(StringUtils.PAGE_URL_ADD_UPDATE).forward(req, resp);
			   
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String updateId= request.getParameter("userId");
//		DBController dbController = new DBController();
		
		
		HttpSession session = request.getSession(false);
        // Don't create a new session if it doesn't exist
		String username = (String) session.getAttribute("username");
		System.out.print("in post user up "+ updateId + " "+ username);
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
		
		 String full_name=req.getParameter("fullName"); 
		 String username=req.getParameter("userId"); 
		 LocalDate birthday = LocalDate.parse(req.getParameter(StringUtils.BIRTHDAY));
		 String email = req.getParameter(StringUtils.EMAIL);
		 String phoneNumber = req.getParameter(StringUtils.PHONE_NUMBER);
		 Part imagePart = req.getPart("image");
		 CulerModel culer= new CulerModel();
		 culer.setImageUrlFromPart(imagePart);
		 String imageUrl=culer.getImageUrlFromPart();
		 DBController dbController = new DBController();

//			HttpSession session = req.getSession(false);
	        // Don't create a new session if it doesn't exist
			
		 dbController.updateCulerDetails(full_name, username, phoneNumber, imageUrl, email, birthday);
		resp.sendRedirect(req.getContextPath() + "/profile");
			/*
			 * dbController.updateUserDetails(player);
			 * resp.sendRedirect(req.getContextPath() + "/rate_player");
			 */
		 
		    
		  
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
