package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import com.hms.entity.Doctor;

public class DoctorDAO {

	private static final String INSERT_DOCTOR = "insert into doctor(fullName,dateOfBirth,qualification,specialist,email,phone,password) values(?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_DOCTORS = "select * from doctor order by id desc";
	private static final String SELECT_DOCTOR_BY_ID = "select * from doctor where id=?";
	private static final String UPDATE_DOCTOR = "update doctor set fullName=?,dateOfBirth=?,qualification=?,specialist=?,email=?,phone=?,password=? where id=?";
	private static final String DELETE_DOCTOR_BY_ID = "delete from doctor where id=?";
	private static final String SELECT_DOCTOR_BY_EMAIL_AND_PASSWORD = "select * from doctor where email=? and password=?";
	private static final String COUNT_RECORDS = "select count(*) from ";
	private static final String SELECT_APPOINTMENT_BY_DOCTOR_ID = "select * from appointment where doctorId=?";
	private static final String CHECK_OLD_PASSWORD = "select * from doctor where id=? and password=?";
	private static final String UPDATE_PASSWORD = "update doctor set password=? where id=?";
	private static final String UPDATE_DOCTOR_PROFILE = "update doctor set fullName=?,dateOfBirth=?,qualification=?,specialist=?,email=?,phone=? where id=?";



	private Connection conn;

	public DoctorDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Registers a new doctor in the database.
	 * 
	 * @param doctor The Doctor object containing the doctor's information.
	 * @return true if the doctor is registered successfully, false otherwise.
	 */
	public boolean registerDoctor(Doctor doctor) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(INSERT_DOCTOR);
			pstmt.setString(1, doctor.getFullName());
			pstmt.setString(2, doctor.getDateOfBirth());
			pstmt.setString(3, doctor.getQualification());
			pstmt.setString(4, doctor.getSpecialist());
			pstmt.setString(5, doctor.getEmail());
			pstmt.setString(6, doctor.getPhone());
			pstmt.setString(7, doctor.getPassword());

			pstmt.executeUpdate();
			// if query inserted or all ok than
			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * Retrieves a list of all doctors from the database.
	 * 
	 * @return A list of Doctor objects.
	 */
	public List<Doctor> getAllDoctor() {

		Doctor doctor = null;
		List<Doctor> docList = new ArrayList<Doctor>();

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_ALL_DOCTORS);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				doctor = new Doctor();

				doctor.setId(resultSet.getInt("id"));
				doctor.setFullName(resultSet.getString("fullName"));
				doctor.setDateOfBirth(resultSet.getString("dateOfBirth"));
				doctor.setQualification(resultSet.getString("qualification"));
				doctor.setSpecialist(resultSet.getString("specialist"));
				doctor.setEmail(resultSet.getString("email"));
				doctor.setPhone(resultSet.getString("phone"));
				doctor.setPassword(resultSet.getString("password"));
				docList.add(doctor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return docList;
	}

	/**
	 * Retrieves a doctor from the database by their ID.
	 * 
	 * @param id The ID of the doctor to retrieve.
	 * @return A Doctor object if found, null otherwise.
	 */
	public Doctor getDoctorById(int id) {

		Doctor doctor = null;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_DOCTOR_BY_ID);
			pstmt.setInt(1, id);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				doctor = new Doctor();

