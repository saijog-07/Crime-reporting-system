package com.hexaware.main;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.Collection;

import com.hexaware.dao.CrimeAnalysisServiceImpl;
import com.hexaware.entity.Incident;
import com.hexaware.entity.Report;
import com.hexaware.myexceptions.IncidentNumberNotFoundException;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CrimeAnalysisServiceImpl service = new CrimeAnalysisServiceImpl();

        int choice;
        do {
            System.out.println("\n====== Crime Analysis System ======");
            System.out.println("1. Create Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. View Incidents in Date Range");
            System.out.println("4. Search Incidents by Type");
            System.out.println("5. Generate Report for Incident");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            try {
                switch (choice) {
                    case 1:
                        Incident incident = new Incident();
                        System.out.print("Enter Incident Type: ");
                        incident.setIncidentType(sc.nextLine());

                        System.out.print("Enter Incident Date (yyyy-mm-dd): ");
                        incident.setIncidentDate(sc.nextLine());

                        System.out.print("Enter Latitude: ");
                        incident.setLatitude(sc.nextDouble());

                        System.out.print("Enter Longitude: ");
                        incident.setLongitude(sc.nextDouble());
                        sc.nextLine(); // consume newline

                        System.out.print("Enter Description: ");
                        incident.setDescription(sc.nextLine());

                        System.out.print("Enter Status: ");
                        incident.setStatus(sc.nextLine());

                        System.out.print("Enter Victim ID: ");
                        incident.setVictimID(sc.nextInt());

                        System.out.print("Enter Suspect ID: ");
                        incident.setSuspectID(sc.nextInt());

                        System.out.print("Enter Officer ID: ");
                        incident.setOfficerID(sc.nextInt());

                        boolean created = service.createIncident(incident);
                        System.out.println(created ? "Incident created successfully." : "Failed to create incident.");
                        break;

                    case 2:
                        System.out.print("Enter Incident ID to update: ");
                        int incidentId = sc.nextInt();
                        sc.nextLine(); // consume newline

                        System.out.print("Enter new status: ");
                        String newStatus = sc.nextLine();

                        boolean updated = service.updateIncidentStatus(newStatus, incidentId);
                        if (updated) {
                            System.out.println("Incident updated successfully.");
                        } else {
                            System.out.println("Incident not found or update failed.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Start Date (yyyy-mm-dd): ");
                        LocalDate start = LocalDate.parse(sc.nextLine());

                        System.out.print("Enter End Date (yyyy-mm-dd): ");
                        LocalDate end = LocalDate.parse(sc.nextLine());

                        Collection<Incident> incidentsInRange = service.getIncidentsInDateRange(start, end);
                        if (incidentsInRange.isEmpty()) {
                            System.out.println("No incidents found in the given date range.");
                        } else {
                            incidentsInRange.forEach(System.out::println);
                        }
                        break;

                    case 4:
                        System.out.print("Enter Incident Type to search: ");
                        String type = sc.nextLine();

                        Collection<Incident> searchResults = service.searchIncidents(type);
                        if (searchResults.isEmpty()) {
                            System.out.println("No incidents found of type: " + type);
                        } else {
                            searchResults.forEach(System.out::println);
                        }
                        break;

                    case 5:
                        System.out.print("Enter Incident ID to generate report: ");
                        int reportIncidentId = sc.nextInt();
                        sc.nextLine(); // consume newline

                        Incident selected = service.getIncidentById(reportIncidentId);
                        if (selected != null) {
                            // Take report input here and set values in the incident
                            System.out.print("Enter Report Date (YYYY-MM-DD): ");
                            String reportDate = sc.nextLine();

                            System.out.print("Enter Report Details: ");
                            String reportDetails = sc.nextLine();

                            System.out.print("Enter Report Status (e.g., draft/finalized): ");
                            String reportStatus = sc.nextLine();

                            // Set temporarily into Incident for transfer
                            selected.setTempReportDate(reportDate);
                            selected.setTempReportDetails(reportDetails);
                            selected.setTempReportStatus(reportStatus);

                            Report report = service.generateIncidentReport(selected);
                            if (report != null) {
                                System.out.println("Report successfully generated and added to database.");
                                System.out.println(report);
                            } else {
                                System.out.println("Failed to generate and store report.");
                            }
                        } else {
                            throw new IncidentNumberNotFoundException("Incident ID " + reportIncidentId + " not found.");
                        }
                        break;

                    case 6:
                        System.out.println("Exiting application.");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (IncidentNumberNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected Error: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }
}
