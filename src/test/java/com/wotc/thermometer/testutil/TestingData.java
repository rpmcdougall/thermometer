package com.wotc.thermometer.testutil;

import com.wotc.thermometer.domain.Temperature;
import com.wotc.thermometer.domain.ThermometerUnit;

import java.util.ArrayList;
import java.util.List;

public class TestingData {

    public List<Temperature> ReadingWithBoilingTemperature = new ArrayList<Temperature>() {{
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 80.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 90.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 100.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 110.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 120.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 130.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 140.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 180.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 200.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 213.0));
    }};

    public List<Temperature> ReadingWithFreezingTemperaturesDecreasing = new ArrayList<Temperature>() {{
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 50.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 40.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 38.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 37.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 38.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 35.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 34.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 32.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 30.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 25.0));
    }};

    public List<Temperature> ReadingSmallDifferential = new ArrayList<Temperature>() {{
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 31.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 32.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 31.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 32.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 31.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 32.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 31.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 32.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 31.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 32.0));
    }};

    public List<Temperature> ReadingWithFreezingTemperaturesIncreasing = new ArrayList<Temperature>() {{
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 25.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 30.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 32.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 34.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 35.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 38.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 40.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 41.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 42.0));
        add(new Temperature(ThermometerUnit.FAHRENHEIT, 43.0));
    }};

    public List<Temperature> getReadingWithBoilingTemperature() {
        return ReadingWithBoilingTemperature;
    }

    public List<Temperature> getReadingWithFreezingTemperaturesDecreasing() {
        return ReadingWithFreezingTemperaturesDecreasing;
    }

    public List<Temperature> getReadingSmallDifferential() {
        return ReadingSmallDifferential;
    }

    public List<Temperature> getReadingWithFreezingTemperaturesIncreasing() {
        return ReadingWithFreezingTemperaturesIncreasing;
    }
}
