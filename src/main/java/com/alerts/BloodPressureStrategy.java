package com.alerts;

import com.data_management.PatientRecord;

public class BloodPressureStrategy implements AlertStrategy{

    AlertFactory alertFactory = new BloodPressureAlertFactory();

    PatientRecord lastBloodPressureRecord;
    PatientRecord[] lastBloodPressureRecords;

    public BloodPressureStrategy(PatientRecord lastBloodPressureRecord, PatientRecord[] lastBloodPressureRecords) {
        this.lastBloodPressureRecord = lastBloodPressureRecord;
        this.lastBloodPressureRecords = lastBloodPressureRecords;
    }

    @Override
    public Alert[] checkAlert() {
        Alert[] alerts = new Alert[2];

        if(lastBloodPressureRecord!=null && (lastBloodPressureRecord.getMeasurementValue() > 120 || lastBloodPressureRecord.getMeasurementValue() < 60)) {
            // blood pressure is lower than 60 or higher than 120
            alerts[0] = alertFactory.createAlert(""+lastBloodPressureRecord.getPatientId(), "Blood Pressure Alert", System.currentTimeMillis());
        } 
        if(lastBloodPressureRecords[0]!=null && lastBloodPressureRecords[1]!=null && lastBloodPressureRecords[2]!=null) {
        
            if (lastBloodPressureRecords[0].getMeasurementValue()-10>=lastBloodPressureRecords[1].getMeasurementValue()
                    && lastBloodPressureRecords[1].getMeasurementValue()-10>=lastBloodPressureRecords[2].getMeasurementValue()) {
                // blood pressure is decreasing very rapidly (by 10 or more each time)
                alerts[1] = alertFactory.createAlert(""+lastBloodPressureRecord.getPatientId(), "Blood Pressure Alert", System.currentTimeMillis());
                } else if (lastBloodPressureRecords[0].getMeasurementValue()+10<=lastBloodPressureRecords[1].getMeasurementValue()
                            && lastBloodPressureRecords[1].getMeasurementValue()+10<=lastBloodPressureRecords[2].getMeasurementValue()) {
                // blood pressure is increasing very rapidly (by 10 or more each time)
                alerts[1] = alertFactory.createAlert(""+lastBloodPressureRecord.getPatientId(), "Blood Pressure Alert", System.currentTimeMillis());
            }
        }

        return alerts;
    }
    
}
