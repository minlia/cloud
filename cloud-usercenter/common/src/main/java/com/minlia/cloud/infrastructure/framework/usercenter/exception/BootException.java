package com.minlia.cloud.infrastructure.framework.usercenter.exception;


import org.springframework.http.HttpStatus;

public class BootException extends Exception {

    private HttpStatus httpStatusResponse;

    public BootException(HttpStatus httpStatusResponse, String message) {
        super(message);
        this.httpStatusResponse = httpStatusResponse;
    }

    public BootException(HttpStatus httpStatusResponse, String message, Throwable cause) {
        super(message, cause);
        this.httpStatusResponse = httpStatusResponse;
    }

    public HttpStatus getHttpStatusResponse() {
        return httpStatusResponse;
    }
}
