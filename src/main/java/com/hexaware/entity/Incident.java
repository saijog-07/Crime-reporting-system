package com.hexaware.entity;

public class Incident {

	private int incidentID;
	private String incidentType;
	private String incidentDate;
	private double latitude;
	private double longitude;
	private String description;
	private String status;
	private int victimID;
	private int suspectID;
	private int officerID;
	
	// Default constructor

	public Incident() {
		super();
	}
	
	// Parameterized constructor

	public Incident(int incidentID, String incidentType, String incidentDate, double latitude, double longitude,
			String description, String status, int victimID, int suspectID, int officerID) {
		super();
		this.incidentID = incidentID;
		this.incidentType = incidentType;
		this.incidentDate = incidentDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.status = status;
		this.victimID = victimID;
		this.suspectID = suspectID;
		this.officerID = officerID;
	}
	
	@Override
	public String toString() {
	    return "incidentID=" + incidentID +
	            ", type='" + incidentType + '\'' +
	            ", date='" + incidentDate + '\'' +
	            ", lat=" + latitude +
	            ", long=" + longitude +
	            ", description='" + description + '\'' +
	            ", status='" + status + '\'' +
	            ", victimID=" + victimID +
	            ", suspectID=" + suspectID +
	            ", officerID=" + officerID +
	            '}';
	}

	
	// Getters and setters

	public int getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(int incidentID) {
		this.incidentID = incidentID;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}

	public String getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(String incidentDate) {
		this.incidentDate = incidentDate;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getVictimID() {
		return victimID;
	}

	public void setVictimID(int victimID) {
		this.victimID = victimID;
	}

	public int getSuspectID() {
		return suspectID;
	}

	public void setSuspectID(int suspectID) {
		this.suspectID = suspectID;
	}

	public int getOfficerID() {
		return officerID;
	}

	public void setOfficerID(int officerID) {
		this.officerID = officerID;
	}
	
	private String tempReportDate;
	private String tempReportDetails;
	private String tempReportStatus;

	public String getTempReportDate() { 
		return tempReportDate; 
		}
	
	public void setTempReportDate(String tempReportDate) { 
		this.tempReportDate = tempReportDate; 
		}

	public String getTempReportDetails() { 
		return tempReportDetails; 
	}
	
	public void setTempReportDetails(String tempReportDetails) { 
		this.tempReportDetails = tempReportDetails; 
	}

	public String getTempReportStatus() { 
		return tempReportStatus; 
	}
	public void setTempReportStatus(String tempReportStatus) { 
		this.tempReportStatus = tempReportStatus; 
	}
	
	
	
	

	
	
	
}


