package com.minlia.cloud.infrastructure.framework.usercenter.exceptions;


import org.springframework.http.HttpStatus;


public class RestServiceException extends UserCenterException {
    public RestServiceException(HttpStatus httpStatusResponse, String message) {
        super(httpStatusResponse, message);
    }

    public RestServiceException(HttpStatus httpStatusResponse, String message, Throwable cause) {
        super(httpStatusResponse, message, cause);
    }
}
