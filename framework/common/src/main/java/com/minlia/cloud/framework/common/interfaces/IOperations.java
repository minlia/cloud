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
package com.minlia.cloud.framework.common.interfaces;

import org.apache.commons.lang3.tuple.Triple;
import com.minlia.cloud.framework.common.search.ClientOperation;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

    // find - one

    T findOne(final Long id);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    List<T> findAll();

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    List<T> findAllSorted(final String sortBy, final String sortOrder);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    List<T> findAllPaginated(final int page, final int size);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder);

    // create

    T create(final T resource);

    // update

    void update(final T resource);

    // delete

    void delete(final Long id);

    void deleteAll();

    // count

    long count();

    // search

    List<T> searchAll(final Triple<String, ClientOperation, String>... constraints);

    T searchOne(final Triple<String, ClientOperation, String>... constraints);

}
