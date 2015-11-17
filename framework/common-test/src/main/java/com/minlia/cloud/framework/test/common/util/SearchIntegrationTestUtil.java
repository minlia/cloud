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
package com.minlia.cloud.framework.test.common.util;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import com.minlia.cloud.framework.common.interfaces.IOperations;
import com.minlia.cloud.framework.common.search.ClientOperation;
import com.minlia.cloud.framework.common.util.SearchField;

@SuppressWarnings("unchecked")
public final class SearchIntegrationTestUtil {

    private SearchIntegrationTestUtil() {
        throw new AssertionError();
    }

    // API

    public static <T extends Serializable> void givenResourceExists_whenSearchByStartsWithEntireKeyIsPerformed_thenResourceIsFound(final IOperations<T> api, final T newEntity, final SearchField key, final ClientOperation op, final String value) {
        final T existingResource = api.create(newEntity);

        // When
        final ImmutableTriple<String, ClientOperation, String> constraint = new ImmutableTriple<String, ClientOperation, String>(key.toString(), op, value);
        final List<T> searchResults = api.searchAll(constraint);

        // Then
        assertThat(searchResults, hasItem(existingResource));
    }

    public static <T extends Serializable> void givenResourceExists_whenSearchByStartsWithPartOfKeyIsPerformed_thenResourceIsFound(final IOperations<T> api, final T newEntity, final SearchField key, final ClientOperation op, final String value) {
        final T existingResource = api.create(newEntity);
        final String partOfValue = value.substring(0, 2);

        // When
        final ImmutableTriple<String, ClientOperation, String> containsConstraint = new ImmutableTriple<String, ClientOperation, String>(key.toString(), op, partOfValue);
        final List<T> searchResults = api.searchAll(containsConstraint);

        // Then
        assertThat(searchResults, hasItem(existingResource));
    }

    public static <T extends Serializable> void givenResourceExists_whenSearchByEndsWithPartOfNameIsPerformed_thenResourceIsFound(final IOperations<T> api, final T newEntity, final SearchField key, final ClientOperation op, final String value) {
        final T existingResource = api.create(newEntity);
        final String partOfValue = value.substring(2);

        // When
        final ImmutableTriple<String, ClientOperation, String> containsConstraint = new ImmutableTriple<String, ClientOperation, String>(key.toString(), op, partOfValue);
        final List<T> searchResults = api.searchAll(containsConstraint);

        // Then
        assertThat(searchResults, hasItem(existingResource));
    }

    public static <T extends Serializable> void givenResourceExists_whenSearchByEndsWithEntireKeyIsPerformed_thenResourceIsFound(final IOperations<T> api, final T newEntity, final SearchField key, final ClientOperation op, final String value) {
        final T existingEntity = api.create(newEntity);

        // When
        final ImmutableTriple<String, ClientOperation, String> constraint = new ImmutableTriple<String, ClientOperation, String>(key.toString(), op, value);
        final List<T> searchResults = api.searchAll(constraint);

        // Then
        assertThat(searchResults, hasItem(existingEntity));
    }

    public static <T extends Serializable> void givenResourceExists_whenSearchByEndsWithIncorrectPartOfKeyIsPerformed_thenResourceIsNotFound(final IOperations<T> api, final T existingEntity, final SearchField key, final ClientOperation op,
            final String value) {
        final String partOfValue = value.substring(2, 5);

        // When
        final ImmutableTriple<String, ClientOperation, String> containsConstraint = new ImmutableTriple<String, ClientOperation, String>(key.toString(), op, partOfValue);
        final List<T> searchResults = api.searchAll(containsConstraint);

        // Then
        assertThat(searchResults, not(hasItem(existingEntity)));
    }

    public static <T extends Serializable> void givenResourceExists_whenSearchByStartsWithPartOfLowerCaseNameIsPerformed_thenResourceIsFound(final IOperations<T> api, final T newEntity, final SearchField key, final ClientOperation op, final String value) {
        final T existingResource = api.create(newEntity);
        final String partOfValue = value.substring(2);

        // When
        final ImmutableTriple<String, ClientOperation, String> containsConstraint = new ImmutableTriple<String, ClientOperation, String>(key.toString(), op, partOfValue);
        final List<T> searchResults = api.searchAll(containsConstraint);

        // Then
        assertThat(searchResults, hasItem(existingResource));
    }

}
