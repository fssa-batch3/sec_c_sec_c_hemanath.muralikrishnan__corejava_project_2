package com.fssa.agrokart.validator;

/**
 * This class contains error messages used for user input validation.
 */
public final class UserValidatorErrors {

	/**
	 * Error message for when the first name is null or empty.
	 */
	public static final String FIRST_NAME_NULL = "First name cannot be null or empty";

	/**
	 * Error message for when the first name does not match the required pattern.
	 */
	public static final String FIRST_NAME_PATTERN = "First name must consist of 3 to 20 alphabetic characters";

	/**
	 * Error message for when the last name is null or empty.
	 */
	public static final String LAST_NAME_NULL = "Last name cannot be null or empty";

	/**
	 * Error message for when the last name does not match the required pattern.
	 */
	public static final String LAST_NAME_PATTERN = "Last name must consist of 1 to 20 alphabetic characters";

	/**
	 * Error message for when the email address is null or empty.
	 */
	public static final String EMAIL_ID_NULL = "Email address cannot be null or empty";

	/**
	 * Error message for when the email address has an invalid format.
	 */
	public static final String EMAIL_ID_PATTERN = "Invalid email address format";

	/**
	 * Error message for when the password is null or empty.
	 */
	public static final String PASSWORD_NULL = "Password cannot be null or empty";

	/**
	 * Error message for when the password does not match the required pattern.
	 */
	public static final String PASSWORD_PATTERN = "Password must be 8 to 16 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character (!@#$%^&*_=+-)";

	/**
	 * Error message for when the mobile number is null or empty.
	 */
	public static final String MOBILE_NUMBER_NULL = "Mobile number cannot be null or empty";

	/**
	 * Error message for when the mobile number has an invalid format.
	 */
	public static final String MOBILE_NUMBER_PATTERN = "Invalid mobile number format";

	/**
	 * Error message for when the gender is null.
	 */
	public static final String GENDER_NULL = "Gender cannot be null";

	/**
	 * Error message for when the gender value is invalid.
	 */
	public static final String INVALID_GENDER = "Invalid gender value";

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private UserValidatorErrors() {
		// Private constructor to prevent instantiation.
	}
}
