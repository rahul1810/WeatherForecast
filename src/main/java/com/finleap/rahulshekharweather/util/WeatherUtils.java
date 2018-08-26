package com.finleap.rahulshekharweather.util;

import com.finleap.rahulshekharweather.config.Config;
import com.finleap.rahulshekharweather.model.Coordinates;
import com.finleap.rahulshekharweather.model.TimezoneDetails;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

@Component
public class WeatherUtils {

    @Autowired
    Config config;

    Logger logger = LoggerFactory.getLogger(WeatherUtils.class);

    public String getDataFromURL(String requestUrl) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(requestUrl);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public boolean isItDay(TimeZone requestLocTimeZone, Long epochTime){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(requestLocTimeZone);
        cal.setTimeInMillis(epochTime * 1000);
        if(cal.get(Calendar.HOUR_OF_DAY) < 6 || cal.get(Calendar.HOUR_OF_DAY) >= 18){
            return false;
        }
        return true;
    }

    @Cacheable
    public TimeZone getLocalTimeOffsetInSeconds(Coordinates coordinates, Long epochTime){
        logger.info("Fetching timezones for {}", coordinates);
        String requestURL = String.format(config.getTimezoneAPIUrl(), coordinates.getLat(), coordinates.getLon(), epochTime/1000);
        try {
            String response = getDataFromURL(requestURL);
            if(response != null){
                Gson gson = new Gson();
                TimezoneDetails tzd = gson.fromJson(response, TimezoneDetails.class);
                return TimeZone.getTimeZone(tzd.getTimeZoneId());
            }
        } catch (IOException e) {
            logger.error("Error fetching details from Timezone API");
        }
        return TimeZone.getDefault();
    }


}
