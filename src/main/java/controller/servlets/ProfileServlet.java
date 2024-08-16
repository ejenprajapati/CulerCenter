package controller.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.CulerModel;
import utils.StringUtils;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("prof");
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		DBController dbContoller = new DBController();
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			
			CulerModel culerInfo = dbContoller.getCulerDetails(username);

			request.setAttribute("culerModel", culerInfo);
			request.getRequestDispatcher(StringUtils.URL_USER_PROFILE).forward(request, response);
			return;
			
			  } else { response.sendRedirect(request.getContextPath() +
			  StringUtils.PAGE_URL_REGISTER); return;
			 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		System.out.println(updateId);

		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * String updatedPhoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
		 * 
		 * String updatedFirstName = request.getParameter(StringUtils.FIRST_NAME);
		 * String updatedLastName = request.getParameter(StringUtils.LAST_NAME);
		 * LocalDate updatedDob =
		 * LocalDate.parse(request.getParameter(StringUtils.DATE));
		 * 
		 * 
		 * UserModel updatedUser = new UserModel();
		 * updatedUser.setPhoneNumber(updatedPhoneNumber);
		 * updatedUser.setFirstName(updatedFirstName);
		 * updatedUser.setLastName(updatedLastName); updatedUser.setDob(updatedDob);
		 * 
		 * 
		 * DBController dbController = new DBController(); if
		 * (dbController.updateUserProfile(updatedUser) == 1) {
		 * request.setAttribute(StringUtils.MESSAGE_SUCCESS,
		 * StringUtils.MESSAGE_SUCCESS_UPDATE); } else {
		 * request.setAttribute(StringUtils.MESSAGE_ERROR,
		 * StringUtils.MESSAGE_ERROR_USER_UPDATE); }
		 * response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_HOME);
		 * return;
		 */
	}

}
