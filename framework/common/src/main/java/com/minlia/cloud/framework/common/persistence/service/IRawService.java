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
