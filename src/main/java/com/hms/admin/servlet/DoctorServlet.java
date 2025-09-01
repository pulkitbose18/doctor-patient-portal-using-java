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
import com.hms.entity.Doctor;

/**
 * This servlet handles the addition of a new doctor to the system.
 */
@WebServlet("/addDoctor")
public class DoctorServlet extends HttpServlet{

	/**
	 * Handles the HTTP POST request for adding a new doctor.
	 * 
	 * @param req The HttpServletRequest object.
	 * @param resp The HttpServletResponse object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException If an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// Get all the doctor details from the request
			String fullName = req.getParameter("fullName");
			String dateOfBirth = req.getParameter("dateOfBirth");
			String qualification = req.getParameter("qualification");
			String specialist = req.getParameter("specialist");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String password = req.getParameter("password");
			
			// Create a new Doctor object with the retrieved details
			Doctor doctor = new Doctor(fullName, dateOfBirth, qualification, specialist, email, phone, password);
			
			// Get a DoctorDAO instance
			DoctorDAO docDAO = new DoctorDAO(DBConnection.getConn());
			
			// Register the new doctor
			boolean isSuccess = docDAO.registerDoctor(doctor);

			// Get the session object
			HttpSession session = req.getSession();
			
			// Set a success or error message in the session and redirect to the doctor page
			if(isSuccess) {
				session.setAttribute("successMsg", "Doctor added Successfully");
				resp.sendRedirect("admin/doctor.jsp");
				
			}
			else {
				session.setAttribute("errorMsg", "Something went wrong on server!");
				resp.sendRedirect("admin/doctor.jsp");
			}
			
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
		}
		
	}

	
}