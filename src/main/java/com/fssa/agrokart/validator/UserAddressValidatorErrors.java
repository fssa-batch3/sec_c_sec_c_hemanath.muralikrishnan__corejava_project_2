package com.fssa.agrokart.validator;

/**
 * This class defines error messages for user address validation.
 */
public class UserAddressValidatorErrors {

	/**
	 * Error message for a null or empty full name.
	 */
	public static final String FULL_NAME_NULL = "Full Name cannot be null or empty.";

	/**
	 * Error message for an invalid full name format.
	 * <p>
	 * The full name should contain only alphabets, spaces, and no consecutive
	 * spaces, with a length between 3 and 20 characters.
	 */
	public static final String FULL_NAME_PATTERN = "Full Name is not in the valid format. It should contain only alphabets, spaces, and no consecutive spaces, with a length between 3 and 20 characters.";

	/**
	 * Error message for a null or empty full address.
	 */
	public static final String FULL_ADDRESS_NULL = "Full Address cannot be null or empty.";

	/**
	 * Error message for an invalid full address format.
	 * <p>
	 * The full address should contain only letters, numbers, spaces, hyphens,
	 * commas, and hash symbols, with a length between 10 and 255 characters.
	 */
	public static final String FULL_ADDRESS_PATTERN = "Full Address is not in the valid format. It should contain only letters, numbers, spaces, hyphens, commas, and hash symbols, with a length between 10 and 255 characters.";

	/**
	 * Error message for a null or empty PIN code.
	 */
	public static final String PINCODE_NULL = "PIN Code cannot be null or empty.";

	/**
	 * Error message for an invalid PIN code format.
	 * <p>
	 * The PIN code should start with '6' and be followed by exactly 5 digits.
	 */
	public static final String PINCODE_PATTERN = "PIN Code is not in the valid format. It should start with '6' and be followed by exactly 5 digits.";

	private UserAddressValidatorErrors() {
		// Private constructor to prevent instantiation of this utility class.
	}
}
