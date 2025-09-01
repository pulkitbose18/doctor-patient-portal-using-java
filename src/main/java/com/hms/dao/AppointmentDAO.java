package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hms.entity.Appointment;

public class AppointmentDAO {

	private static final String INSERT_APPOINTMENT = "insert into appointment(userId, fullName, gender, age, appointmentDate, email, phone, diseases, doctorId, address, status) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_APPOINTMENTS_BY_USER_ID = "select * from appointment where userId=?";
	private static final String SELECT_APPOINTMENTS_BY_DOCTOR_ID = "select * from appointment where doctorId=?";
	private static final String SELECT_APPOINTMENT_BY_ID = "select * from appointment where id=?";
	private static final String UPDATE_APPOINTMENT_STATUS = "update appointment set status=? where id=? and doctorId=?";
	private static final String SELECT_ALL_APPOINTMENTS = "select * from appointment order by id desc";

	private Connection conn;

	public AppointmentDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Adds a new appointment to the database.
	 * 
	 * @param appointment The Appointment object containing the appointment information.
	 * @return true if the appointment is added successfully, false otherwise.
	 */
	public boolean addAppointment(Appointment appointment) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(INSERT_APPOINTMENT);

			pstmt.setInt(1, appointment.getUserId());
			pstmt.setString(2, appointment.getFullName());
			pstmt.setString(3, appointment.getGender());
			pstmt.setString(4, appointment.getAge());
			pstmt.setString(5, appointment.getAppointmentDate());
			pstmt.setString(6, appointment.getEmail());
			pstmt.setString(7, appointment.getPhone());
			pstmt.setString(8, appointment.getDiseases());
			pstmt.setInt(9, appointment.getDoctorId());
			pstmt.setString(10, appointment.getAddress());
			pstmt.setString(11, appointment.getStatus());

			pstmt.executeUpdate();

			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * Retrieves a list of all appointments for a specific user.
	 * 
	 * @param userId The ID of the user.
	 * @return A list of Appointment objects.
	 */
	public List<Appointment> getAllAppointmentByLoginUser(int userId) {
		List<Appointment> appList = new ArrayList<Appointment>();

		Appointment appointment = null;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_APPOINTMENTS_BY_USER_ID);

			pstmt.setInt(1, userId);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setDiseases(resultSet.getString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;

	}

	/**
	 * Retrieves a list of all appointments for a specific doctor.
	 * 
	 * @param doctorId The ID of the doctor.
	 * @return A list of Appointment objects.
	 */
	public List<Appointment> getAllAppointmentByLoginDoctor(int doctorId) {
		List<Appointment> appList = new ArrayList<Appointment>();

		Appointment appointment = null;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_APPOINTMENTS_BY_DOCTOR_ID);

			pstmt.setInt(1, doctorId);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setDiseases(resultSet.getString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;

	}

	/**
	 * Retrieves an appointment by its ID.
	 * 
	 * @param id The ID of the appointment to retrieve.
	 * @return An Appointment object if found, null otherwise.
	 */
	public Appointment getAppointmentById(int id) {

		Appointment appointment = null;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_APPOINTMENT_BY_ID);

			pstmt.setInt(1, id);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setDiseases(resultSet.getString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appointment;

	}

	/**
	 * Updates the comment status of an appointment.
	 * 
	 * @param apptId  The ID of the appointment.
	 * @param docId   The ID of the doctor.
	 * @param comment The comment to be updated.
	 * @return true if the update is successful, false otherwise.
	 */
	public boolean updateDrAppointmentCommentStatus(int apptId, int docId, String comment) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(UPDATE_APPOINTMENT_STATUS);
			pstmt.setString(1, comment);
			pstmt.setInt(2, apptId);
			pstmt.setInt(3, docId);

			pstmt.executeUpdate();

			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * Retrieves a list of all appointments from the database.
	 * 
	 * @return A list of all Appointment objects.
	 */
	public List<Appointment> getAllAppointment() {
		List<Appointment> appList = new ArrayList<Appointment>();
		Appointment appointment = null;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_ALL_APPOINTMENTS);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setDiseases(resultSet.getString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;
	}

}