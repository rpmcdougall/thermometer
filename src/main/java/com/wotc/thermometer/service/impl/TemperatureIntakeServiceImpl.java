package com.wotc.thermometer.service.impl;

import com.wotc.thermometer.configuration.TemperatureIntakeServiceConfiguration;
import com.wotc.thermometer.domain.Temperature;
import com.wotc.thermometer.domain.TemperatureReadingResponse;
import com.wotc.thermometer.domain.ThermometerUnit;
import com.wotc.thermometer.service.TemperatureIntakeService;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

public class TemperatureIntakeServiceImpl implements TemperatureIntakeService {

    DecimalFormat dec = new DecimalFormat("#0.00");

    TemperatureIntakeServiceConfiguration configuration;

    RestClient restClient;

    public TemperatureIntakeServiceImpl(TemperatureIntakeServiceConfiguration configuration, RestClient restClient) {
        this.configuration = configuration;
        this.restClient = restClient;
    }

    @Override
    public List<Temperature> getTemperaturesReading(ThermometerUnit unit) {
        RestClient.ResponseSpec result = restClient
                .get()
                .retrieve();

        if (result.toEntity(TemperatureReadingResponse.class).getStatusCode() != HttpStatus.OK) {
            throw new WebServerException("Rest client returned non 200 status code", new Throwable("Rest client returned non 200 status code"));
        }
        List<Temperature> temperaturesReading = Objects.requireNonNull(result.toEntity(TemperatureReadingResponse.class).getBody()).getTemperatures();

        // Convert temperatures to desired unit based on thermometer setting
        for (Temperature temperature : temperaturesReading) {
            if (temperature.getTemperatureUnit() != unit) {
                if (unit == ThermometerUnit.FAHRENHEIT) {
                    temperature.setTemperature(Double.parseDouble(dec.format(temperature.toFahrenheit())));
                    temperature.setTemperatureUnit(unit);
                } else {
                    temperature.setTemperature(Double.parseDouble(dec.format(temperature.toCelsius())));
                    temperature.setTemperatureUnit(unit);
                }
            }

        }
        return temperaturesReading;
    }
}
