package com.hms.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * This servlet handles user registration.
 */
@WebServlet("/user_register")
public class UserRegisterServlet extends HttpServlet {

	/**
	 * Handles the HTTP POST request for user registration.
	 * 
	 * @param req  The HttpServletRequest object.
	 * @param resp The HttpServletResponse object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// get all data/value which is coming from signup.jsp page for new User
			// registration
			String fullName = req.getParameter("fullName");
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			// Set all data to User Entity
			User user = new User(fullName, email, password);

			// Create Connection with DB
			UserDAO userDAO = new UserDAO(DBConnection.getConn());

			// get session
			HttpSession session = req.getSession();

			// call userRegister() and pass user object to insert or save user into DB.
			boolean isSuccess = userDAO.userRegister(user); // userRegister() method return boolean type value

			if (isSuccess) {

				session.setAttribute("successMsg", "Register Successfully");
				resp.sendRedirect("signup.jsp");// which page you want to show this msg

			} else {

				session.setAttribute("errorMsg", "Something went wrong!");
				resp.sendRedirect("signup.jsp");// which page you want to show this msg

			}

		} catch (Exception e) {
			e.printStackTrace();
			// Set an error message in the session and redirect to the signup page
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "An unexpected error occurred. Please try again.");
			resp.sendRedirect("signup.jsp");
		}

	}

}