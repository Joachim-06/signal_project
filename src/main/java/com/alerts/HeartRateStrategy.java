package com.alerts;

import com.data_management.PatientRecord;

public class HeartRateStrategy implements AlertStrategy{

    AlertFactory alertFactory = new ECGAlertFactory();

    PatientRecord lastECGRecord;
    double averageECG;

    public HeartRateStrategy(PatientRecord lastECGRecord, double averageECG) {
        this.lastECGRecord = lastECGRecord;
        this.averageECG = averageECG;
    }

    @Override
    public Alert[] checkAlert() {
        Alert alert = null;
        if (lastECGRecord != null && lastECGRecord.getMeasurementValue()-40 >= averageECG){
            alert = alertFactory.createAlert(""+lastECGRecord.getPatientId(), "ECG Alert", System.currentTimeMillis());
            
        }

        Alert[] alerts = new Alert[] {alert};
        return alerts;
    }
}
