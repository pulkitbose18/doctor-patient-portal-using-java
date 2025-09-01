package com.hms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.dao.UserDAO;
import com.hms.db.DBConnection;
import com.hms.entity.User;

/**
 * This servlet handles user login.
 */
@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {

	/**
	 * Handles the HTTP POST request for user login.
	 * 
	 * @param req  The HttpServletRequest object.
	 * @param resp The HttpServletResponse object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			HttpSession session = req.getSession();

			UserDAO userDAO = new UserDAO(DBConnection.getConn());
			User user = userDAO.loginUser(email, password);

			if (user != null) {
				session.setAttribute("userObj", user);
				resp.sendRedirect("index.jsp");
			} else {
				session.setAttribute("errorMsg", "Invalid email or password");
				resp.sendRedirect("user_login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Set an error message in the session and redirect to the login page
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "An unexpected error occurred. Please try again.");
			resp.sendRedirect("user_login.jsp");
		}
	}

}