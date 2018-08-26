package com.finleap.rahulshekharweather.model;

public class ForecastSummary {

    Double averagePressure;
    Double averageDailyTemperature;
    Double averageNightlyTemeperature;

    public ForecastSummary(Double averagePressure, Double averageDailyTemperature, Double averageNightlyTemeperature) {
        this.averagePressure = averagePressure;
        this.averageDailyTemperature = averageDailyTemperature;
        this.averageNightlyTemeperature = averageNightlyTemeperature;
    }

    public Double getAveragePressure() {
        return averagePressure;
    }

    public void setAveragePressure(Double averagePressure) {
        this.averagePressure = averagePressure;
    }

    public Double getAverageDailyTemperature() {
        return averageDailyTemperature;
    }

    public void setAverageDailyTemperature(Double averageDailyTemperature) {
        this.averageDailyTemperature = averageDailyTemperature;
    }

    public Double getAverageNightlyTemeperature() {
        return averageNightlyTemeperature;
    }

    public void setAverageNightlyTemeperature(Double averageNightlyTemeperature) {
        this.averageNightlyTemeperature = averageNightlyTemeperature;
    }

    @Override
    public String toString() {
        return "ForecastSummary{" +
                "averagePressure=" + averagePressure +
                ", averageDailyTemperature=" + averageDailyTemperature +
                ", averageNightlyTemeperature=" + averageNightlyTemeperature +
                '}';
    }
}
