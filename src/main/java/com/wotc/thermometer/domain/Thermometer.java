package com.wotc.thermometer.domain;

import com.wotc.thermometer.service.TemperatureIntakeService;
import com.wotc.thermometer.service.TemperatureNotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Thermometer {

    ThermometerUnit thermometerUnit;
    TemperatureIntakeService temperatureIntakeService;
    TemperatureNotificationService temperatureNotificationService;


    Temperature currentTemperature;
    Temperature previousTemperature;
    List<Temperature> temperaturesReading;

    public Thermometer(ThermometerUnit thermometerUnit, TemperatureIntakeService temperatureIntakeService, TemperatureNotificationService temperatureNotificationService) {
        this.thermometerUnit = thermometerUnit;
        this.temperatureIntakeService = temperatureIntakeService;
        this.temperatureNotificationService = temperatureNotificationService;
    }


    public ThermometerUnit getThermometerUnit() {
        return thermometerUnit;
    }

    public void setThermometerUnit(ThermometerUnit thermometerUnit) {
        this.thermometerUnit = thermometerUnit;
    }

    public Temperature getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature() {
        List<Temperature> temperaturesReading = temperatureIntakeService.getTemperaturesReading(this.thermometerUnit);
        this.currentTemperature = temperatureIntakeService.getTemperaturesReading(this.thermometerUnit).get(temperaturesReading.size() - 1);
    }

    public Temperature getPreviousTemperature() {
        return previousTemperature;
    }

    public void setPreviousTemperature(Temperature previousTemperature) {
        List<Temperature> temperaturesReading = temperatureIntakeService.getTemperaturesReading(this.thermometerUnit);
        this.previousTemperature = temperatureIntakeService.getTemperaturesReading(this.thermometerUnit).get(temperaturesReading.size() - 2);
    }

    public List<Temperature> getTemperaturesReading() {
        return temperaturesReading;
    }

    public void setTemperatureReading(List<Temperature> temperaturesReading) {
        temperatureNotificationService.processAndSend(temperaturesReading, thermometerUnit);
        this.temperaturesReading = temperatureIntakeService.getTemperaturesReading(this.thermometerUnit);
    }
}
