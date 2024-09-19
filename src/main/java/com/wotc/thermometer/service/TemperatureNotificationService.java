package com.wotc.thermometer.service;

import com.wotc.thermometer.configuration.TemperatureNotificationRulesConfig;
import com.wotc.thermometer.configuration.ThresholdConfiguration;
import com.wotc.thermometer.domain.Notification;
import com.wotc.thermometer.domain.Temperature;
import com.wotc.thermometer.domain.ThermometerUnit;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

// TemperatureNotificationService is responsible for comparing a reading data set and comparing it against thresholds to determine if a notification needs to be logged
@Service
public interface TemperatureNotificationService {
   List<Notification> processAndSend(List<Temperature> temperatureReading, ThermometerUnit thermometerUnit);
   TemperatureNotificationRulesConfig getRules();
   void setRules(TemperatureNotificationRulesConfig temperatureNotificationRulesConfig);
   ThresholdConfiguration getThresholdConfiguration();
   void setThresholdConfiguration(ThresholdConfiguration thresholdConfiguration);
   Logger getNotificationLogger();
}
