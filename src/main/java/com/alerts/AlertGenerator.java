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
        // Implementation goes here

        PatientRecord[] lastBloodPressureRecords = patient.getlastBloodPressureRecords();
        PatientRecord lastBloodPressureRecord = lastBloodPressureRecords[lastBloodPressureRecords.length-1];
        PatientRecord lastBloodSaturationRecord = patient.getlastBloodSaturationRecord();
        PatientRecord lastECGRecord = patient.getlastECGRecord();

        //____________________Blood pressure alerts_________________
        if(lastBloodPressureRecord.getMeasurementValue() > 120 || lastBloodPressureRecord.getMeasurementValue() < 60) {
            // blood pressure is lower than 60 or higher than 120
            Alert alert = new Alert(""+lastBloodPressureRecord.getPatientId(), "Blood Pressure Alert", System.currentTimeMillis());
            triggerAlert(alert);
        } else if (lastBloodPressureRecords[0].getMeasurementValue()-10>=lastBloodPressureRecords[1].getMeasurementValue()
                        && lastBloodPressureRecords[1].getMeasurementValue()-10>=lastBloodPressureRecords[2].getMeasurementValue()) {
            // blood pressure is decreasing very rapidely (by 10 or more each time)
            Alert alert = new Alert(""+lastBloodPressureRecord.getPatientId(), "Blood Pressure Alert", System.currentTimeMillis());
            triggerAlert(alert);
        } else if (lastBloodPressureRecords[0].getMeasurementValue()+10<=lastBloodPressureRecords[1].getMeasurementValue()
                        && lastBloodPressureRecords[1].getMeasurementValue()+10<=lastBloodPressureRecords[2].getMeasurementValue()) {
            // blood pressure is increasing very rapidely (by 10 or more each time)
            Alert alert = new Alert(""+lastBloodPressureRecord.getPatientId(), "Blood Pressure Alert", System.currentTimeMillis());
            triggerAlert(alert);
        }

        //___________________Blood saturation alerts________________
        if(lastBloodSaturationRecord.getMeasurementValue()<0.92) {
            Alert alert = new Alert(""+lastBloodPressureRecord.getPatientId(), "Blood Saturation Alert", System.currentTimeMillis());
            triggerAlert(alert);
        }


        //_______________________Combined alerts____________________
        if(lastBloodSaturationRecord.getMeasurementValue()<0.92 && lastBloodPressureRecord.getMeasurementValue()<90) {
            Alert alert = new Alert(""+lastBloodPressureRecord.getPatientId(), "Hypotensive Hypoxemia Alert", System.currentTimeMillis());
            triggerAlert(alert);
        }


        //_________________________ECG alerts_______________________
        if (lastECGRecord.getMeasurementValue()-40 >= patient.getAverageECG()){
            Alert alert = new Alert(""+lastBloodPressureRecord.getPatientId(), "ECG Alert", System.currentTimeMillis());
            triggerAlert(alert); 
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
        System.out.println("Patient: " + alert.getPatientId() + " had following condition: " + alert.getCondition() + ", at time: "+ alert.getTimestamp() + ".");
        //System.out.println(alert.getCondition());
    }
}
