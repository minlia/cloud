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
package com.minlia.cloud.framework.web.validation;

import javax.validation.MessageInterpolator;
import javax.validation.ValidationException;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * Specific interpolation context management for resthub validation utilities
 *
 * Allows to manage messages and interpolations related to a {@link ConstraintDescriptor}
 * and a value. Managing the value allows to resolve dynamic message interpolation for error messages
 * containing parameters
 */
public class ValidationContext implements MessageInterpolator.Context {

    /**
     * Managed constraint descriptor
     */
    private final ConstraintDescriptor<?> constraintDescriptor;

    /**
     * Managed object value
     */
    private final Object validatedValue;

    public ValidationContext(ConstraintDescriptor<?> constraintDescriptor, Object validatedValue) {
        this.constraintDescriptor = constraintDescriptor;
        this.validatedValue = validatedValue;
    }

    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return constraintDescriptor;
    }

    public Object getValidatedValue() {
        return validatedValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unwrap(Class<T> type) {
        if (type.isAssignableFrom(ValidationContext.class)) {
            return type.cast(this);
        }
        throw new ValidationException(String.format("Type %s not supported for unwrapping", type));
    }

}
