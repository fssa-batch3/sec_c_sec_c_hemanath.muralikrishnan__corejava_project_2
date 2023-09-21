package com.fssa.agrokart.model;

/**
 * This class contains regular expression patterns for validating user address
 * fields.
 */
public final class UserAddressRegexPatterns {

	/**
	 * Regular expression pattern for validating Full Name.
	 * 
	 * <p>
	 * Pattern Explanation: - ^(?!.*\\s\\s): Ensures that there are no consecutive
	 * spaces. - [A-Za-z ]{3,20}: Matches alphabets and spaces with a length between
	 * 3 and 20 characters. - (?:\\s[A-Za-z ]{1,20})*: Allows additional words
	 * separated by spaces (up to 20 characters each).
	 * </p>
	 */
	public static final String FULL_NAME_PATTERN = "^(?!.*\\s\\s)[A-Za-z ]{3,20}(?:\\s[A-Za-z ]{1,20})*";

	/**
	 * Regular expression pattern for validating Full Address.
	 * 
	 * <p>
	 * Pattern Explanation: - [a-zA-Z0-9\\s\\-\\,\\#]{10,255}: Matches letters,
	 * numbers, spaces, hyphens, commas, and hash symbols with a length between 10
	 * and 255 characters.
	 * </p>
	 */
	public static final String FULL_ADDRESS_PATTERN = "^[a-zA-Z0-9\\s\\-\\,\\#\\.\\'\\/]{10,255}$";

	/**
	 * Regular expression pattern for validating PIN Code.
	 * 
	 * <p>
	 * Pattern Explanation: - ^[6][0-9]{5}$: Matches a PIN Code that starts with '6'
	 * and is followed by exactly 5 digits.
	 * </p>
	 */
	public static final String PINCODE_PATTERN = "^[6][0-9]{5}$";

	private UserAddressRegexPatterns() {

	}
}
