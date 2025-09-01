package com.hms.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.entity.User;

/**
 * This servlet handles the admin login.
 */
@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

	/**
	 * Handles the HTTP POST request for admin login.
	 * 
	 * @param req  The HttpServletRequest object.
	 * @param resp The HttpServletResponse object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// create one static Admin for this project
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			HttpSession session = req.getSession();

			// logic for a static Admin
			if ("admin@gmail.com".equals(email) && "admin".equals(password)) {

				// if "adminObj" obj available then give the access of admin page,
				// otherwise "adminObj" is not present in obj then others user is login(which is
				// not admin). so dont give him the access of Admin.
				// the below line specially check the admin is log in or not! "adminObj"
				// object is available that means admin is log in.
				session.setAttribute("adminObj", new User());
				resp.sendRedirect("admin/index.jsp");
			} else {
				session.setAttribute("errorMsg", "Invalid Username or Password.");
				resp.sendRedirect("admin_login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// Set an error message in the session and redirect to the admin login page
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "An unexpected error occurred. Please try again.");
			resp.sendRedirect("admin_login.jsp");
		}
	}

}