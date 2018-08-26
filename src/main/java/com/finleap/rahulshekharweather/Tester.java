package com.finleap.rahulshekharweather;

import com.finleap.rahulshekharweather.model.WeatherData;
import com.finleap.rahulshekharweather.model.WeatherServiceResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class Tester {

    public static void main(String[] args) throws Exception {

        String json = readUrl("https://samples.openweathermap.org/data/2.5/forecast?q=8fn_291&appid=b6907d289e10d714a6e88b30761fae22");
        WeatherServiceResponse response = new Gson().fromJson(json, WeatherServiceResponse.class);
        for(WeatherData data: response.getWeatherData()) {
            Long time = data.getEpochTime();
            Date dt = new Date(time * 1000);

            System.out.println(dt.getHours());
        }
        System.out.print(json.length());
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
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
}
