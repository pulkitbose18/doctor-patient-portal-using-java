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

/**
 * This servlet handles the changing of a user's password.
 */
@WebServlet("/userChangePassword")
public class ChangePasswordServlet extends HttpServlet {

	/**
	 * Handles the HTTP POST request for changing a user's password.
	 * 
	 * @param req  The HttpServletRequest object.
	 * @param resp The HttpServletResponse object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int userId = Integer.parseInt(req.getParameter("userId"));
			String oldPassword = req.getParameter("oldPassword");
			String newPassword = req.getParameter("newPassword");

			UserDAO uDAO = new UserDAO(DBConnection.getConn());

			HttpSession session = req.getSession();

			if (uDAO.checkOldPassword(userId, oldPassword)) {

				if (uDAO.changePassword(userId, newPassword)) {

					session.setAttribute("successMsg", "Password Change Successfully.");
					resp.sendRedirect("change_password.jsp");

				} else {

					session.setAttribute("errorMsg", "Something wrong on server!");
					resp.sendRedirect("change_password.jsp");

				}

			} else {
				session.setAttribute("errorMsg", "Old password incorrect");
				resp.sendRedirect("change_password.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Set an error message in the session and redirect to the change password page
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "An unexpected error occurred. Please try again.");
			resp.sendRedirect("change_password.jsp");
		}

	}

}