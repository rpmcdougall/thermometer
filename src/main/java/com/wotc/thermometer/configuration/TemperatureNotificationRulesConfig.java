package com.wotc.thermometer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;


//TemperatureNotificationRulesConfig allows setting basic parameters on whether notification will occur
@Configuration
public class TemperatureNotificationRulesConfig {
    @Value("notifyOnlyOnIncrease")
    Boolean notifyOnlyOnIncrease;
    @Value("notifyOnlyOnDecrease")
    Boolean notifyOnlyOnDecrease;
    @Value("temperatureDifferential")
    Double temperatureDifferential;

    public Boolean getNotifyOnlyOnIncrease() {
        return notifyOnlyOnIncrease;
    }

    public void setNotifyOnlyOnIncrease(Boolean notifyOnlyOnIncrease) {
        this.notifyOnlyOnIncrease = notifyOnlyOnIncrease;
    }

    public Boolean getNotifyOnlyOnDecrease() {
        return notifyOnlyOnDecrease;
    }

    public void setNotifyOnlyOnDecrease(Boolean notifyOnlyOnDecrease) {
        this.notifyOnlyOnDecrease = notifyOnlyOnDecrease;
    }

    public Double getTemperatureDifferential() {
        return temperatureDifferential;
    }

    public void setTemperatureDifferential(Double temperatureDifferential) {
        this.temperatureDifferential = temperatureDifferential;
    }
}
