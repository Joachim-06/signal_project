package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.data_management.Patient;
import com.data_management.PatientRecord;

public class PatientTest {
    
    @Test
    public void testGetRecords() {
        Patient patient = new Patient(1);
        patient.addRecord(110, "HeartRate", 10000);
        patient.addRecord(116, "HeartRate", 12000);
        patient.addRecord(106, "HeartRate", 13500);
        patient.addRecord(98, "HeartRate", 16000);
        patient.addRecord(108, "HeartRate", 20000);


        List<PatientRecord> records = patient.getRecords(11000, 18000);
        assertEquals(3, records.size());
    }
}
