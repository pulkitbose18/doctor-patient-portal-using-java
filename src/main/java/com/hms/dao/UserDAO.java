package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hms.entity.User;

public class UserDAO {

	private static final String INSERT_USER = "insert into user_details(full_name, email, password) values(?,?,?)";
	private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "select * from user_details where email=? and password=?";
	private static final String CHECK_OLD_PASSWORD = "select * from user_details where id=? and password=?";
	private static final String UPDATE_PASSWORD = "update user_details set password=? where id=?";

	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Registers a new user in the database.
	 * 
	 * @param user The User object containing the user's information.
	 * @return true if the user is registered successfully, false otherwise.
	 */
	public boolean userRegister(User user) {

		boolean isSuccess = false;

		try {
			// insert user in db
			PreparedStatement pstmt = conn.prepareStatement(INSERT_USER);
			pstmt.setString(1, user.getFullName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());

			pstmt.executeUpdate();

			isSuccess = true; // if query execute successfully then isSuccess becomes true otherwise false...

		} catch (Exception e) {
			e.printStackTrace();

		}

		return isSuccess;
	}

	/**
	 * Authenticates a user by their email and password.
	 * 
	 * @param email    The user's email.
	 * @param password The user's password.
	 * @return A User object if the login is successful, null otherwise.
	 */
	public User loginUser(String email, String password) {

		User user = null;

		try {
			PreparedStatement pstmt = this.conn.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				// if any row available, then fetch the data of that row...

				// create new user object
				user = new User();

				// fetch data one by one from db table and set it/bind it to user's objects.
				user.setId(resultSet.getInt("id"));
				user.setFullName(resultSet.getString("full_name"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	/**
	 * Checks if the old password matches the one in the database.
	 * 
	 * @param userId      The ID of the user.
	 * @param oldPassword The old password to check.
	 * @return true if the old password is correct, false otherwise.
	 */
	public boolean checkOldPassword(int userId, String oldPassword) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(CHECK_OLD_PASSWORD);
			pstmt.setInt(1, userId);
			pstmt.setString(2, oldPassword);

			ResultSet resultSet = pstmt.executeQuery();
			//System.out.println(resultSet);
			while (resultSet.next()) {
				isSuccess = true;
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	/**
	 * Changes the password for a user.
	 * 
	 * @param userId      The ID of the user.
	 * @param newPassword The new password.
	 * @return true if the password is changed successfully, false otherwise.
	 */
	public boolean changePassword(int userId, String newPassword) {

		boolean isSuccess = false;

		try {

			PreparedStatement pstmt = this.conn.prepareStatement(UPDATE_PASSWORD);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, userId);

			pstmt.executeUpdate();

			isSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

}