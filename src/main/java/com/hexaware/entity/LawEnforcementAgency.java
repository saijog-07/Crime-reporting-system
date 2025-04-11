package com.hexaware.entity;

public class LawEnforcementAgency {
	private int agencyID;
	private String agencyName;
	private String jurisdiction;
	private String contactInfo;
	
	// Default constructor
	
	public LawEnforcementAgency() {
		super();
	}
	
	// Parameterized constructor

	public LawEnforcementAgency(int agencyID, String agencyName, String jurisdiction, String contactInfo) {
		super();
		this.agencyID = agencyID;
		this.agencyName = agencyName;
		this.jurisdiction = jurisdiction;
		this.contactInfo = contactInfo;
	}
	
	// Getters and setters

	public int getAgencyID() {
		return agencyID;
	}

	public void setAgencyID(int agencyID) {
		this.agencyID = agencyID;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	
	
	
}
