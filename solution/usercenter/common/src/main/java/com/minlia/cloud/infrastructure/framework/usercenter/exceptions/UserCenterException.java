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
