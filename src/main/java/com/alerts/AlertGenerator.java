package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;
    private AlertFactory alertFactory;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {

        PatientRecord[] lastBloodPressureRecords = patient.getlastBloodPressureRecords();
        PatientRecord lastBloodPressureRecord = lastBloodPressureRecords[lastBloodPressureRecords.length-1];
        PatientRecord lastBloodSaturationRecord = patient.getlastBloodSaturationRecord();
        PatientRecord lastECGRecord = patient.getlastECGRecord();

        //____________________Blood pressure alerts_________________

        BloodPressureStrategy bloodPressureStrategy = new BloodPressureStrategy(lastBloodPressureRecord, lastBloodPressureRecords);
        Alert[] bloodPressureAlert = bloodPressureStrategy.checkAlert();
        // checks if bloodPressureStrategy.checkAlert() found an alert
        // if yes => alert is triggered
        for (Alert alert: bloodPressureAlert) {
            if (alert!=null) {
                triggerAlert(alert);
            }
        }

        //_________Blood saturation alerts and Combined alerts_____

        OxygenSaturationStrategy oxygenSaturationStrategy = new OxygenSaturationStrategy(lastBloodSaturationRecord, lastBloodPressureRecord);
        Alert bloodOxygenAlert = oxygenSaturationStrategy.checkAlert()[0];
        // checks if oxygenSaturationStrategy.checkAlert() found an alert
        // if yes => alert is triggered
        if (bloodOxygenAlert!=null) {
            triggerAlert(bloodOxygenAlert);
        }

        //_________________________ECG alerts_______________________
        
        HeartRateStrategy heartRateStrategy = new HeartRateStrategy(lastECGRecord, patient.getAverageECG());
        Alert ecgAlert = heartRateStrategy.checkAlert()[0];
        // checks if heartRateStrategy.checkAlert() found an alert
        // if yes => alert is triggered
        if (ecgAlert!=null) {
            triggerAlert(ecgAlert);
        }
    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
        //System.out.println("Patient: " + alert.getPatientId() + " had following condition: " + alert.getCondition() + ", at time: "+ alert.getTimestamp() + ".");
        System.out.println(alert.getCondition());
    }
}
