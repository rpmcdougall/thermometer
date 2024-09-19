package com.wotc.thermometer.configuration;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Configuration
public class TestTemperatureIntakeServiceConfiguration {

    @Value("temperatureDataUrl")
    URI temperatureDataUrl;

    @Bean
    public RestClient getRestClient() {
        return Mockito.mock(RestClient.class);
    }

    public URI getTemperatureDataUrl() {
        return temperatureDataUrl;
    }

    public void setTemperatureDataUrl(URI temperatureDataUrl) {
        this.temperatureDataUrl = temperatureDataUrl;
    }
}
