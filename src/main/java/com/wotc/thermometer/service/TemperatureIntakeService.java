package com.wotc.thermometer.service;

import com.wotc.thermometer.domain.Temperature;
import com.wotc.thermometer.domain.ThermometerUnit;
import org.springframework.stereotype.Service;

import java.util.List;

//TemperatureIntakeService is responsible for managing collecting data from external sources
@Service
public interface TemperatureIntakeService {
    // getTemperaturesReading is responsible for collecting temperature readings data and converting to the desired unit (C or F)
    List<Temperature> getTemperaturesReading(ThermometerUnit unit);
}
