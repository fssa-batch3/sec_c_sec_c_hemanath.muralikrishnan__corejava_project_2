package com.fssa.agrokart.validator;

import java.util.Arrays;
import java.util.List;
import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.model.Gender;
import com.fssa.agrokart.model.User;
import com.fssa.agrokart.model.UserRegexPatterns;
import com.fssa.agrokart.util.StringUtil;

/**
 * The UserValidator class provides methods to validate user input for user
 * registration.
 */
public class UserValidator {

	private StringUtil stringUtil = new StringUtil();

	/**
	 * Validates the user's registration information.
	 *
	 * @param user The user object containing registration information.
	 * @return true if the validation succeeds; otherwise, false.
	 * @throws InvalidInputException If any of the validation checks fail, an
	 *                               exception is thrown with an error message.
	 */
	public boolean validateUserRegister(User user) throws InvalidInputException {
		validateFirstName(user.getFirstName());
		validateLastName(user.getLastName());
		validateEmail(user.getEmailId());
		validateMobileNumber(user.getPhoneNumber());
		validatePassword(user.getPassword());

		// If there is no exception, then return true
		return true;
	}

	public boolean validateUpdateProfile(User user) throws InvalidInputException {

		validateFirstName(user.getFirstName());
		validateLastName(user.getLastName());
		validateGender(user.getGender());

		// If there is no exception, then return true

		return true;

	}

	/**
	 * Validates the user's first name.
	 *
	 * @param name The first name to be validated.
	 * @return true if the first name is valid.
	 * @throws InvalidInputException If the first name is null, empty, or doesn't
	 *                               match the required pattern, an exception is
	 *                               thrown with an error message.
	 */
	public boolean validateFirstName(String name) throws InvalidInputException {
		String trimmedInput = stringUtil.trimString(name);

		if (trimmedInput == null) {
			throw new InvalidInputException(UserValidatorErrors.FIRST_NAME_NULL);
		}

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, UserRegexPatterns.FIRST_NAME_PATTERN);

		if (!isMatch) {
			throw new InvalidInputException(UserValidatorErrors.FIRST_NAME_PATTERN);
		}

		return true;
	}

	/**
	 * Validates the user's last name.
	 *
	 * @param name The last name to be validated.
	 * @return true if the last name is valid.
	 * @throws InvalidInputException If the last name is null, empty, or doesn't
	 *                               match the required pattern, an exception is
	 *                               thrown with an error message.
	 */
	public boolean validateLastName(String name) throws InvalidInputException {
		String trimmedInput = stringUtil.trimString(name);

		if (trimmedInput == null) {
			throw new InvalidInputException(UserValidatorErrors.LAST_NAME_NULL);
		}

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, UserRegexPatterns.LAST_NAME_PATTERN);

		if (!isMatch) {
			throw new InvalidInputException(UserValidatorErrors.LAST_NAME_PATTERN);
		}

		return true;
	}

	/**
	 * Validates the user's gender.
	 *
	 * @param gender The gender to be validated.
	 * @return true if the gender is valid.
	 * @throws InvalidInputException If the gender is null or not one of the
	 *                               expected values, an exception is thrown with an
	 *                               error message.
	 */
	public boolean validateGender(Gender gender) throws InvalidInputException {
		if (gender == null) {
			throw new InvalidInputException(UserValidatorErrors.GENDER_NULL);
		}

		Object[] arr = Gender.values();
		List<Object> genderList = Arrays.asList(arr);

		if (!genderList.contains(gender)) {
			throw new InvalidInputException(UserValidatorErrors.INVALID_GENDER);
		}

		return true;
	}

	/**
	 * Validates the user's email address.
	 *
	 * @param email The email address to be validated.
	 * @return true if the email address is valid.
	 * @throws InvalidInputException If the email address is null, empty, or doesn't
	 *                               match the required pattern, an exception is
	 *                               thrown with an error message.
	 */
	public boolean validateEmail(String email) throws InvalidInputException {
		String trimmedInput = stringUtil.trimString(email);

		if (trimmedInput == null) {
			throw new InvalidInputException(UserValidatorErrors.EMAIL_ID_NULL);
		}

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, UserRegexPatterns.EMAIL_PATTERN);

		if (!isMatch) {
			throw new InvalidInputException(UserValidatorErrors.EMAIL_ID_PATTERN);
		}

		return true;
	}

	/**
	 * Validates the user's mobile number.
	 *
	 * @param number The mobile number to be validated.
	 * @return true if the mobile number is valid.
	 * @throws InvalidInputException If the mobile number is null, empty, or doesn't
	 *                               match the required pattern, an exception is
	 *                               thrown with an error message.
	 */
	public boolean validateMobileNumber(String number) throws InvalidInputException {
		String trimmedInput = stringUtil.trimString(number);

		if (trimmedInput == null) {
			throw new InvalidInputException(UserValidatorErrors.MOBILE_NUMBER_NULL);
		}

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, UserRegexPatterns.PHONE_NUMBER_PATTERN);

		if (!isMatch) {
			throw new InvalidInputException(UserValidatorErrors.MOBILE_NUMBER_PATTERN);
		}

		return true;
	}

	/**
	 * Validates the user's password.
	 *
	 * @param password The password to be validated.
	 * @return true if the password is valid.
	 * @throws InvalidInputException If the password is null, empty, or doesn't
	 *                               match the required pattern, an exception is
	 *                               thrown with an error message.
	 */
	public boolean validatePassword(String password) throws InvalidInputException {
		String trimmedInput = stringUtil.trimString(password);

		if (trimmedInput == null) {
			throw new InvalidInputException(UserValidatorErrors.PASSWORD_NULL);
		}

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, UserRegexPatterns.PASSWORD_PATTERN);

		if (!isMatch) {
			throw new InvalidInputException(UserValidatorErrors.PASSWORD_PATTERN);
		}

		return true;
	}
}
