package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hms.entity.Specialist;

public class SpecialistDAO {

	private static final String INSERT_SPECIALIST = "insert into specialist (specialist_name) values(?)";
	private static final String SELECT_ALL_SPECIALISTS = "select * from specialist";

	private Connection conn;

	public SpecialistDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Adds a new specialist to the database.
	 * 
	 * @param specialistName The name of the specialist.
	 * @return true if the specialist is added successfully, false otherwise.
	 */
	public boolean addSpecialist(String specialistName) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(INSERT_SPECIALIST);

			pstmt.setString(1, specialistName);

			pstmt.executeUpdate();

			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;

	}

	/**
	 * Retrieves a list of all specialists from the database.
	 * 
	 * @return A list of Specialist objects.
	 */
	public List<Specialist> getAllSpecialist() {

		List<Specialist> spList = new ArrayList<Specialist>();

		Specialist specialistObj = null;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_ALL_SPECIALISTS);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				// create object
				specialistObj = new Specialist();
				
				specialistObj.setId(resultSet.getInt(1));// column index number 1 -(id)
				specialistObj.setSpecialistName(resultSet.getString(2));// column index number 2 -(specialist_name)

				// now add specialist object into List
				spList.add(specialistObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return spList;
	}

}