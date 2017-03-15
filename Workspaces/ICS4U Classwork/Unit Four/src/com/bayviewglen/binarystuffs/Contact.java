package com.bayviewglen.binarystuffs;

import java.io.FileWriter;
import java.io.IOException;

//A contact class, which implements comparable and stores the phone, last name, and first name of a contact in the contactbook

public class Contact implements Comparable {
	private String lastName;
	private String firstName;
	private String phone;

	// constructor method takes in the first name, last name, and phone of the
	// contact
	public Contact(String fname, String lname, String phone) {
		this.firstName = fname;
		this.lastName = lname;
		this.phone = phone;
	}

	// setter for first name
	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	// getter for first name
	public String getFirstName() {
		return firstName;
	}

	// getter for last name
	public String getLastName() {
		return lastName;
	}

	// setter for last name
	public void setLastName(String lname) {
		this.lastName = lname;
	}

	// getter for phone
	public String getPhone() {
		return phone;
	}

	// setter for phone
	public void setPhone(String phone) {
		this.phone = phone;
	}

	// Returns a printable string of the contact containing the contact's
	// information
	public String toString() {
		return "First Name: " + this.firstName + "\n Last Name: " + this.lastName + "\nPhone Number: " + this.phone
				+ "\n";
	}

	// return the contact as a string in the proper format to be saved to a file
	@Override
	public String toStringForSave() {
		return "~" + this.firstName + "~" + this.lastName + "~" + this.phone;
	}

	// return true if the contact's name or phone contain a substring equal to
	// the phone of the input parameter object
	@Override
	public boolean contactContainsInfo(Comparable object) {
		String input = ((Contact) object).getPhone();
		if (this.firstName.toLowerCase().contains(input.toLowerCase())
				|| this.lastName.toLowerCase().contains(input.toLowerCase())
				|| this.phone.toLowerCase().contains(input.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	// Compare a contact object with this one, based on lexicographical order of
	// the phone number strings
	@Override
	public int compareTo(Comparable object) {
		String phoneNumber = ((Contact) object).getPhone();
		if (this.phone.compareTo(phoneNumber) > 0) {
			return 1;
		} else if (this.phone.compareTo(phoneNumber) < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}