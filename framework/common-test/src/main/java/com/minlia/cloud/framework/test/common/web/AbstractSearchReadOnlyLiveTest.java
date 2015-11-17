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
package com.minlia.cloud.framework.test.common.web;

import com.minlia.cloud.framework.common.interfaces.IDto;
import com.minlia.cloud.framework.common.search.ClientOperation;
import com.minlia.cloud.framework.common.util.SearchField;
import com.minlia.cloud.framework.test.common.client.template.IRestTemplate;
import com.minlia.cloud.framework.test.common.web.util.ClientConstraintsUtil;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.minlia.cloud.framework.common.search.ClientOperation.ENDS_WITH;
import static com.minlia.cloud.framework.common.search.ClientOperation.EQ;
import static com.minlia.cloud.framework.common.spring.util.Profiles.CLIENT;
import static com.minlia.cloud.framework.common.spring.util.Profiles.TEST;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
@ActiveProfiles({ CLIENT, TEST })
public abstract class AbstractSearchReadOnlyLiveTest<T extends IDto> {

    public AbstractSearchReadOnlyLiveTest() {
        super();
    }

    // tests

    // by id

    public final void givenResourceWithIdDoesNotExist_whenResourceIsSearchedById_thenResourceIsNotFound() {
        // When
        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createConstraint(EQ, SearchField.id.toString(), randomNumeric(8)));

        // Then
        assertThat(found, Matchers.<T> empty());
    }

    // by name

    @Test
    public final void givenResourceWithNameDoesNotExist_whenResourceIsSearchedByName_thenResourceIsNotFound() {
        // When
        final List<T> found = getApi().searchAll(ClientConstraintsUtil.createNameConstraint(EQ, randomAlphabetic(8)));

        // Then
        assertThat(found, Matchers.<T> empty());
    }

    // starts with, ends with

    @Test
    public final void whenSearchByStartsWithIsPerformed_thenNoExceptions() {
        // When
        final ImmutableTriple<String, ClientOperation, String> nameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), ClientOperation.STARTS_WITH, randomAlphabetic(8));
        getApi().searchAll(nameConstraint);
    }

    @Test
    public final void whenSearchByEndsWithIsPerformed_thenNoExceptions() {
        // When
        final ImmutableTriple<String, ClientOperation, String> nameConstraint = new ImmutableTriple<String, ClientOperation, String>(SearchField.name.toString(), ENDS_WITH, randomAlphabetic(8));
        getApi().searchAll(nameConstraint);
    }

    // template

    protected abstract IRestTemplate<T> getApi();

}
