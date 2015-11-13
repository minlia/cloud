package com.minlia.cloud.infrastructure.framework.usercenter.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Maximum limit of requests reached")
public class ThrottlingException extends UserCenterException {

    public ThrottlingException() {
        super(HttpStatus.FORBIDDEN, "Maximum limit of requests reached");
    }

}
