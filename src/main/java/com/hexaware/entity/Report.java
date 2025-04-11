package com.hexaware.entity;

public class Report {
	private int reportID;
	private int incidentID;
	private int reportingOfficer;
	private String reportDate;
	private String reportDetails;
	private String status;
	
	// Default constructor
	
	public Report() {
		super();
	}
	
	// Parameterized constructor

	public Report(int reportID, int incidentID, int reportingOfficer, String reportDate, String reportDetails,
			String status) {
		super();
		this.reportID = reportID;
		this.incidentID = incidentID;
		this.reportingOfficer = reportingOfficer;
		this.reportDate = reportDate;
		this.reportDetails = reportDetails;
		this.status = status;
	}
	
	@Override
	public String toString() {
	    return "incidentID=" + incidentID +
	            ", reportingOfficer=" + reportingOfficer +
	            ", reportDate='" + reportDate + '\'' +
	            ", details='" + reportDetails + '\'' +
	            ", status='" + status + '\'' +
	            '}';
	}

	
	// Getters and setters

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public int getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(int incidentID) {
		this.incidentID = incidentID;
	}

	public int getReportingOfficer() {
		return reportingOfficer;
	}

	public void setReportingOfficer(int reportingOfficer) {
		this.reportingOfficer = reportingOfficer;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportDetails() {
		return reportDetails;
	}

	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
