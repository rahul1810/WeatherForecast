package com.finleap.rahulshekharweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherServiceResponse
{
    private Double message;

    @SerializedName("cnt")
    private Long count;

    private String cod;

    @SerializedName("list")
    private List<WeatherData> weatherData;

    private City city;

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public List<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(List<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "WeatherServiceResponse [message = "+message+", count = "+count+", cod = "+cod+", weatherData = "+weatherData+", city = "+city+"]";
    }
}
