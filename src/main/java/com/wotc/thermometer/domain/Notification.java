package com.wotc.thermometer.domain;

public class Notification {
    String thresholdName;
    Double value;

    public Notification(String thresholdName, Double value) {
        this.thresholdName = thresholdName;
        this.value = value;
    }

    public String getThresholdName() {
        return thresholdName;
    }

    public void setThresholdName(String thresholdName) {
        this.thresholdName = thresholdName;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
