package com.alerts;

public class AlertDecorator implements Alert{

    private Alert wrappee;

    public AlertDecorator(Alert alert) {
        wrappee = alert;
    }

    @Override
    public String getPatientId() {
        return wrappee.getPatientId();
    }

    @Override
    public String getCondition() {
        return wrappee.getCondition();
    }

    @Override
    public long getTimestamp() {
        return wrappee.getTimestamp();
    }
    
}
