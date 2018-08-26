package com.finleap.rahulshekharweather.util;

import com.finleap.rahulshekharweather.config.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class WeatherUtilsTest {

    @Before
    public void setUp() throws Exception {
        weatherUtils = new WeatherUtils();
    }

    @Mock
    Config config;

    @InjectMocks
    private WeatherUtils weatherUtils;

    @Test
    public void getDataFromURL() throws IOException {
        String url = "https://maps.googleapis.com/maps/api/timezone/json?location=48.137,11.5752&timestamp=1535224313";
        String response = weatherUtils.getDataFromURL(url);
        Assert.assertNotNull(response);
    }

    @Test
    public void isItDay() {

        Calendar cal = Calendar.getInstance();
        boolean expectedValue = cal.get(Calendar.HOUR_OF_DAY) >= 6 &&  cal.get(Calendar.HOUR_OF_DAY) < 18;
        long currentTime = System.currentTimeMillis()/1000;
        TimeZone currentTimezone = cal.getTimeZone();
        boolean result = weatherUtils.isItDay(currentTimezone, currentTime);
        Assert.assertEquals(expectedValue, result);
        cal.add(Calendar.HOUR_OF_DAY, 12);
        result = weatherUtils.isItDay(cal.getTimeZone(), System.currentTimeMillis());
        Assert.assertNotEquals(expectedValue, result);
    }

    @Test
    public void getLocalTimeOffsetInSeconds() {
    }
}