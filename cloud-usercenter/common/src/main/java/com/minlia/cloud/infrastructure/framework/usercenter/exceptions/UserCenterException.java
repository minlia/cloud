package com.minlia.cloud.infrastructure.framework.usercenter.exceptions;


import org.springframework.http.HttpStatus;

public class UserCenterException extends Exception {

    private HttpStatus httpStatusResponse;

    public UserCenterException(HttpStatus httpStatusResponse, String message) {
        super(message);
        this.httpStatusResponse = httpStatusResponse;
    }

    public UserCenterException(HttpStatus httpStatusResponse, String message, Throwable cause) {
        super(message, cause);
        this.httpStatusResponse = httpStatusResponse;
    }

    public HttpStatus getHttpStatusResponse() {
        return httpStatusResponse;
    }
}
