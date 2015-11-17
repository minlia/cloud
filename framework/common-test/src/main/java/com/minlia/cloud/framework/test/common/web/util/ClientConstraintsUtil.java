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
package com.minlia.cloud.framework.test.common.web.util;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import com.minlia.cloud.framework.common.search.ClientOperation;
import com.minlia.cloud.framework.common.util.SearchField;

import java.io.Serializable;

public class ClientConstraintsUtil {

    private ClientConstraintsUtil() {
        throw new AssertionError();
    }

    //

    public static Triple<String, ClientOperation, String> createNameConstraint(final ClientOperation operation, final String nameValue) {
        return createConstraint(operation, SearchField.name.toString(), nameValue);
    }

    public static Triple<String, ClientOperation, String> createIdConstraint(final ClientOperation operation, final Serializable idValue) {
        return createConstraint(operation, SearchField.id.toString(), idValue.toString());
    }

    public static Triple<String, ClientOperation, String> createConstraint(final ClientOperation operation, final String key, final String value) {
        return new ImmutableTriple<String, ClientOperation, String>(key, operation, value);
    }

}
