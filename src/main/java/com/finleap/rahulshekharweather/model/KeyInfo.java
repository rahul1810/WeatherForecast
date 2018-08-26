package com.finleap.rahulshekharweather.model;

import com.google.gson.annotations.SerializedName;

public class KeyInfo
{
    @SerializedName("temp_kf")
    private Double tempKf;

    private Double humidity;

    private Double pressure;

    @SerializedName("temp_max")
    private Double maxTemp;

    @SerializedName("sea_level")
    private Double seaLevel;

    @SerializedName("temp_min")
    private Double tempMin;

    private Double temp;

    @SerializedName("grnd_level")
    private Double grndLevel;

    public Double getTempKf() {
        return tempKf;
    }

    public void setTempKf(Double tempKf) {
        this.tempKf = tempKf;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Double grndLevel) {
        this.grndLevel = grndLevel;
    }

    @Override
    public String toString()
    {
        return "KeyInfo [tempKf = "+ tempKf +", humidity = "+humidity+", pressure = "+pressure+", maxTemp = "+ maxTemp +", seaLevel = "+ seaLevel +", tempMin = "+ tempMin +", temp = "+temp+", grndLevel = "+ grndLevel +"]";
    }
}