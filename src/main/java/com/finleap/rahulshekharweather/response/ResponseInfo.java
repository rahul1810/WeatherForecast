package com.finleap.rahulshekharweather.response;

import org.springframework.http.HttpStatus;

public enum ResponseInfo {

    INVALID_CITY(1001,"Information unavailable for the city you have entered", HttpStatus.NOT_FOUND),
    SUCCESS(200, "Success", HttpStatus.OK),
    ERROR_GETTING_RESPONSE_FROM_WEATHER_SERVICE(1002, "No Information received from weather service", HttpStatus.NOT_FOUND),
    UNSUPPORTED_QUERY_PARAMETERS(1003, "You have entered query parameters that are not supported", HttpStatus.BAD_REQUEST),
    NO_CITY_ENTERED(1004, "No city sent in request", HttpStatus.BAD_REQUEST);



    private final Integer code;
    private final String description;
    private final HttpStatus httpStatus;

    private ResponseInfo(Integer code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

}
