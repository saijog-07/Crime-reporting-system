package com.hexaware.dao;

import java.util.Collection;

import com.hexaware.entity.Incident;
import com.hexaware.entity.Report;

import java.time.LocalDate;

import com.hexaware.myexceptions.IncidentNumberNotFoundException;


public interface ICrimeAnalysisService {

	
	boolean createIncident(Incident incident);
	
	
	boolean updateIncidentStatus(String status, int incidentID) throws IncidentNumberNotFoundException;

	
	Collection<Incident> getIncidentsInDateRange(LocalDate startDate, LocalDate endDate);
	
	
	Collection<Incident> searchIncidents(String incidentType);

	
	Report generateIncidentReport(Incident incident);
}
