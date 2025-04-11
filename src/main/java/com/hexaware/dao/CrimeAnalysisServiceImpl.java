package com.hexaware.dao;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.hexaware.entity.Incident;
import com.hexaware.entity.Report;
import com.hexaware.util.DBConnection;
import com.hexaware.myexceptions.IncidentNumberNotFoundException;



public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

    private static Connection connection;

    public CrimeAnalysisServiceImpl() {
        connection = DBConnection.getConnection();
    }

    public CrimeAnalysisServiceImpl(Connection conn) {
    	connection = conn;
	}

    @Override
    public boolean createIncident(Incident incident) {
        String query = "INSERT INTO Incidents (incidentType, incidentDate, latitude, longitude, description, status, victimID, suspectID, officerID) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Check date format strictly
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(incident.getIncidentDate(), formatter);
            ps.setString(1, incident.getIncidentType());
            ps.setDate(2, Date.valueOf(date));
            ps.setDouble(3, incident.getLatitude());
            ps.setDouble(4, incident.getLongitude());
            ps.setString(5, incident.getDescription());
            ps.setString(6, incident.getStatus());
            ps.setInt(7, incident.getVictimID());
            ps.setInt(8, incident.getSuspectID());
            ps.setInt(9, incident.getOfficerID());

            return ps.executeUpdate() > 0;

        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format. Expected yyyy-MM-dd. Got: " + incident.getIncidentDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateIncidentStatus(String status, int incidentId) throws IncidentNumberNotFoundException {
        String query = "UPDATE Incidents SET status = ? WHERE incidentID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, incidentId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new IncidentNumberNotFoundException("Incident ID " + incidentId + " not found in database.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<Incident> getIncidentsInDateRange(LocalDate startDate, LocalDate endDate) {
        Collection<Incident> incidents = new ArrayList<>();
        String query = "SELECT * FROM Incidents WHERE incidentDate BETWEEN ? AND ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                incidents.add(mapResultSetToIncident(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    @Override
    public Collection<Incident> searchIncidents(String incidentType) {
        Collection<Incident> incidents = new ArrayList<>();
        String query = "SELECT * FROM Incidents WHERE incidentType = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, incidentType);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                incidents.add(mapResultSetToIncident(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    @Override
    public Report generateIncidentReport(Incident incident) {
        String insertQuery = "INSERT INTO Reports (IncidentID, ReportingOfficer, ReportDate, ReportDetails, Status) VALUES (?, ?, ?, ?, ?)";
        Report report = new Report();

        try (PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, incident.getIncidentID());
            ps.setInt(2, incident.getOfficerID());
            ps.setString(3, incident.getTempReportDate());
            ps.setString(4, incident.getTempReportDetails());
            ps.setString(5, incident.getTempReportStatus());

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedReportID = generatedKeys.getInt(1);

                    report.setReportID(generatedReportID);
                    report.setIncidentID(incident.getIncidentID());
                    report.setReportingOfficer(incident.getOfficerID());
                    report.setReportDate(incident.getTempReportDate());
                    report.setReportDetails(incident.getTempReportDetails());
                    report.setStatus(incident.getTempReportStatus());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return report;
    }

    public Incident getIncidentById(int id) {
        String query = "SELECT * FROM Incidents WHERE incidentID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToIncident(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Incident mapResultSetToIncident(ResultSet rs) throws SQLException {
        return new Incident(
            rs.getInt("incidentID"),
            rs.getString("incidentType"),
            rs.getDate("incidentDate").toLocalDate().toString(), // convert to LocalDate then to String
            rs.getDouble("latitude"),
            rs.getDouble("longitude"),
            rs.getString("description"),
            rs.getString("status"),
            rs.getInt("victimID"),
            rs.getInt("suspectID"),
            rs.getInt("officerID")
        );
    }
}
