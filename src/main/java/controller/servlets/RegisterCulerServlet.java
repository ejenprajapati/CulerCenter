package controller.servlets;


import java.io.IOException;

import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.CulerModel;
import utils.StringUtils;
//import utils.ValidationUtil;
/**
 * Servlet implementation class RegisterCuler
 */
@WebServlet("/registerculer")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class RegisterCulerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
	
	public RegisterCulerServlet() {
		this.dbController = new DBController();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = request.getParameter(StringUtils.FULL_NAME);
		
		LocalDate birthday = LocalDate.parse(request.getParameter(StringUtils.BIRTHDAY));
		String gender = request.getParameter(StringUtils.GENDER);
		String email = request.getParameter(StringUtils.EMAIL);
		String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
		
		String username = request.getParameter(StringUtils.USER_NAME);
		String password = request.getParameter(StringUtils.PASSWORD);
		Part imagePart = request.getPart("image");
		// Data Validation
		 if (dbController.isUsernameTaken(username)) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, "Username is already taken");
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	            return; // Stop further processing
	        }
		 
		CulerModel culer = new CulerModel(
				fullName, birthday, gender, email, phoneNumber, 
				 username, password, imagePart);
		int result = dbController.registerCuler(culer);
		
			if (result == 1) {
			
			// Get the image file name from the student object (assuming it was extracted earlier)
			String fileName = culer.getImageUrlFromPart();

			// Check if a filename exists (not empty or null)
			if (!fileName.isEmpty() && fileName != null) {
			  // Construct the full image save path by combining the directory path and filename
			  String savePath = StringUtils.IMAGE_DIR_USER;
			  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
			}

			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN+ "?success=true");
		} else if (result == 0) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		} else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		}
	}

}
