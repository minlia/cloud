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
package com.minlia.cloud.framework.common.web;

import com.minlia.cloud.framework.common.web.exception.MyConflictException;
import com.minlia.cloud.framework.common.web.exception.MyForbiddenException;
import com.minlia.cloud.framework.common.web.exception.MyResourceNotFoundException;
import org.springframework.http.HttpStatus;

/**
 * Simple static methods to be called at the start of your own methods to verify correct arguments and state. If the Precondition fails, an {@link HttpStatus} code is thrown
 */
public final class RestPreconditions {

    private RestPreconditions() {
        throw new AssertionError();
    }

    // API

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference
     *            an object reference
     * @return the non-null reference that was validated
     * @throws MyResourceNotFoundException
     *             if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new MyResourceNotFoundException();
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference
     *            an object reference
     * @return the non-null reference that was validated
     * @throws MyConflictException
     *             if {@code reference} is null
     */
    public static <T> T checkRequestElementNotNull(final T reference) {
        if (reference == null) {
            throw new MyConflictException();
        }
        return reference;
    }

    /**
     * Ensures the truth of an expression
     *
     * @param expression
     *            a boolean expression
     */
    public static void checkRequestState(final boolean expression) {
        if (!expression) {
            throw new MyConflictException();
        }
    }

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param expression
     *            has value true if found, otherwise false
     * @throws MyResourceNotFoundException
     *             if expression is false, means value not found.
     */
    public static void checkFound(final boolean expression) {
        if (!expression) {
            throw new MyResourceNotFoundException();
        }
    }

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param expression
     *            has value true if found, otherwise false
     * @throws MyResourceNotFoundException
     *             if expression is false, means value not found.
     */
    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }

        return resource;
    }

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param expression
     *            has value true if found, otherwise false
     * @throws MyForbiddenException
     *             if expression is false, means operation not allowed.
     */
    public static void checkAllowed(final boolean expression) {
        if (!expression) {
            throw new MyForbiddenException();
        }
    }

}
