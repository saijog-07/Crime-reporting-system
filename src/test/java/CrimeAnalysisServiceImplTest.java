import static org.junit.jupiter.api.Assertions.*;

import com.hexaware.entity.Incident;
import com.hexaware.myexceptions.IncidentNumberNotFoundException;
import com.hexaware.dao.CrimeAnalysisServiceImpl;
import com.hexaware.util.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class CrimeAnalysisServiceImplTest {

    private CrimeAnalysisServiceImpl service;

    @BeforeEach
    public void setUp() {
        Connection conn = DBConnection.getConnection();
        service = new CrimeAnalysisServiceImpl(conn);
    }

    // Test 1: Create a valid incident
    @Test
    public void testCreateIncident_Success() {
        Incident incident = new Incident();
        incident.setIncidentType("Burglary");
        incident.setIncidentDate("2025-04-10");
        incident.setLatitude(17.385044);
        incident.setLongitude(78.486671);
        incident.setDescription("Attempted burglary at local store.");
        incident.setStatus("under investigation");
        incident.setVictimID(205);
        incident.setSuspectID(305);
        incident.setOfficerID(105);

        boolean result = service.createIncident(incident);
        if (result) {
            System.out.println("Test Passed: Incident created successfully.");
        } else {
            System.out.println("Test Failed: Incident creation failed.");
        }
        assertTrue(result, "Incident should be successfully created");
    }

    // Test 2: Create an incident with invalid date format
    @Test
    public void testCreateIncident_InvalidDate() {
        Incident incident = new Incident();
        incident.setIncidentType("Vandalism");
        incident.setIncidentDate("10-04-2025"); // Invalid format
        incident.setLatitude(19.2183);
        incident.setLongitude(72.9781);
        incident.setDescription("Graffiti on school wall.");
        incident.setStatus("under investigation");
        incident.setVictimID(210);
        incident.setSuspectID(310);
        incident.setOfficerID(110);

        boolean result = service.createIncident(incident);
        if (!result) {
            System.out.println("Test Passed: Incident creation failed due to invalid date format.");
        } else {
            System.out.println("Test Failed: Incident created with invalid date format.");
        }
        assertFalse(result, "Incident creation should fail due to invalid date");
    }

    // Test 3: Update status of a valid incident
    @Test
    public void testUpdateIncidentStatus_Success() throws IncidentNumberNotFoundException {
        boolean result = service.updateIncidentStatus("closed", 401);
        if (result) {
            System.out.println("Test Passed: Incident status updated successfully.");
        } else {
            System.out.println("Test Failed: Failed to update incident status.");
        }
        assertTrue(result, "Status should be updated for valid incident ID");
    }

    // Test 4: Update status of an invalid incident ID
    @Test
    public void testUpdateIncidentStatus_InvalidID() {
        try {
            service.updateIncidentStatus("closed", 9999);
            System.out.println("Test Failed: Expected exception for invalid incident ID was not thrown.");
            fail("Expected IncidentNumberNotFoundException");
        } catch (IncidentNumberNotFoundException e) {
            System.out.println("Test Passed: Correctly threw IncidentNumberNotFoundException for invalid ID 9999.");
        }
    }
}
