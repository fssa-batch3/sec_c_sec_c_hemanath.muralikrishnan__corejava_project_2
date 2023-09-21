package com.fssa.agrokart.model;

/**
 * Represents a user address with various details such as full name, address,
 * pincode, and phone number.
 */
public class UserAddress {

	/**
	 * Unique identifier for the user address.
	 */
	private int id;

	private int userId;

	/**
	 * The full address including street, city, etc.
	 */
	private String address;

	/**
	 * The full name of the user associated with this address.
	 */
	private String fullName;

	/**
	 * The postal code or ZIP code of the address.
	 */
	private String pincode;

	/**
	 * The phone number associated with this address.
	 */
	private String phoneNumber;

	/**
	 * Default constructor for UserAddress.
	 */
	public UserAddress() {
		// Default constructor
	}

	/**
	 * Constructs a UserAddress with the specified parameters.
	 *
	 * @param id          The unique identifier for the user address.
	 * @param address     The full address including street, city, etc.
	 * @param fullName    The full name of the user associated with this address.
	 * @param pincode     The postal code or ZIP code of the address.
	 * @param phoneNumber The phone number associated with this address.
	 */
	public UserAddress(int id, String address, String fullName, String pincode, String phoneNumber) {
		this.id = id;
		this.address = address;
		this.fullName = fullName;
		this.pincode = pincode;
		this.phoneNumber = phoneNumber;
	}

	// Other getters and setters...
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", userId=" + userId + ", address=" + address + ", fullName=" + fullName
				+ ", pincode=" + pincode + ", phoneNumber=" + phoneNumber + "]";
	}

	/**
	 * Returns a string representation of the UserAddress object.
	 *
	 * @return A string representation including all fields.
	 */

}
