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
package com.minlia.cloud.framework.common.web.controller;

import com.minlia.cloud.framework.common.persistence.model.IEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ISortingController<T extends IEntity> {

    public List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    public List<T> findAllPaginated(final int page, final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    public List<T> findAllSorted(final String sortBy, final String sortOrder);

    public List<T> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

}
