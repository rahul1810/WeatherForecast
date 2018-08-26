package com.finleap.rahulshekharweather.manager;

import com.finleap.rahulshekharweather.config.Config;
import com.finleap.rahulshekharweather.exception.ApplicationException;
import com.finleap.rahulshekharweather.model.ForecastSummary;
import com.finleap.rahulshekharweather.model.WeatherData;
import com.finleap.rahulshekharweather.model.WeatherServiceResponse;
import com.finleap.rahulshekharweather.response.ResponseInfo;
import com.finleap.rahulshekharweather.util.WeatherUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TimeZone;

@Component
public class ForecastSummaryManager {

    @Autowired
    WeatherUtils weatherUtils;

    @Autowired
    Config config;

    int NUM_OF_DAYS_TO_BE_FORECASTED = 3;

    Logger logger = LoggerFactory.getLogger(ForecastSummaryManager.class);

    @Cacheable("forecast")
    public ForecastSummary getSummaryByCity(String city) throws ApplicationException {
        if(city == null){
            throw new ApplicationException(ResponseInfo.NO_CITY_ENTERED);
        }
        WeatherServiceResponse weatherServiceResponse = getWeatherResponseForCity(city);
        if(weatherServiceResponse == null || weatherServiceResponse.getWeatherData() == null || weatherServiceResponse.getWeatherData().size() == 0){
            throw new ApplicationException(ResponseInfo.INVALID_CITY);
        }
        logger.info("Retrieved information for {}", city);
        ForecastSummary summary = getForecastSummaryFromWeatherResponse(weatherServiceResponse);
        logger.info("Retrieved " + summary);
        return summary;
    }

    protected WeatherServiceResponse getWeatherResponseForCity(String city) throws ApplicationException {
        String url = String.format(config.getWeatherForecastURL(), city, config.getWeatherForeCastAPIKey());
        WeatherServiceResponse weatherServiceResponse = null;
        try {
            String response = weatherUtils.getDataFromURL(url);
            Gson gson = new Gson();
            weatherServiceResponse = gson.fromJson(response, WeatherServiceResponse.class);
        } catch (FileNotFoundException e){
            throw new ApplicationException(ResponseInfo.INVALID_CITY);
        }
        catch (IOException e) {
            throw new ApplicationException(ResponseInfo.ERROR_GETTING_RESPONSE_FROM_WEATHER_SERVICE);
        }
        return weatherServiceResponse;
    }

    protected ForecastSummary getForecastSummaryFromWeatherResponse(WeatherServiceResponse weatherServiceResponse){
        int count = 0, nightCount = 0;
        Double pressureSum = 0.0, dailyTemperatureSum = 0.0, nightlyTemperatureSum = 0.0;
        WeatherData data = null;
        TimeZone locationTimeZone = weatherUtils.getLocalTimeOffsetInSeconds(weatherServiceResponse.getCity().getCoordinates(), System.currentTimeMillis());

        while (count < weatherServiceResponse.getWeatherData().size()){
            data = weatherServiceResponse.getWeatherData().get(count);
            count++;
            long maxEpochTime = System.currentTimeMillis() + NUM_OF_DAYS_TO_BE_FORECASTED * 24 * 60 * 60 * 1000;

            if(data.getEpochTime()*1000 < maxEpochTime) {
                pressureSum += data.getKeyInfo().getPressure();
                if(weatherUtils.isItDay(locationTimeZone, data.getEpochTime())) {
                    dailyTemperatureSum += (data.getKeyInfo().getTemp() - 273.0);
                }
                else {
                    nightlyTemperatureSum += (data.getKeyInfo().getTemp() -273.0);
                    nightCount++;
                }
            }
            else{
                break;
            }

        }
        Double averagePressure = getAverage(pressureSum, count);
        Double averageDailyTemperature = getAverage(dailyTemperatureSum, count - nightCount);
        Double averageNightlyTemperature = getAverage(nightlyTemperatureSum, nightCount);
        return new ForecastSummary(averagePressure, averageDailyTemperature, averageNightlyTemperature);
    }

    protected Double getAverage(double value, int count){
        double average = value/count;
        average = (Math.round(average * 1000.0))/1000.0;
        return average;
    }
}
