package com.fssa.agrokart.service;

import com.fssa.agrokart.dao.UserDAO;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.exception.ServiceException;
import com.fssa.agrokart.model.User;
import com.fssa.agrokart.util.ExceptionLoggerUtil;
import com.fssa.agrokart.validator.UserValidator;

/**
 * The UserService class provides methods for user registration, login, checking
 * email or phone number existence, and retrieving user records by email.
 */
public class UserService {

	private UserDAO userDAO = new UserDAO();
	private UserValidator validator = new UserValidator();

	/**
	 * Registers a new user.
	 *
	 * @param user The user to register.
	 * @return true if registration is successful, false otherwise.
	 * @throws ServiceException If an error occurs during registration.
	 */
	public boolean register(User user) throws ServiceException {
		try {
			if (validator.validateUserRegister(user)) {
				return userDAO.registerUser(user);
			}
		} catch (DAOException | InvalidInputException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	/**
	 * Logs in a user with the provided email and password.
	 *
	 * @param email    The user's email.
	 * @param password The user's password.
	 * @return true if login is successful, false otherwise.
	 * @throws ServiceException If an error occurs during login.
	 */
	public boolean login(String email, String password) throws ServiceException {
		try {
			if (validator.validateEmail(email) && validator.validatePassword(password)) {
				if (userDAO.isEmailExists(email) && userDAO.login(email, password)) {
					return true;
				}
			}
		} catch (DAOException | InvalidInputException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	/**
	 * Checks if the provided email or phone number exists in the system.
	 *
	 * @param email    The email to check.
	 * @param password The phone number to check.
	 * @return true if neither the email nor the phone number exists, false
	 *         otherwise.
	 * @throws ServiceException If an error occurs during the check.
	 */
	public boolean isEmailOrPhoneExists(String email, String mobileNumber) throws ServiceException {
		try {
			if (validator.validateEmail(email) && validator.validateMobileNumber(mobileNumber)) {
				return !(userDAO.isEmailExists(email) || userDAO.isPhoneNumberExists(mobileNumber));
			}
		} catch (DAOException | InvalidInputException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return true;
	}

	/**
	 * Retrieves a user record by their email.
	 *
	 * @param email The email of the user to retrieve.
	 * @return The user record if found, or null if not found.
	 * @throws ServiceException If an error occurs during retrieval.
	 */
	public User getUserRecordByEmail(String email) throws ServiceException {
		try {
			if (validator.validateEmail(email) && userDAO.isEmailExists(email)) {
				return userDAO.findUserByEmail(email);
			}
		} catch (DAOException | InvalidInputException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates the user's profile information with the provided User object.
	 *
	 * @param user The User object containing the updated user profile information.
	 * @return true if the user profile was successfully updated; otherwise, false.
	 * @throws ServiceException If there is an issue with validating the update,
	 *                          checking user existence, or updating the user
	 *                          profile in the data access layer.
	 */
	public boolean updateUserProfile(User user) throws ServiceException {
		try {
			// Validate the user profile update
			if (validator.validateUpdateProfile(user)) {
				// Check if a user with the provided ID exists
				if (userDAO.isUserExistsWithID(user.getId())) {
					// Update the user profile in the data access layer
					return userDAO.updateUserProfile(user);
				}
			}
		} catch (DAOException | InvalidInputException e) {
			ExceptionLoggerUtil.logException(e);
			// Handle any exceptions and wrap them in a ServiceException
			throw new ServiceException(e.getMessage());
		}

		// Return false if the update was unsuccessful
		return false;
	}

}
