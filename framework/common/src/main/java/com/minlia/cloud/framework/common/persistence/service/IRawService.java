package com.minlia.cloud.framework.common.persistence.service;

import com.minlia.cloud.framework.common.interfaces.IOperations;
import com.minlia.cloud.framework.common.persistence.model.IEntity;
import com.minlia.cloud.framework.common.search.ClientOperation;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRawService<T extends IEntity> extends IOperations<T> {

    // search

    List<T> searchAll(final String queryString);

    List<T> searchPaginated(final String queryString, final int page, final int size);

    Page<T> searchPaginated(final int page, final int size, final Triple<String, ClientOperation, String>... constraints);

    Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder);

}
