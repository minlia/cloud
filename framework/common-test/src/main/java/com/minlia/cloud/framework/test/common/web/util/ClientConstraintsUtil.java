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
