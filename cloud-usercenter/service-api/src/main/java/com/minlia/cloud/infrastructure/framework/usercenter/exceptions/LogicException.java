package com.minlia.cloud.infrastructure.framework.usercenter.exception;


import com.minlia.cloud.infrastructure.framework.usercenter.exception.BootException;
import org.springframework.http.HttpStatus;


public class LogicException extends BootException {
    public LogicException(HttpStatus httpStatusResponse, String message) {
        super(httpStatusResponse, message);
    }

    public LogicException(HttpStatus httpStatusResponse, String message, Throwable cause) {
        super(httpStatusResponse, message, cause);
    }
}
