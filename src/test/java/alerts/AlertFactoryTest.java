package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.*;

public class AlertFactoryTest {
    @Test
    public void testBloodPressureFactoryCreateAlert() {
        AlertFactory alertFactory = new BloodPressureAlertFactory();

        String patientId = "1";
        String condition = "Blood pressure alert";
        int timestamp = 111;
        Alert alert = alertFactory.createAlert(patientId, condition, timestamp);

        
        assertTrue(alert instanceof BloodPressureAlert);

        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());

    }

    @Test
    public void testBloodOxygenFactoryCreateAlert() {
        AlertFactory alertFactory = new BloodOxygenAlertFactory();

        String patientId = "1";
        String condition = "Blood oxygen alert";
        int timestamp = 111;
        Alert alert = alertFactory.createAlert(patientId, condition, timestamp);

        
        assertTrue(alert instanceof BloodOxygenAlert);

        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());

    }

    @Test
    public void testECGFactoryCreateAlert() {
        AlertFactory alertFactory = new ECGAlertFactory();

        String patientId = "1";
        String condition = "ECG alert";
        int timestamp = 111;
        Alert alert = alertFactory.createAlert(patientId, condition, timestamp);

        
        assertTrue(alert instanceof ECGAlert);

        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());

    }
}
