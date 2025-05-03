package com.alerts;

import com.data_management.PatientRecord;

public class OxygenSaturationStrategy implements AlertStrategy{

    AlertFactory alertFactory = new BloodOxygenAlertFactory();

    PatientRecord lastBloodPressureRecord;
    PatientRecord lastBloodSaturationRecord;

    public OxygenSaturationStrategy(PatientRecord lastBloodSaturationRecord, PatientRecord lastBloodPressureRecord) {
        this.lastBloodSaturationRecord = lastBloodSaturationRecord;
        this.lastBloodPressureRecord = lastBloodPressureRecord;
    }

    @Override
    public Alert[] checkAlert() {
        Alert alert = null;

        if(lastBloodPressureRecord!=null && lastBloodSaturationRecord!=null && lastBloodSaturationRecord.getMeasurementValue()<0.92 && lastBloodPressureRecord.getMeasurementValue()<90) {
            alert = alertFactory.createAlert(""+lastBloodPressureRecord.getPatientId(), "Hypotensive Hypoxemia Alert", System.currentTimeMillis());
        } else if(lastBloodSaturationRecord != null && lastBloodSaturationRecord.getMeasurementValue()<0.92) {
            alert = alertFactory.createAlert(""+lastBloodSaturationRecord.getPatientId(), "Blood Saturation Alert", System.currentTimeMillis());
        }

        Alert[] alerts = new Alert[] {alert};

        return alerts;
    }
}
