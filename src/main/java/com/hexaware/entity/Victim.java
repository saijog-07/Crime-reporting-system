package com.hexaware.entity;

public class Victim {
	private int victimID;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String contactInfo;
	
	// Default constructor
	
	public Victim() {
		super();
	}
	
	// Parameterized constructor
	
	public Victim(int victimID, String firstName, String lastName, String dateOfBirth, String gender, String contactInfo) {
		super();
		this.victimID = victimID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactInfo = contactInfo;
	}

	// Getters and setters
	
	public int getVictimID() {
		return victimID;
	}

	public void setVictimID(int victimID) {
		this.victimID = victimID;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	
	
}