				doctor.setId(resultSet.getInt("id"));
				doctor.setFullName(resultSet.getString("fullName"));
				doctor.setDateOfBirth(resultSet.getString("dateOfBirth"));
				doctor.setQualification(resultSet.getString("qualification"));
				doctor.setSpecialist(resultSet.getString("specialist"));
				doctor.setEmail(resultSet.getString("email"));
				doctor.setPhone(resultSet.getString("phone"));
				doctor.setPassword(resultSet.getString("password"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctor;
	}

	/**
	 * Updates a doctor's information in the database.
	 * 
	 * @param doctor The Doctor object containing the updated information.
	 * @return true if the update is successful, false otherwise.
	 */
	public boolean updateDoctor(Doctor doctor) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(UPDATE_DOCTOR);
			pstmt.setString(1, doctor.getFullName());
			pstmt.setString(2, doctor.getDateOfBirth());
			pstmt.setString(3, doctor.getQualification());
			pstmt.setString(4, doctor.getSpecialist());
			pstmt.setString(5, doctor.getEmail());
			pstmt.setString(6, doctor.getPhone());
			pstmt.setString(7, doctor.getPassword());
			// need to set id also for update
			pstmt.setInt(8, doctor.getId());

			pstmt.executeUpdate();
			// if query updated or all ok than
			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * Deletes a doctor from the database by their ID.
	 * 
	 * @param id The ID of the doctor to delete.
	 * @return true if the deletion is successful, false otherwise.
	 */
	public boolean deleteDoctorById(int id) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(DELETE_DOCTOR_BY_ID);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * Authenticates a doctor by their email and password.
	 * 
	 * @param email    The doctor's email.
	 * @param password The doctor's password.
	 * @return A Doctor object if the login is successful, null otherwise.
	 */
	public Doctor loginDoctor(String email, String password) {

		Doctor doctor = null;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_DOCTOR_BY_EMAIL_AND_PASSWORD);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				doctor = new Doctor();

				
				doctor.setId(resultSet.getInt(1));
				doctor.setFullName(resultSet.getString(2));
				doctor.setDateOfBirth(resultSet.getString(3));
				doctor.setQualification(resultSet.getString(4));
				doctor.setSpecialist(resultSet.getString(5));
				doctor.setEmail(resultSet.getString(6));
				doctor.setPhone(resultSet.getString(7));
				doctor.setPassword(resultSet.getString(8));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctor;

	}

	
	private int countRecords(String tableName) {
		int count = 0;
		try {
			String sql = COUNT_RECORDS + tableName;
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * Counts the total number of doctors in the database.
	 * 
	 * @return The total number of doctors.
	 */
	public int countTotalDoctor() {
		return countRecords("doctor");
	}

	/**
	 * Counts the total number of appointments in the database.
	 * 
	 * @return The total number of appointments.
	 */
	public int countTotalAppointment() {
		return countRecords("appointment");
	}

	/**
	 * Counts the total number of appointments for a specific doctor.
	 * 
	 * @param doctorId The ID of the doctor.
	 * @return The total number of appointments for the specified doctor.
	 */
	public int countTotalAppointmentByDoctorId(int doctorId) {

		int i = 0;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_APPOINTMENT_BY_DOCTOR_ID);
			pstmt.setInt(1, doctorId);

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * Counts the total number of users in the database.
	 * 
	 * @return The total number of users.
	 */
	public int countTotalUser() {
		return countRecords("user_details");
	}

	/**
	 * Counts the total number of specialists in the database.
	 * 
	 * @return The total number of specialists.
	 */
	public int countTotalSpecialist() {
		return countRecords("specialist");
	}

	/**
	 * Checks if the old password matches the one in the database.
	 * 
	 * @param doctorId    The ID of the doctor.
	 * @param oldPassword The old password to check.
	 * @return true if the old password is correct, false otherwise.
	 */
	public boolean checkOldPassword(int doctorId, String oldPassword) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(CHECK_OLD_PASSWORD);
			pstmt.setInt(1, doctorId);
			pstmt.setString(2, oldPassword);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				isSuccess = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * Changes the password for a doctor.
	 * 
	 * @param doctorId    The ID of the doctor.
	 * @param newPassword The new password.
	 * @return true if the password is changed successfully, false otherwise.
	 */
	public boolean changePassword(int doctorId, String newPassword) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(UPDATE_PASSWORD);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, doctorId);

			pstmt.executeUpdate();

			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * Edits the profile of a doctor.
	 * 
	 * @param doctor The Doctor object containing the updated profile information.
	 * @return true if the profile is edited successfully, false otherwise.
	 */
	public boolean editDoctorProfile(Doctor doctor) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(UPDATE_DOCTOR_PROFILE);
			pstmt.setString(1, doctor.getFullName());
			pstmt.setString(2, doctor.getDateOfBirth());
			pstmt.setString(3, doctor.getQualification());
			pstmt.setString(4, doctor.getSpecialist());
			pstmt.setString(5, doctor.getEmail());
			pstmt.setString(6, doctor.getPhone());
			//pstmt.setString(7, doctor.getPassword());
			// need to set id also for update
			pstmt.setInt(7, doctor.getId());

			pstmt.executeUpdate();
			// if query updated or all okay than
			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

}