package com.finleap.rahulshekharweather.response;

import io.swagger.annotations.ApiModelProperty;

public class BaseResponse {

    @ApiModelProperty(required = true)
    private Integer statusCode;

    @ApiModelProperty(required = true)
    private String statusMessage;

    public BaseResponse(Integer statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
