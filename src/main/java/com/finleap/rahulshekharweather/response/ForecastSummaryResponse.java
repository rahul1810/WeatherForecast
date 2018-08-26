package com.finleap.rahulshekharweather.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.finleap.rahulshekharweather.exception.ApplicationException;
import com.finleap.rahulshekharweather.model.ForecastSummary;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForecastSummaryResponse extends BaseResponse {

    private ForecastSummary forecastSummary;

    public ForecastSummaryResponse(ResponseInfo responseInfo){
        super(responseInfo.getCode(), responseInfo.getDescription());
    }

    public ForecastSummaryResponse(ApplicationException e){
        super(e.getCode(), e.getMessage());
    }

    public ForecastSummaryResponse(Integer statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }

    public ForecastSummary getForecastSummary() {
        return forecastSummary;
    }

    public void setForecastSummary(ForecastSummary forecastSummary) {
        this.forecastSummary = forecastSummary;
    }

    @Override
    public String toString() {
        return "ForecastSummaryResponse{" +
                "forecastSummary=" + forecastSummary +
                '}';
    }
}
