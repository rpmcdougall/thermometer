package com.wotc.thermometer.service.impl;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.wotc.thermometer.configuration.TemperatureNotificationRulesConfig;
import com.wotc.thermometer.configuration.ThresholdConfiguration;
import com.wotc.thermometer.domain.Notification;
import com.wotc.thermometer.domain.ThermometerUnit;
import com.wotc.thermometer.service.TemperatureNotificationService;
import com.wotc.thermometer.testutil.TestingData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureNotificationServiceImplTest {

    TemperatureNotificationRulesConfig temperatureNotificationRulesConfig;
    ThresholdConfiguration thresholdConfiguration;

    @BeforeEach
    void setUp() {
        temperatureNotificationRulesConfig = new TemperatureNotificationRulesConfig();
        thresholdConfiguration = new ThresholdConfiguration();
    }

    @Test
    void NotificationLoggedForBoiling() {
        temperatureNotificationRulesConfig.setNotifyOnlyOnDecrease(false);
        temperatureNotificationRulesConfig.setNotifyOnlyOnIncrease(false);
        temperatureNotificationRulesConfig.setTemperatureDifferential(0.0);
        thresholdConfiguration.setBoilingEnabled(true);
        thresholdConfiguration.setFreezingEnabled(false);


        TemperatureNotificationService temperatureNotificationService = new TemperatureNotificationServiceImpl();

        temperatureNotificationService.setRules(temperatureNotificationRulesConfig);
        temperatureNotificationService.setThresholdConfiguration(thresholdConfiguration);

        List<Notification> notifications = temperatureNotificationService.processAndSend(new TestingData().ReadingWithBoilingTemperature, ThermometerUnit.FAHRENHEIT);

        // Verify that there is only one temperature at or above boiling
        assert(notifications.size() == 1);

        // Verify that the threshold details are getting passed to notification
        assertEquals(notifications.get(0).getThresholdName(), "Boiling");
        assertEquals(notifications.get(0).getValue(), 213.0);
    }

    @Test
    void NotificationLoggedForFreezing() {
        temperatureNotificationRulesConfig.setNotifyOnlyOnDecrease(false);
        temperatureNotificationRulesConfig.setNotifyOnlyOnIncrease(false);
        temperatureNotificationRulesConfig.setTemperatureDifferential(0.0);
        thresholdConfiguration.setBoilingEnabled(false);
        thresholdConfiguration.setFreezingEnabled(true);


        TemperatureNotificationService temperatureNotificationService = new TemperatureNotificationServiceImpl();

        temperatureNotificationService.setRules(temperatureNotificationRulesConfig);
        temperatureNotificationService.setThresholdConfiguration(thresholdConfiguration);

        List<Notification> notifications = temperatureNotificationService.processAndSend(new TestingData().ReadingWithFreezingTemperaturesDecreasing, ThermometerUnit.FAHRENHEIT);

        //Verify that we got the desired number of freezing records in the reading
        assertEquals(notifications.size(), 3);

        // Verify that the threshold details are getting passed to notification and are accurate
        for (Notification notification : notifications) {
            assertEquals(notification.getThresholdName(), "Freezing");
        }
    }

    @Test
    void NotificationIgnoredForSmallDifferential() {
        temperatureNotificationRulesConfig.setNotifyOnlyOnDecrease(false);
        temperatureNotificationRulesConfig.setNotifyOnlyOnIncrease(false);
        temperatureNotificationRulesConfig.setTemperatureDifferential(10.0);
        thresholdConfiguration.setBoilingEnabled(false);
        thresholdConfiguration.setFreezingEnabled(true);


        TemperatureNotificationService temperatureNotificationService = new TemperatureNotificationServiceImpl();

        temperatureNotificationService.setRules(temperatureNotificationRulesConfig);
        temperatureNotificationService.setThresholdConfiguration(thresholdConfiguration);

        // Temps are below freezing and should be ignored due to differential setting excluding the first in the reading
        List<Notification> notifications = temperatureNotificationService.processAndSend(new TestingData().ReadingSmallDifferential, ThermometerUnit.FAHRENHEIT);

        //Verify that we got the desired number of freezing records in the reading
        assertEquals(1, notifications.size());
        assertEquals(notifications.get(0).getThresholdName(), "Freezing");
        assertEquals(notifications.get(0).getValue(), 31.0);
    }

    @Test
    void NotificationLoggedForIncreasingToFreezing() {
        temperatureNotificationRulesConfig.setNotifyOnlyOnDecrease(false);
        temperatureNotificationRulesConfig.setNotifyOnlyOnIncrease(true);
        temperatureNotificationRulesConfig.setTemperatureDifferential(0.0);
        thresholdConfiguration.setBoilingEnabled(false);
        thresholdConfiguration.setFreezingEnabled(true);


        TemperatureNotificationService temperatureNotificationService = new TemperatureNotificationServiceImpl();

        temperatureNotificationService.setRules(temperatureNotificationRulesConfig);
        temperatureNotificationService.setThresholdConfiguration(thresholdConfiguration);

        // Temps are increasing for the first few values until surpassing freezing
        List<Notification> notifications = temperatureNotificationService.processAndSend(new TestingData().ReadingWithFreezingTemperaturesIncreasing, ThermometerUnit.FAHRENHEIT);

        //Verify that we got the desired number of freezing records in the reading
        assertEquals(2, notifications.size());
    }

    @Test
    void NotificationLoggedForIncreaseBelowFreezing() {
        temperatureNotificationRulesConfig.setNotifyOnlyOnDecrease(true);
        temperatureNotificationRulesConfig.setNotifyOnlyOnIncrease(false);
        temperatureNotificationRulesConfig.setTemperatureDifferential(0.0);
        thresholdConfiguration.setBoilingEnabled(false);
        thresholdConfiguration.setFreezingEnabled(true);


        TemperatureNotificationService temperatureNotificationService = new TemperatureNotificationServiceImpl();

        temperatureNotificationService.setRules(temperatureNotificationRulesConfig);
        temperatureNotificationService.setThresholdConfiguration(thresholdConfiguration);

        // Temps are increasing for the first few values until surpassing freezing
        List<Notification> notifications = temperatureNotificationService.processAndSend(new TestingData().ReadingWithFreezingTemperaturesDecreasing, ThermometerUnit.FAHRENHEIT);

        //Verify that we got the desired number of freezing records in the reading
        assertEquals(2, notifications.size());
    }


}
