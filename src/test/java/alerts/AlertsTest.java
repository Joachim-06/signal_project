package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alerts.*;

public class AlertsTest {
    
    @Test
    public void testBloodPressureAlert() {
        String patientId = "1";
        String condition = "Blood pressure alert";
        int timestamp = 111;

        BloodPressureAlert alert = new BloodPressureAlert(patientId, condition, timestamp);

        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());
    }

    @Test
    public void testBloodOxygenAlert() {
        String patientId = "1";
        String condition = "Blood oxygen alert";
        int timestamp = 111;

        BloodOxygenAlert alert = new BloodOxygenAlert(patientId, condition, timestamp);

        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());
    }

    @Test
    public void testECGAlert() {
        String patientId = "1";
        String condition = "ECG alert";
        int timestamp = 111;

        ECGAlert alert = new ECGAlert(patientId, condition, timestamp);

        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());
    }
}
