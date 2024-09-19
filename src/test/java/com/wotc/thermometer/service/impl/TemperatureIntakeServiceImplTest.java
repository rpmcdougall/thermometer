package com.wotc.thermometer.service.impl;

import com.wotc.thermometer.configuration.TemperatureIntakeServiceConfiguration;
import com.wotc.thermometer.domain.Temperature;
import com.wotc.thermometer.domain.TemperatureReadingResponse;
import com.wotc.thermometer.domain.ThermometerUnit;
import com.wotc.thermometer.service.TemperatureIntakeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestHeadersUriSpec;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class TemperatureIntakeServiceImplTest {

   @Mock
   private RestClient.ResponseSpec responseSpec;

   @Mock
   private TemperatureIntakeServiceConfiguration temperatureIntakeServiceConfiguration;

   @Mock
   private RestClient restClient;

   private RequestHeadersUriSpec requestHeadersUriSpec;

   private TemperatureReadingResponse temperatureReadingResponse;

    // Verifies that temperature readings are properly processed and converted.
    @Test
    void getTemperaturesReading() throws URISyntaxException {

        restClient = Mockito.mock(RestClient.class);
        responseSpec = Mockito.mock(RestClient.ResponseSpec.class);
        requestHeadersUriSpec = Mockito.mock(RestClient.RequestHeadersUriSpec.class);
        temperatureIntakeServiceConfiguration = Mockito.mock(TemperatureIntakeServiceConfiguration.class);
        temperatureReadingResponse = new TemperatureReadingResponse();
        temperatureReadingResponse.setTemperatures(   new ArrayList<Temperature>() {{
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 30.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 35.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 38.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 40.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 45.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 50.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 53.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 58.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 60.0));
            add(new Temperature(ThermometerUnit.FAHRENHEIT, 65.0));
        }});

        Mockito.when(restClient.get()).thenReturn(requestHeadersUriSpec);
        Mockito.when(temperatureIntakeServiceConfiguration.getTemperatureDataUrl()).thenReturn(new URI("http://test.com/temps"));
        Mockito.when(restClient.get().retrieve()).thenReturn(responseSpec);
        Mockito.when(responseSpec.toEntity(TemperatureReadingResponse.class)).thenReturn(new ResponseEntity<>(temperatureReadingResponse, HttpStatus.OK));
        TemperatureIntakeService service = new TemperatureIntakeServiceImpl(temperatureIntakeServiceConfiguration, restClient);

        List<Temperature> temperatureReading = service.getTemperaturesReading(ThermometerUnit.CELSIUS);

        // verify conversions happened nicely
        assertEquals(temperatureReading.get(0).toFahrenheit(), temperatureReadingResponse.getTemperatures().get(0).toFahrenheit());

        // verify we still have the same amount of temperatures in the reading
        assert(temperatureReading.size() == 10);
    }
}