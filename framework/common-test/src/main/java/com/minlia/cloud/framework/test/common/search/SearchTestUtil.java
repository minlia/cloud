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
package com.minlia.cloud.framework.test.common.search;

import org.apache.commons.lang3.tuple.Triple;
import com.minlia.cloud.framework.client.util.SearchUriBuilder;
import com.minlia.cloud.framework.common.search.ClientOperation;
import com.minlia.cloud.framework.common.util.SearchField;

public final class SearchTestUtil {

    private SearchTestUtil() {
        throw new UnsupportedOperationException();
    }

    // API

    public static String constructQueryString(final String idVal, final String nameVal) {
        return new SearchUriBuilder().consume(ClientOperation.EQ, SearchField.id.toString(), idVal, false).consume(ClientOperation.EQ, SearchField.name.toString(), nameVal, false).build();
    }

    public static String constructQueryString(final String idVal, final boolean negatedId, final String nameVal, final boolean negatedName) {
        return new SearchUriBuilder().consume(ClientOperation.EQ, SearchField.id.toString(), idVal, negatedId).consume(ClientOperation.EQ, SearchField.name.toString(), nameVal, negatedName).build();
    }

    public static String constructQueryString(final Triple<String, ClientOperation, String> idConstraint, final Triple<String, ClientOperation, String> nameConstraint) {
        return new SearchUriBuilder().consume(idConstraint).consume(nameConstraint).build();
    }

}
