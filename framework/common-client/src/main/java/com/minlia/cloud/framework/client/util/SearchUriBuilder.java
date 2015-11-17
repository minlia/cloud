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
package com.minlia.cloud.framework.client.util;

import com.minlia.cloud.framework.common.search.ClientOperation;
import com.minlia.cloud.framework.common.util.QueryConstants;
import org.apache.commons.lang3.tuple.Triple;

public final class SearchUriBuilder {
    private StringBuilder uri;

    public SearchUriBuilder() {
        uri = new StringBuilder();
    }

    // API

    public final SearchUriBuilder consume(final Triple<String, ClientOperation, String> constraint) {
        final ClientOperation operation = (constraint == null) ? null : constraint.getMiddle();
        final boolean negated = (constraint == null) ? false : constraint.getMiddle().isNegated();
        final String key = (constraint == null) ? null : constraint.getLeft();
        final String value = (constraint == null) ? null : constraint.getRight();

        return consume(operation, key, value, negated);
    }

    public final SearchUriBuilder consume(final ClientOperation op, final String key, final String value, final boolean negated) {
        if (value != null) {
            addInterFragmentSeparatorIfNecessary();
            final String idFragment = constructFragment(op, negated, key, value);
            uri.append(idFragment);
        }

        return this;
    }

    // util

    private String constructFragment(final ClientOperation operation, final boolean negated, final String key, final String value) {
        final String op = constructOperationString(negated);
        final String fragment = key + op + constructStringQueryValue(value, operation);
        return fragment;
    }

    private String constructOperationString(final boolean negated) {
        return (negated) ? QueryConstants.NEGATION + QueryConstants.OP : QueryConstants.OP;
    }

    private void addInterFragmentSeparatorIfNecessary() {
        if (uri.length() != 0) {
            uri.append(QueryConstants.SEPARATOR);
        }
    }

    public String constructStringQueryValue(final String name, final ClientOperation op) {
        switch (op) {
        case CONTAINS:
            return QueryConstants.ANY_CLIENT + name + QueryConstants.ANY_CLIENT;
        case NEG_CONTAINS:
            return QueryConstants.ANY_CLIENT + name + QueryConstants.ANY_CLIENT;

        case STARTS_WITH:
            return name + QueryConstants.ANY_CLIENT;
        case NEG_STARTS_WITH:
            return name + QueryConstants.ANY_CLIENT;

        case ENDS_WITH:
            return QueryConstants.ANY_CLIENT + name;
        case NEG_ENDS_WITH:
            return QueryConstants.ANY_CLIENT + name;

        default:
            break;
        }
        return name;
    }

    public final String build() {
        return uri.toString();
    }

}
