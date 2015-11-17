package com.minlia.cloud.framework.client.template;

import java.util.List;

import com.minlia.cloud.framework.common.interfaces.IDto;
import org.apache.commons.lang3.tuple.Pair;

public interface IReadOnlyTemplateWithUri<T extends IDto> {

    // find - one

    T findOneByUri(final String uri, final Pair<String, String> credentials);

    // find - all

    List<T> findAllByUri(final String uri, final Pair<String, String> credentials);

}
