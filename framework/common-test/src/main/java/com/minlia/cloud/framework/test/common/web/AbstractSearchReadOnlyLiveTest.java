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
