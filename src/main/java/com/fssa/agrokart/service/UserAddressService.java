package com.fssa.agrokart.service;

import java.util.List;

import com.fssa.agrokart.dao.UserAddressDAO;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.exception.ServiceException;
import com.fssa.agrokart.model.UserAddress;
import com.fssa.agrokart.util.ExceptionLoggerUtil;
import com.fssa.agrokart.validator.UserAddressValidator;

public class UserAddressService {

	UserAddressValidator addressValidator = new UserAddressValidator();
	UserAddressDAO userAddressDAO = new UserAddressDAO();

	/**
	 * Adds a new user address to the database.
	 *
	 * @param address The UserAddress object representing the address to be added.
	 * @return true if the address is added successfully, false otherwise.
	 * @throws ServiceException If there is an error while performing the service
	 *                          operation.
	 */
	public boolean addAddress(UserAddress address) throws ServiceException {
		try {
			if (addressValidator.validateUserAddress(address)) {
				return userAddressDAO.addAddress(address);
			}
		} catch (InvalidInputException | DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	/**
	 * Updates an existing user address in the database.
	 *
	 * @param address The UserAddress object representing the updated address.
	 * @return true if the address is updated successfully, false otherwise.
	 * @throws ServiceException If there is an error while performing the service
	 *                          operation.
	 */
	public boolean updateAddress(UserAddress address) throws ServiceException {
		try {
			if (addressValidator.validateUserAddress(address)) {
				if (userAddressDAO.isAddressExistsWithID(address.getId())) {
					return userAddressDAO.updateById(address);
				}
			}
		} catch (InvalidInputException | DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	/**
	 * Deletes a user address from the database based on its ID.
	 *
	 * @param id The ID of the address to be deleted.
	 * @return true if the address is deleted successfully, false otherwise.
	 * @throws ServiceException If there is an error while performing the service
	 *                          operation.
	 */
	public boolean deleteAddress(int id) throws ServiceException {
		try {
			if (userAddressDAO.isAddressExistsWithID(id)) {
				return userAddressDAO.deleteById(id);
			}
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	/**
	 * Retrieves a list of user addresses for a specific user from the database.
	 *
	 * @param userId The ID of the user for whom to retrieve addresses.
	 * @return A List of UserAddress objects representing addresses for the
	 *         specified user.
	 * @throws ServiceException If there is an error while performing the service
	 *                          operation.
	 */
	public List<UserAddress> readAllAddress(int userId) throws ServiceException {
		try {
			return userAddressDAO.readAll(userId);
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a user address from the database by its unique ID.
	 *
	 * @param id The ID of the user address to retrieve.
	 * @return A UserAddress object representing the address with the specified ID,
	 *         or null if the address does not exist.
	 * @throws ServiceException If there is an error while retrieving the address or
	 *                          if the DAO layer encounters an exception.
	 */
	public UserAddress getAddressById(int id) throws ServiceException {
		try {
			// Check if the address with the specified ID exists in the database
			if (userAddressDAO.isAddressExistsWithID(id)) {
				// Retrieve the address from the DAO layer
				return userAddressDAO.getAddressById(id);
			}
		} catch (DAOException e) {
			// Log any DAO-related exceptions and rethrow them as ServiceException
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}

		return null; // Return null if the address with the specified ID does not exist
	}

}
