package com.fssa.agrokart.validator;

import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.model.UserAddress;
import com.fssa.agrokart.model.UserAddressRegexPatterns;
import com.fssa.agrokart.util.StringUtil;

/**
 * The UserAddressValidator class provides methods to validate user address
 * information. It ensures that the full name, full address, and PIN code meet
 * the required validation patterns. Additionally, it validates the mobile/phone
 * number using the UserValidator class.
 */
public class UserAddressValidator {

	StringUtil stringUtil = new StringUtil();
	UserValidator userValidator = new UserValidator();

	/**
	 * Validates a UserAddress object, including its full name, full address, PIN
	 * code, and mobile/phone number.
	 *
	 * @param address The UserAddress object to be validated.
	 * @return true if the address is valid; otherwise, false.
	 * @throws InvalidInputException If any validation fails, an
	 *                               InvalidInputException is thrown with a specific
	 *                               error message.
	 */
	public boolean validateUserAddress(UserAddress address) throws InvalidInputException {
		validateFullName(address.getFullName());
		validateFullAddress(address.getAddress());
		validatePinCode(address.getPincode());
		userValidator.validateMobileNumber(address.getPhoneNumber());
		return true;
	}

	/**
	 * Validates a full name based on a specified regex pattern.
	 *
	 * @param name The full name to be validated.
	 * @return true if the full name is valid; otherwise, false.
	 * @throws InvalidInputException If the full name is null or doesn't match the
	 *                               regex pattern, an InvalidInputException is
	 *                               thrown.
	 */
	public boolean validateFullName(String name) throws InvalidInputException {
		String trimmedInput = stringUtil.trimString(name);
		if (trimmedInput == null) {
			throw new InvalidInputException(UserAddressValidatorErrors.FULL_NAME_NULL);
		}
		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, UserAddressRegexPatterns.FULL_NAME_PATTERN);
		if (!isMatch) {
			throw new InvalidInputException(UserAddressValidatorErrors.FULL_NAME_PATTERN);
		}
		return true;
	}

	/**
	 * Validates a full address based on a specified regex pattern.
	 *
	 * @param address The full address to be validated.
	 * @return true if the full address is valid; otherwise, false.
	 * @throws InvalidInputException If the full address is null or doesn't match
	 *                               the regex pattern, an InvalidInputException is
	 *                               thrown.
	 */
	public boolean validateFullAddress(String address) throws InvalidInputException {
		String trimmedInput = stringUtil.trimString(address);
		if (trimmedInput == null) {
			throw new InvalidInputException(UserAddressValidatorErrors.FULL_ADDRESS_NULL);
		}
		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, UserAddressRegexPatterns.FULL_ADDRESS_PATTERN);
		if (!isMatch) {
			throw new InvalidInputException(UserAddressValidatorErrors.FULL_ADDRESS_PATTERN);
		}
		return true;
	}

	/**
	 * Validates a PIN code based on a specified regex pattern.
	 *
	 * @param pincode The PIN code to be validated.
	 * @return true if the PIN code is valid; otherwise, false.
	 * @throws InvalidInputException If the PIN code is null or doesn't match the
	 *                               regex pattern, an InvalidInputException is
	 *                               thrown.
	 */
	public boolean validatePinCode(String pincode) throws InvalidInputException {
		String trimmedInput = stringUtil.trimString(pincode);
		if (trimmedInput == null) {
			throw new InvalidInputException(UserAddressValidatorErrors.PINCODE_NULL);
		}
		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, UserAddressRegexPatterns.PINCODE_PATTERN);
		if (!isMatch) {
			throw new InvalidInputException(UserAddressValidatorErrors.PINCODE_PATTERN);
		}
		return true;
	}
}
