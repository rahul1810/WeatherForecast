package com.finleap.rahulshekharweather.service;

import com.finleap.rahulshekharweather.exception.ApplicationException;
import com.finleap.rahulshekharweather.manager.ForecastSummaryManager;
import com.finleap.rahulshekharweather.model.ForecastSummary;
import com.finleap.rahulshekharweather.response.ForecastSummaryResponse;
import com.finleap.rahulshekharweather.response.ResponseInfo;
import com.finleap.rahulshekharweather.util.ArgsValidator;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ForecastSummaryServiceImpl implements ForecastSummaryService {

    @Autowired
    ForecastSummaryManager forecastSummaryManager;

    @Autowired
    ArgsValidator argsValidator;

    @RequestMapping(value = "data", method = RequestMethod.GET)
    public ResponseEntity<ForecastSummaryResponse> getForecastSummary(@ApiParam(required = false, hidden = true)@RequestParam Map<String,String> allRequestParams, HttpServletResponse response) throws InterruptedException {
        ForecastSummary forecastSummary = null;
        try {
            String[] validArgs = {"city"};
            argsValidator.validate(validArgs, allRequestParams.keySet());
            String city = allRequestParams.get("city");
            System.out.println("City: " + city);
            forecastSummary = forecastSummaryManager.getSummaryByCity(city);
        } catch (ApplicationException e) {
            ForecastSummaryResponse forecastSummaryResponse = new ForecastSummaryResponse(e);
            return new ResponseEntity<>(forecastSummaryResponse, e.getHttpStatus());
        }
        ForecastSummaryResponse forecastSummaryResponse = new ForecastSummaryResponse(ResponseInfo.SUCCESS);
        forecastSummaryResponse.setForecastSummary(forecastSummary);
        return new ResponseEntity<>(forecastSummaryResponse, HttpStatus.OK);
    }
}
