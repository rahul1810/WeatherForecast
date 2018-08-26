package com.finleap.rahulshekharweather.service;

import com.finleap.rahulshekharweather.response.ForecastSummaryResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ForecastSummaryService {

    @ApiOperation(value = "Returns 3 day average day, night temperature along with pressure", response = ForecastSummaryResponse.class)

    @ApiImplicitParam(
            name = "city",
            value = "City for which forecast is to be requested",
            required = true,
            dataType = "string",
            paramType = "query",
            example = "Berlin"
    )

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved forecast"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "The information you were trying to fetch could not be found")
    })

    @RequestMapping(value = "data", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ForecastSummaryResponse> getForecastSummary(@ApiParam(required = false, hidden = true)@RequestParam Map<String,String> allRequestParams, HttpServletResponse response) throws InterruptedException ;

}
