/**
 * 
 */
package com.fssa.agrokart.model;

import java.util.List;

/**
 * Represents a user in the system.
 */
public class User {

	// Fields
	int id; // The unique identifier of the user.
	String imageUrl; // The URL of the user's profile image.
	String firstName; // The user's first name.
	String lastName; // The user's last name.
	Gender gender; // The user's gender.
	String emailId; // The user's email address.
	String phoneNumber; // The user's phone number.
	String password; // The user's password.
	String userCreatedDateTime; // The date and time when the user account was created.
	List<UserAddress> address; // A list of addresses associated with the user.

	/**
	 * Default constructor for the User class.
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a new User object with the provided information.
	 *
	 * @param firstName   The user's first name.
	 * @param lastName    The user's last name.
	 * @param emailId     The user's email address.
	 * @param phoneNumber The user's phone number.
	 * @param password    The user's password.
	 */
	public User(String firstName, String lastName, String emailId, String phoneNumber, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserCreatedDateTime() {
		return userCreatedDateTime;
	}

	public void setUserCreatedDateTime(String userCreatedDateTime) {
		this.userCreatedDateTime = userCreatedDateTime;
	}

	public List<UserAddress> getAddress() {
		return address;
	}

	public void setAddress(List<UserAddress> address) {
		this.address = address;
	}

	/**
	 * Returns a string representation of the User object.
	 *
	 * @return A string representation of the User object.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", imageUrl=" + imageUrl + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", password="
				+ password + ", userCreatedDateTime=" + userCreatedDateTime + ", address=" + address + "]";
	}

}
