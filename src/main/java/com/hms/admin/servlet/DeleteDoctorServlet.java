package com.hms.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.dao.DoctorDAO;
import com.hms.db.DBConnection;

/**
 * This servlet handles the deletion of a doctor.
 */
@WebServlet("/deleteDoctor")
public class DeleteDoctorServlet extends HttpServlet {

	/**
	 * Handles the HTTP GET request for deleting a doctor.
	 * 
	 * @param req  The HttpServletRequest object.
	 * @param resp The HttpServletResponse object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// get id(which is coming as string value) and convert into int
			int id = Integer.parseInt(req.getParameter("id"));

			DoctorDAO docDAO = new DoctorDAO(DBConnection.getConn());
			HttpSession session = req.getSession();

			boolean isSuccess = docDAO.deleteDoctorById(id);

			if (isSuccess) {
				session.setAttribute("successMsg", "Doctor Deleted Successfully.");
				resp.sendRedirect("admin/view_doctor.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong on server!");
				resp.sendRedirect("admin/view_doctor.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Set an error message in the session and redirect to the view doctor page
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "An unexpected error occurred. Please try again.");
			resp.sendRedirect("admin/view_doctor.jsp");
		}
	}

}