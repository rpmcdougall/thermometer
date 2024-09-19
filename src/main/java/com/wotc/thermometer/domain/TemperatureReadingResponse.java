package com.wotc.thermometer.domain;

import java.util.List;

public class TemperatureReadingResponse {
    List<Temperature> temperatures;

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }
}
