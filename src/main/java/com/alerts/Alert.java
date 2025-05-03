package com.alerts;

// Represents an alert
public interface Alert {

    public String getPatientId();

    public String getCondition();

    public long getTimestamp();
}
