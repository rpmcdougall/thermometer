package com.wotc.thermometer.service.impl;

import com.wotc.thermometer.configuration.TemperatureNotificationRulesConfig;
import com.wotc.thermometer.configuration.ThresholdConfiguration;
import com.wotc.thermometer.domain.Notification;
import com.wotc.thermometer.domain.Temperature;
import com.wotc.thermometer.domain.ThermometerThreshold;
import com.wotc.thermometer.domain.ThermometerUnit;
import com.wotc.thermometer.service.TemperatureNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class TemperatureNotificationServiceImpl implements TemperatureNotificationService {

    Logger notifcationLogger = LoggerFactory.getLogger(TemperatureNotificationServiceImpl.class);
    ThresholdConfiguration thresholdConfiguration;
    TemperatureNotificationRulesConfig temperatureNotificationRulesConfig;

    public ThresholdConfiguration getThresholdConfiguration() {
        return thresholdConfiguration;
    }

    @Override
    public Logger getNotificationLogger() {
        return notifcationLogger;
    }

    public void setThresholdConfiguration(ThresholdConfiguration thresholdConfiguration) {
        this.thresholdConfiguration = thresholdConfiguration;
    }

    public TemperatureNotificationRulesConfig getRules() {
        return temperatureNotificationRulesConfig;
    }

    public void setRules(TemperatureNotificationRulesConfig temperatureNotificationRulesConfig) {
        this.temperatureNotificationRulesConfig = temperatureNotificationRulesConfig;
    }

    @Override
    public List<Notification> processAndSend(List<Temperature> temperatureReading, ThermometerUnit thermometerUnit) {
        List<Notification> notifications = new ArrayList<>();
        List<ThermometerThreshold> thermometerThresholds = collectThresholds(thermometerUnit);
        Integer index = 0;
        for (Temperature temperature : temperatureReading) {
            for (ThermometerThreshold thermometerThreshold : thermometerThresholds) {
                boolean meetsThreshold = thermometerThreshold.getThermometerThreshold().test(temperature.getTemperature());
                System.out.println(meetsThreshold);
                if (meetsThreshold) {
                    if (index != 0 && temperatureNotificationRulesConfig.getNotifyOnlyOnDecrease() && (temperature.getTemperature() < temperatureReading.get(index - 1).getTemperature())) {
                        if (temperatureNotificationRulesConfig.getTemperatureDifferential() > 0 && index != 0) {
                            if (abs(temperature.getTemperature() - temperatureReading.get(index - 1).getTemperature()) > temperatureNotificationRulesConfig.getTemperatureDifferential()) {
                                notifications.add(new Notification(thermometerThreshold.getName(), temperature.getTemperature()));
                            }
                        } else {
                            notifications.add(new Notification(thermometerThreshold.getName(), temperature.getTemperature()));
                        }
                    }
                    if (index != 0 && temperatureNotificationRulesConfig.getNotifyOnlyOnIncrease() && (temperature.getTemperature() > temperatureReading.get(index - 1).getTemperature())) {
                        if (temperatureNotificationRulesConfig.getTemperatureDifferential() > 0 && index != 0) {
                            if (abs(temperature.getTemperature() - temperatureReading.get(index - 1).getTemperature()) > temperatureNotificationRulesConfig.getTemperatureDifferential()) {
                                notifications.add(new Notification(thermometerThreshold.getName(), temperature.getTemperature()));
                            }
                        } else {
                            notifications.add(new Notification(thermometerThreshold.getName(), temperature.getTemperature()));
                        }
                    }

                    if (!temperatureNotificationRulesConfig.getNotifyOnlyOnIncrease()
                            && !temperatureNotificationRulesConfig.getNotifyOnlyOnDecrease()) {

                        if (temperatureNotificationRulesConfig.getTemperatureDifferential() > 0 && index != 0) {
                            if (abs(temperature.getTemperature() - temperatureReading.get(index - 1).getTemperature()) > temperatureNotificationRulesConfig.getTemperatureDifferential()) {
                                notifications.add(new Notification(thermometerThreshold.getName(), temperature.getTemperature()));
                            }
                        } else {
                            notifications.add(new Notification(thermometerThreshold.getName(), temperature.getTemperature()));
                        }

                    }
                    index++;
                }
            }
        }
            return notifications;
    }


    private List<ThermometerThreshold> collectThresholds(ThermometerUnit thermometerUnit) {
        List<ThermometerThreshold> thresholds = new ArrayList<>();

        Double boiling;
        Double freezing;

        if (thermometerUnit == ThermometerUnit.CELSIUS) {
            boiling = 100.0;
            freezing = 0.0;
        } else {
            boiling = 212.0;
            freezing = 32.0;
        }

        if(thresholdConfiguration.getBoilingEnabled()) {
            ThermometerThreshold threshold = new ThermometerThreshold();
            threshold.setName("Boiling");
            threshold.setThermometerThreshold(t -> (t >= boiling));
            thresholds.add(threshold);
        } else if (thresholdConfiguration.getFreezingEnabled()) {
            ThermometerThreshold threshold = new ThermometerThreshold();
            threshold.setName("Freezing");
            threshold.setThermometerThreshold(t -> (t <= freezing));
            thresholds.add(threshold);
        }
        if (thresholdConfiguration.getCustomThresholds() != null){
            thresholds.addAll(thresholdConfiguration.getCustomThresholds());
        }

        return thresholds;
    }

    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

}
