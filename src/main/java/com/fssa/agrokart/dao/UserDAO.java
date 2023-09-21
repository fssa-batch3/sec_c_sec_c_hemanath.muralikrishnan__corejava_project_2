package com.fssa.agrokart.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fssa.agrokart.exception.ConnectionException;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.model.Gender;
import com.fssa.agrokart.model.User;
import com.fssa.agrokart.util.ConnectionUtil;
import com.fssa.agrokart.util.ExceptionLoggerUtil;

public class UserDAO {

	/**
	 * Registers a new user by inserting their information into the 'users' table in
	 * the database.
	 *
	 * @param user The User object containing user registration information.
	 * @return true if the registration is successful; otherwise, false.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public boolean registerUser(User user) throws DAOException {
		// SQL query to insert user data into the 'users' table
		String sql = "INSERT INTO users (first_name, last_name, email_id, password, phone_number) VALUES (?,?,?,?,?)";

		try (Connection conn = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				// Set user data parameters
				pst.setString(1, user.getFirstName().trim());
				pst.setString(2, user.getLastName().trim());
				pst.setString(3, user.getEmailId().trim());
				pst.setString(4, hashPassword(user.getPassword()).trim());
				pst.setString(5, user.getPhoneNumber().trim());

				// Execute the SQL query
				int rows = pst.executeUpdate();

				// Registration is successful if one or more rows were affected
				return rows > 0;
			}
		} catch (SQLException | ConnectionException e) {
			// Log and rethrow SQL exceptions as DAOException
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while registering user: " + e.getMessage(), e);
		}

	}

	/**
	 * Checks if an email address already exists in the 'users' table.
	 *
	 * @param email The email address to check.
	 * @return true if the email address exists; otherwise, false.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public boolean isEmailExists(String email) throws DAOException {
		String sql = "SELECT COUNT(*) FROM users WHERE email_id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, email.trim());

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while checking email existence: " + e.getMessage(), e);
		}

		return false;
	}

	/**
	 * Finds a user by their email address.
	 *
	 * @param email The email address of the user to find.
	 * @return A User object if found, or null if not found.
	 * @throws DAOException If there is an error in the data access operation.
	 */
	public User findUserByEmail(String email) throws DAOException {

		String sql = "SELECT id, image_Url, first_name, last_name, gender, email_id, phone_number FROM users "
				+ "WHERE email_id = ?";

		User user = null;

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setString(1, email);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {

					user = new User(); // Initialize a new User object

					user.setId(rs.getInt("id"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					if (rs.getString("gender") != null) {
						user.setGender(Gender.valueOf(rs.getString("gender")));
					}
					user.setEmailId(rs.getString("email_id"));
					user.setPhoneNumber(rs.getString("phone_number"));

				}
			}

		} catch (SQLException | ConnectionException e) {

			ExceptionLoggerUtil.logException(e);
			// Wrap the exception and provide an error message
			throw new DAOException("Error finding user by email: " + e.getMessage(), e);
		}

		return user;
	}

	/**
	 * Checks if a phone number already exists in the 'users' table.
	 *
	 * @param phoneNumber The phone number to check.
	 * @return true if the phone number exists; otherwise, false.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public boolean isPhoneNumberExists(String phoneNumber) throws DAOException {
		String sql = "SELECT COUNT(*) FROM users WHERE phone_number = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, phoneNumber.trim());

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while checking phone number existence: " + e.getMessage(), e);
		}

		return false;
	}

	/**
	 * Authenticates a user based on their email address and password.
	 *
	 * @param email    The user's email address.
	 * @param password The user's password.
	 * @return true if authentication is successful; otherwise, false.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public boolean login(String email, String password) throws DAOException {
		String sql = "SELECT password FROM users WHERE email_id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, email.trim());

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String storedHashedPassword = rs.getString("password");
					// Compare the entered password with the stored hashed password
					return checkPassword(password, storedHashedPassword);
				}
			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error during login: " + e.getMessage(), e);
		}

		return false;
	}

	/**
	 * Hashes the provided password using the SHA-256 cryptographic hash function.
	 *
	 * @param password the raw password to be hashed.
	 * @return the hashed password as a hexadecimal string.
	 * @throws InvalidEmployeeException if an error occurs during hashing (e.g.,
	 *                                  NoSuchAlgorithmException).
	 */
	public static String hashPassword(String password) throws DAOException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			// Convert the byte array to a hexadecimal string
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Checks if the entered password matches the stored hashed password.
	 *
	 * @param enteredPassword      The password entered by the user during login.
	 * @param storedHashedPassword The hashed password stored in the database.
	 * @return true if the entered password is correct; otherwise, false.
	 * @throws InvalidEmployeeException if an error occurs during hashing.
	 */
	public static boolean checkPassword(String enteredPassword, String storedHashedPassword) throws DAOException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] enteredHashedBytes = md.digest(enteredPassword.getBytes(StandardCharsets.UTF_8));

			// Convert the byte array to a hexadecimal string
			StringBuilder enteredHashedPassword = new StringBuilder();
			for (byte b : enteredHashedBytes) {
				enteredHashedPassword.append(String.format("%02x", b));
			}

			// Compare the entered hashed password with the stored hashed password
			return enteredHashedPassword.toString().equals(storedHashedPassword);
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Updates the user profile with the provided information.
	 *
	 * @param user The User object containing the updated user profile information.
	 * @return true if the user profile was successfully updated; otherwise, false.
	 * @throws DAOException If there is an issue while updating the user profile in
	 *                      the database.
	 */
	public boolean updateUserProfile(User user) throws DAOException {
		// SQL query to update user profile
		String sql = "UPDATE users SET first_name = ?, last_name = ?, gender = ? WHERE id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			// Set parameters for the SQL query
			pst.setString(1, user.getFirstName().trim());
			pst.setString(2, user.getLastName().trim());
			pst.setString(3, user.getGender().toString());
			pst.setInt(4, user.getId());

			// Execute the update query
			int row = pst.executeUpdate();

			// Return true if the update was successful
			return row > 0;

		} catch (SQLException | ConnectionException e) {
			// Throw a DAOException if an error occurs
			throw new DAOException("Error while updating the user profile: " + e.getMessage(), e);
		}
	}

	/**
	 * Checks if a user with the given ID exists in the database.
	 *
	 * @param id The ID of the user to check for existence.
	 * @return true if a user with the specified ID exists; otherwise, false.
	 * @throws DAOException If there is an issue while accessing the database or
	 *                      executing the query.
	 */
	public boolean isUserExistsWithID(int id) throws DAOException {
		// SQL query to count the number of users with the specified ID
		String sql = "SELECT COUNT(*) FROM users WHERE id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			// Set the parameter for the SQL query
			pst.setInt(1, id);

			try (ResultSet resultSet = pst.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					// Return true if a user with the specified ID exists
					return count > 0;
				}
			}
		} catch (SQLException | ConnectionException e) {
			// Throw a DAOException if an error occurs
			throw new DAOException("Error while checking user existence by ID: " + e.getMessage(), e);
		}

		// Return false if no user with the specified ID was found
		return false;
	}

}
