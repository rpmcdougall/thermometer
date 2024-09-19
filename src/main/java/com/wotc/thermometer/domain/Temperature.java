package com.wotc.thermometer.domain;

public class Temperature {
    ThermometerUnit temperatureUnit;
    Double temperature;

    public Temperature(ThermometerUnit temperatureUnit, Double temperature) {
        this.temperatureUnit = temperatureUnit;
        this.temperature = temperature;
    }


    public Double getTemperature() {
        return temperature;
    }

    public ThermometerUnit getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setTemperatureUnit(ThermometerUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Double toFahrenheit() {
        return ((temperature * 9)/5)+32  ;
    }

    public Double toCelsius() {
        return (( 5 * (temperature - 32.0)) / 9.0);
    }
}
