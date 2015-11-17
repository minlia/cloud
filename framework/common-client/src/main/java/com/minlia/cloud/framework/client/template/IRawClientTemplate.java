package com.minlia.cloud.framework.client.template;

import com.minlia.cloud.framework.common.interfaces.IDto;
import com.minlia.cloud.framework.common.interfaces.IOperations;
import org.apache.commons.lang3.tuple.Pair;

public interface IRawClientTemplate<T extends IDto> extends IOperations<T>, ITemplateWithUri<T> {

    //

    String getUri();

    // create

    T create(final T resource, final Pair<String, String> credentials);

    T findOne(final long id, final Pair<String, String> credentials);

}
