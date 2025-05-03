package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.AlertGenerator;
import com.alerts.BloodPressureStrategy;
import com.alerts.HeartRateStrategy;
import com.alerts.OxygenSaturationStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

public class AlertStrategyTest {
    @Test
    public void testBloodPressureStategyCheckAlert() {

        Patient patient = new Patient(1);

        patient.addRecord(70, "BloodPressure", 20000);
        patient.addRecord(80, "BloodPressure", 20010);
        patient.addRecord(91, "BloodPressure", 20020);
        patient.addRecord(141, "BloodPressure", 20030);

        PatientRecord[] lastBloodPressureRecords = patient.getlastBloodPressureRecords();
        PatientRecord lastBloodPressureRecord = lastBloodPressureRecords[lastBloodPressureRecords.length-1];

        BloodPressureStrategy bloodPressureStrategy = new BloodPressureStrategy(lastBloodPressureRecord, lastBloodPressureRecords);
        Alert[] bloodPressureAlert = bloodPressureStrategy.checkAlert();

        assertEquals("Blood Pressure Alert", bloodPressureAlert[0].getCondition());
        assertEquals("Blood Pressure Alert", bloodPressureAlert[1].getCondition());
    }

    @Test
    public void testOxygenSaturationStategyCheckAlert() {

        Patient patient = new Patient(1);

        patient.addRecord(0.91, "BloodSaturation", 20000);

        PatientRecord lastBloodSaturationRecord = patient.getlastBloodSaturationRecord();
        PatientRecord[] lastBloodPressureRecords = patient.getlastBloodPressureRecords();
        PatientRecord lastBloodPressureRecord = lastBloodPressureRecords[lastBloodPressureRecords.length-1];

        OxygenSaturationStrategy oxygenSaturationStrategy = new OxygenSaturationStrategy(lastBloodSaturationRecord, lastBloodPressureRecord);
        Alert bloodOxygenAlert = oxygenSaturationStrategy.checkAlert()[0];

        assertEquals("Blood Saturation Alert", bloodOxygenAlert.getCondition());
    }

    @Test
    public void testHeartRateStategyCheckAlert() {

        Patient patient = new Patient(1);

        patient.addRecord(80, "ECG", 20000);
        patient.addRecord(75, "ECG", 20000);
        patient.addRecord(78, "ECG", 20000);
        patient.addRecord(82, "ECG", 20000);
        patient.addRecord(119, "ECG", 20000);

        PatientRecord lastECGRecord = patient.getlastECGRecord();

        HeartRateStrategy heartRateStrategy = new HeartRateStrategy(lastECGRecord, patient.getAverageECG());
        Alert ecgAlert = heartRateStrategy.checkAlert()[0];

        assertEquals("ECG Alert", ecgAlert.getCondition());
    }
}
