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
package com.minlia.cloud.framework.test.common.client.template;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import com.minlia.cloud.framework.common.interfaces.IDto;
import com.minlia.cloud.framework.common.search.ClientOperation;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public interface ITemplateAsResponse<T extends IDto> {

    // find - one

    Response findOneAsResponse(final long id, final RequestSpecification req);

    Response findOneByUriAsResponse(final String uriOfResource, final RequestSpecification req);

    // find - all

    Response findAllByUriAsResponse(final String uriOfResource, final RequestSpecification req);

    Response findAllAsResponse(final RequestSpecification req);

    Response findAllPaginatedAsResponse(final int page, final int size, final RequestSpecification req);

    Response findAllSortedAsResponse(final String sortBy, final String sortOrder, final RequestSpecification req);

    Response findAllPaginatedAndSortedAsResponse(final int page, final int size, final String sortBy, final String sortOrder, final RequestSpecification req);

    // search

    Response searchAsResponse(final Triple<String, ClientOperation, String> idOp, final Triple<String, ClientOperation, String> nameOp);

    Response searchAsResponse(final Triple<String, ClientOperation, String> idOp, final Triple<String, ClientOperation, String> nameOp, final int page, final int size);

    Response searchAsResponse(final Triple<String, ClientOperation, String>... constraints);

    // create

    Response createAsResponse(final T resource);

    Response createAsResponse(final T resource, final Pair<String, String> credentials);

    // update

    Response updateAsResponse(final T resource);

    // delete

    Response deleteAsResponse(final String uriOfResource);

    // count

    Response countAsResponse();

}
