package com.minlia.cloud.infrastructure.framework.usercenter.rest.controllers;

import com.minlia.cloud.infrastructure.framework.usercenter.beans.ExceptionInfo;
import com.minlia.cloud.infrastructure.framework.usercenter.exceptions.RestServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = RestServiceException.class)
    public
    @ResponseBody
    ExceptionInfo handleBootException(RestServiceException exception, HttpServletRequest request,
                                      HttpServletResponse response) {
        LOG.error(exception.getMessage(), exception);
        response.setStatus(exception.getHttpStatusResponse().value());
        return new ExceptionInfo(exception.getLocalizedMessage(), exception.getHttpStatusResponse().value(), request.getRequestURI());
    }

}
