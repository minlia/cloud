/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/solution/usercenter/2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
