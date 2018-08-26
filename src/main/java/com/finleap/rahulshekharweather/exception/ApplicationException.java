package com.finleap.rahulshekharweather.exception;

import com.finleap.rahulshekharweather.response.ResponseInfo;
import org.springframework.http.HttpStatus;

public class ApplicationException extends Exception{

    int code;
    HttpStatus httpStatus;

    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(int code, String message){
        super(message);
        this.code = code;
    }

    public ApplicationException(ResponseInfo responseInfo){
        super(responseInfo.getDescription());
        this.code = responseInfo.getCode();
        this.httpStatus = responseInfo.getHttpStatus();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
