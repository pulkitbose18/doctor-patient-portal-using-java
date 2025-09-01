package com.hms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.dao.AppointmentDAO;
import com.hms.db.DBConnection;
import com.hms.entity.Appointment;

/**
 * This servlet handles the creation of a new appointment.
 */
@WebServlet("/addAppointment")
public class AppointmentServlet extends HttpServlet {

	/**
	 * Handles the HTTP POST request for adding a new appointment.
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
			String fullName = req.getParameter("fullName");
			String gender = req.getParameter("gender");
			String age = req.getParameter("age");
			String appointmentDate = req.getParameter("appointmentDate");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String diseases = req.getParameter("diseases");
			int doctorId = Integer.parseInt(req.getParameter("doctorNameSelect"));
			String address = req.getParameter("address");

			Appointment appointment = new Appointment(userId, fullName, gender, age, appointmentDate, email, phone, diseases,
					doctorId, address, "Pending");

			AppointmentDAO appointmentDAO = new AppointmentDAO(DBConnection.getConn());
			boolean isSuccess = appointmentDAO.addAppointment(appointment);

			// get session
			HttpSession session = req.getSession();

			if (isSuccess) {

				session.setAttribute("successMsg", "Appointment is recorded Successfully.");
				resp.sendRedirect("user_appointment.jsp");

			} else {

				session.setAttribute("errorMsg", "Something went wrong on server!");
				resp.sendRedirect("user_appointment.jsp");

			}
		} catch (Exception e) {
			e.printStackTrace();
			// Set an error message in the session and redirect to the appointment page
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "An unexpected error occurred. Please try again.");
			resp.sendRedirect("user_appointment.jsp");
		}

	}

}