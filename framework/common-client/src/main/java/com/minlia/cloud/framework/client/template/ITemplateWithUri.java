package com.minlia.cloud.framework.client.template;

import com.minlia.cloud.framework.common.interfaces.IDto;
import org.apache.commons.lang3.tuple.Pair;

public interface ITemplateWithUri<T extends IDto> extends IReadOnlyTemplateWithUri<T> {

    // create

    String createAsUri(final T resource, final Pair<String, String> credentials);

    String createAsUri(final T resource);

}
