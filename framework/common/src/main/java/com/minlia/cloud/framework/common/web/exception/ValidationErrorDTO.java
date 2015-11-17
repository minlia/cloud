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
package com.minlia.cloud.framework.common.web.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO {

    private final List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

    public ValidationErrorDTO() {
        super();
    }

    //

    public final void addFieldError(final String path, final String message) {
        final FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }

    public final List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    //

    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ValidationErrorDTO [fieldErrors=").append(fieldErrors).append("]");
        return builder.toString();
    }

}
