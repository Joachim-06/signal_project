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


        List<PatientRecord> records1 = patient.getRecords(11000, 18000);
        List<PatientRecord> records2 = patient.getRecords(0000, 5000);
        List<PatientRecord> records3 = patient.getRecords(20000, 21000);
        List<PatientRecord> records4 = patient.getRecords(20000, 20000);


        assertEquals(3, records1.size());
        assertEquals(0, records2.size());
        assertEquals(1, records3.size());
        assertEquals(1, records4.size());

    }
}
