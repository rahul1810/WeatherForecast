package com.finleap.rahulshekharweather.manager;

import com.finleap.rahulshekharweather.config.Config;
import com.finleap.rahulshekharweather.config.TestConfig;
import com.finleap.rahulshekharweather.exception.ApplicationException;
import com.finleap.rahulshekharweather.model.ForecastSummary;
import com.finleap.rahulshekharweather.model.TimezoneDetails;
import com.finleap.rahulshekharweather.model.WeatherServiceResponse;
import com.finleap.rahulshekharweather.util.WeatherUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TimeZone;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ForecastSummaryManagerTest {

    ForecastSummary forecastSummary;

    WeatherServiceResponse weatherServiceResponse;

    TimezoneDetails timezoneDetails;

    @Mock
    WeatherUtils weatherUtils;

    @Mock
    Config config;

    @InjectMocks
    private ForecastSummaryManager forecastSummaryManager;

    @Before
    public void setUp() throws Exception {
        forecastSummary = forecastSummary();
        weatherServiceResponse = weatherServiceResponse();
        timezoneDetails = timezoneDetails();
    }

    @Test
    public void getSummaryByCity() throws ApplicationException, IOException {
        String cityName = "Cairo";
        Gson gson = new Gson();
        Mockito.when(config.getWeatherForeCastAPIKey()).thenReturn("");
        Mockito.when(config.getWeatherForecastURL()).thenReturn("");
        Mockito.when(weatherUtils.getDataFromURL("")).thenReturn(gson.toJson(weatherServiceResponse));
        ForecastSummary summary = forecastSummaryManager.getSummaryByCity(cityName);
        Assert.assertNotNull(summary.getAverageDailyTemperature());
        Assert.assertNotNull(summary.getAverageNightlyTemeperature());
        Assert.assertNotNull(summary.getAveragePressure());
    }

    @Test
    public void getForecastSummaryFromWeatherResponse() {

        Mockito.when(weatherUtils.getLocalTimeOffsetInSeconds
                (weatherServiceResponse.getCity().getCoordinates(),
                        System.currentTimeMillis()))
                .thenReturn(TimeZone.getTimeZone(timezoneDetails.getTimeZoneId()));
        ForecastSummary summary = forecastSummaryManager.getForecastSummaryFromWeatherResponse(weatherServiceResponse);
        Assert.assertNotNull(summary.getAverageDailyTemperature());
        Assert.assertNotNull(summary.getAverageNightlyTemeperature());
        Assert.assertNotNull(summary.getAveragePressure());

    }


    WeatherServiceResponse weatherServiceResponse() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data/testForecastData.json").getFile());
        JsonReader reader = new JsonReader(new FileReader(file));
        Gson gson = new Gson();
        return gson.fromJson(reader, WeatherServiceResponse.class);
    }

    TimezoneDetails timezoneDetails() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data/testTimezoneData.json").getFile());
        JsonReader reader = new JsonReader(new FileReader(file));
        Gson gson = new Gson();
        return gson.fromJson(reader, TimezoneDetails.class);
    }

    public ForecastSummary forecastSummary(){
        return new ForecastSummary(100.0, 20.0, 13.0);
    }



}