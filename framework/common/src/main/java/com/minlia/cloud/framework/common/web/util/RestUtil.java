/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/framework/2015
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
package com.minlia.cloud.framework.common.web.util;

import com.minlia.cloud.framework.common.web.exception.MyConflictException;
import com.minlia.cloud.framework.common.web.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

public final class RestUtil {

    private RestUtil() {
        throw new AssertionError();
    }

    //

    /**
     * This is to be used when a Create REST request is sent to another service - if the response does not have the expected 201 status code, then an exception is thrown
     *
     * @param createResponse
     *            the response returned on create.
     * @param message
     *            the message to show if failed to create.
     */
    public static void propagateStatusCodeOnCreate(final ResponseEntity<?> createResponse, final String message) {
        if (createResponse.getStatusCode().value() == 409) {
            throw new MyConflictException(message);
        }
        if (createResponse.getStatusCode().value() != 201) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * This is to be used when an exception is
     *
     * @param ex
     *            the not client status code exception.
     * @param message
     *            the message to show if failed to created
     */
    public static void propagateStatusCodeOnException(final HttpStatusCodeException ex, final String message) {
        if (ex.getStatusCode().value() == 409) {
            throw new ValidationException(ex.getStatusText());
        }

        throw new IllegalStateException(message);
    }
}
