package com.finleap.rahulshekharweather.model;

import com.google.gson.annotations.SerializedName;

public class WeatherData
{

    @SerializedName("dt")
    private Long epochTime;

    @SerializedName("dt_txt")
    private String strDate;

    @SerializedName("main")
    private KeyInfo keyInfo;

    public Long getEpochTime() {
        return epochTime;
    }

    public void setEpochTime(Long epochTime) {
        this.epochTime = epochTime;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    public void setKeyInfo(KeyInfo keyInfo) {
        this.keyInfo = keyInfo;
    }

    @Override
    public String toString()
    {
        return "WeatherData [epochTime = "+ epochTime +", strDate = "+strDate+", keyInfo = "+keyInfo+"]";
    }
}
