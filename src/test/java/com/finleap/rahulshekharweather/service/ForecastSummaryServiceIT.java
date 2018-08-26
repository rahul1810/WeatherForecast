package com.finleap.rahulshekharweather.service;

import com.finleap.rahulshekharweather.RahulshekharWeatherApplication;
import com.finleap.rahulshekharweather.response.ForecastSummaryResponse;
import com.finleap.rahulshekharweather.response.ResponseInfo;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RahulshekharWeatherApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForecastSummaryServiceIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    Gson gson = new Gson();

    @Test
    public void testForecastSummaryService(){
        ResponseEntity<String> response = getResponseForPayload("/data?city=Berlin");
        Assert.assertEquals(response.getStatusCode().value(), ResponseInfo.SUCCESS.getHttpStatus().value());
        Assert.assertNotNull(response.getBody());
        ForecastSummaryResponse forecastSummaryResponse = gson.fromJson(response.getBody(), ForecastSummaryResponse.class);
        Assert.assertEquals(forecastSummaryResponse.getStatusMessage(), ResponseInfo.SUCCESS.getDescription());
        Assert.assertEquals(forecastSummaryResponse.getStatusCode(), ResponseInfo.SUCCESS.getCode());
        Assert.assertNotNull(forecastSummaryResponse.getForecastSummary());
        Assert.assertNotNull(forecastSummaryResponse.getForecastSummary().getAveragePressure());
        Assert.assertNotNull(forecastSummaryResponse.getForecastSummary().getAverageNightlyTemeperature());
        Assert.assertNotNull(forecastSummaryResponse.getForecastSummary().getAverageDailyTemperature());

    }

    @Test
    public void testForecastSummaryServiceWithNoCity(){
        ResponseEntity<String> response = getResponseForPayload("/data");
        Assert.assertEquals(response.getStatusCode().value(), ResponseInfo.NO_CITY_ENTERED.getHttpStatus().value());
        Assert.assertNotNull(response.getBody());
        ForecastSummaryResponse forecastSummaryResponse = gson.fromJson(response.getBody(), ForecastSummaryResponse.class);
        Assert.assertEquals(forecastSummaryResponse.getStatusMessage(), ResponseInfo.NO_CITY_ENTERED.getDescription());
        Assert.assertEquals(forecastSummaryResponse.getStatusCode(), ResponseInfo.NO_CITY_ENTERED.getCode());
    }

    @Test
    public void testForecastSummaryServiceWithInvalidCity(){
        ResponseEntity<String> response = getResponseForPayload("/data?city=qwerty");
        Assert.assertEquals(response.getStatusCode().value(), ResponseInfo.INVALID_CITY.getHttpStatus().value());
        Assert.assertNotNull(response.getBody());
        ForecastSummaryResponse forecastSummaryResponse = gson.fromJson(response.getBody(), ForecastSummaryResponse.class);
        Assert.assertEquals(forecastSummaryResponse.getStatusMessage(), ResponseInfo.INVALID_CITY.getDescription());
        Assert.assertEquals(forecastSummaryResponse.getStatusCode(), ResponseInfo.INVALID_CITY.getCode());
    }

    @Test
    public void testForecastSummaryServiceWithInvalidParams(){
        ResponseEntity<String> response = getResponseForPayload("/data?param=invalid");
        Assert.assertEquals(response.getStatusCode().value(), ResponseInfo.UNSUPPORTED_QUERY_PARAMETERS.getHttpStatus().value());
        Assert.assertNotNull(response.getBody());
        ForecastSummaryResponse forecastSummaryResponse = gson.fromJson(response.getBody(), ForecastSummaryResponse.class);
        Assert.assertEquals(forecastSummaryResponse.getStatusMessage(), ResponseInfo.UNSUPPORTED_QUERY_PARAMETERS.getDescription());
        Assert.assertEquals(forecastSummaryResponse.getStatusCode(), ResponseInfo.UNSUPPORTED_QUERY_PARAMETERS.getCode());
    }


    private ResponseEntity<String> getResponseForPayload(String payload){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(payload),
                HttpMethod.GET, entity, String.class);
        return response;
    }

    private String createURLWithPort(String payload) {
        return "http://localhost:" + port + payload;
    }

}
