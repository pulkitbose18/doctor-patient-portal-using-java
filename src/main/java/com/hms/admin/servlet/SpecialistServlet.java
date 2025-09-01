package com.hms.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.dao.SpecialistDAO;
import com.hms.db.DBConnection;

/**
 * This servlet handles the addition of a new specialist.
 */
@WebServlet("/addSpecialist")
public class SpecialistServlet extends HttpServlet {

	/**
	 * Handles the HTTP POST request for adding a new specialist.
	 * 
	 * @param req  The HttpServletRequest object.
	 * @param resp The HttpServletResponse object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String specialistName = req.getParameter("specialistName");

			SpecialistDAO specialistDAO = new SpecialistDAO(DBConnection.getConn());
			boolean isSuccess = specialistDAO.addSpecialist(specialistName);

			HttpSession session = req.getSession();

			if (isSuccess) {
				session.setAttribute("successMsg", "Specialist added Successfully.");
				resp.sendRedirect("admin/index.jsp");

			} else {
				session.setAttribute("errorMsg", "Something went wrong on server");
				resp.sendRedirect("admin/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Set an error message in the session and redirect to the admin index page
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "An unexpected error occurred. Please try again.");
			resp.sendRedirect("admin/index.jsp");
		}
	}

}