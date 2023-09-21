package com.fssa.agrokart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.agrokart.exception.ConnectionException;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.model.UserAddress;
import com.fssa.agrokart.util.ConnectionUtil;
import com.fssa.agrokart.util.ExceptionLoggerUtil;

public class UserAddressDAO {

	/**
	 * Inserts a new user address into the database.
	 *
	 * @param address The UserAddress object to be inserted.
	 * @return true if the insertion was successful, false otherwise.
	 * @throws DAOException If there is an error while performing the database
	 *                      operation.
	 */
	public boolean addAddress(UserAddress address) throws DAOException {

		String sql = "INSERT INTO user_address(user_id,full_name,full_address,pincode,phone_number) VALUES(?,?,?,?,?)";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setInt(1, address.getUserId());
			pst.setString(2, address.getFullName());
			pst.setString(3, address.getAddress());
			pst.setString(4, address.getPincode());
			pst.setString(5, address.getPhoneNumber());

			int row = pst.executeUpdate();

			return row > 0;

		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while inserting the address " + e.getMessage(), e);

		}
	}

	/**
	 * Deletes a user address from the database by ID.
	 *
	 * @param id The ID of the user address to be deleted.
	 * @return true if the deletion was successful, false otherwise.
	 * @throws DAOException If there is an error while performing the database
	 *                      operation.
	 */

	public boolean updateById(UserAddress address) throws DAOException {
		String sql = "UPDATE user_address SET full_name = ?, full_address = ?, pincode = ?, phone_number = ? WHERE id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, address.getFullName());
			pst.setString(2, address.getAddress());
			pst.setString(3, address.getPincode());
			pst.setString(4, address.getPhoneNumber());
			pst.setInt(5, address.getId());

			int row = pst.executeUpdate();

			return row > 0;
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while updating the address by ID: " + e.getMessage(), e);
		}
	}

	/**
	 * Deletes a user address from the database by ID.
	 *
	 * @param id The ID of the user address to be deleted.
	 * @return true if the deletion was successful, false otherwise.
	 * @throws DAOException If there is an error while performing the database
	 *                      operation.
	 */
	public boolean deleteById(int id) throws DAOException {
		String sql = "DELETE FROM user_address WHERE id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, id);

			int row = pst.executeUpdate();

			return row > 0;
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while deleting the address by ID: " + e.getMessage(), e);
		}
	}

	/**
	 * Retrieves a list of all user addresses for a specific user (user_id) from the
	 * database.
	 *
	 * @param userId The ID of the user for whom to retrieve addresses.
	 * @return A List of UserAddress objects representing all addresses for the
	 *         specified user.
	 * @throws DAOException If there is an error while performing the database
	 *                      operation.
	 */
	public List<UserAddress> readAll(int userId) throws DAOException {
		String sql = "SELECT id, user_id, full_name, full_address, pincode, phone_number FROM user_address WHERE user_id = ?";
		List<UserAddress> addresses = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, userId);
			try (ResultSet resultSet = pst.executeQuery()) {
				while (resultSet.next()) {
					UserAddress address = new UserAddress();
					address.setId(resultSet.getInt("id"));
					address.setFullName(resultSet.getString("full_name"));
					address.setAddress(resultSet.getString("full_address"));
					address.setPincode(resultSet.getString("pincode"));
					address.setPhoneNumber(resultSet.getString("phone_number"));
					addresses.add(address);
				}
			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while reading user addresses by user ID: " + e.getMessage(), e);
		}

		return addresses;
	}

	/**
	 * Checks if a user address with the specified ID exists in the database.
	 *
	 * @param addressId The ID of the user address to be checked.
	 * @return true if the user address exists, false otherwise.
	 * @throws DAOException If there is an error while performing the database
	 *                      operation.
	 */
	public boolean isAddressExistsWithID(int addressId) throws DAOException {
		String sql = "SELECT COUNT(*) FROM user_address WHERE id = ?";
		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, addressId);
			try (ResultSet resultSet = pst.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while checking address existence by ID: " + e.getMessage(), e);
		}
		return false;
	}

	/**
	 * Retrieves a user address from the database by its unique ID.
	 *
	 * @param addressId The ID of the user address to retrieve.
	 * @return A UserAddress object representing the address with the specified ID,
	 *         or null if the address does not exist.
	 * @throws DAOException If there is an error while performing the database
	 *                      operation.
	 */
	public UserAddress getAddressById(int addressId) throws DAOException {
		// SQL query to retrieve an address by its ID
		String sql = "SELECT id,full_name, full_address, pincode, phone_number FROM user_address WHERE id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, addressId);
			try (ResultSet resultSet = pst.executeQuery()) {
				if (resultSet.next()) {
					// Create a UserAddress object and populate its fields from the query result
					UserAddress address = new UserAddress();
					address.setId(resultSet.getInt("id"));
					address.setFullName(resultSet.getString("full_name"));
					address.setAddress(resultSet.getString("full_address"));
					address.setPincode(resultSet.getString("pincode"));
					address.setPhoneNumber(resultSet.getString("phone_number"));

					return address; // Return the retrieved UserAddress object
				}
			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while retrieving address by ID: " + e.getMessage(), e);
		}

		return null; // Return null if the address with the specified ID does not exist
	}

}
