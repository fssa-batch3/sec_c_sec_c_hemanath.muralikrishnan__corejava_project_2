package com.fssa.agrokart.model;

/**
 * This class contains regular expression patterns for validating user input.
 */
public final class UserRegexPatterns {

	/**
	 * Regular expression pattern for validating a user's first name. The name must
	 * consist of 3 to 20 alphabetic characters.
	 */
	public static final String FIRST_NAME_PATTERN = "^[A-Za-z]{3,20}$";

	/**
	 * Regular expression pattern for validating a user's last name. The name must
	 * consist of 1 to 20 alphabetic characters.
	 */
	public static final String LAST_NAME_PATTERN = "^[A-Za-z]{1,20}$";

	/**
	 * Regular expression pattern for validating an email address. It follows a
	 * common email address format.
	 */
	public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]{2,}[.][a-zA-Z]{2,4}$";

	/**
	 * Regular expression pattern for validating a phone number. It must start with
	 * a digit from 6 to 9 and be followed by 9 more digits.
	 */
	public static final String PHONE_NUMBER_PATTERN = "^[6-9][0-9]{9}$";

	/**
	 * Regular expression pattern for validating a password. The password must be 8
	 * to 16 characters long and include at least one lowercase letter, one
	 * uppercase letter, one digit, and one special character from the set:
	 * !@#$%^&*_=+-.
	 */
	public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$";

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private UserRegexPatterns() {
		// Private constructor to prevent instantiation.
	}
}
