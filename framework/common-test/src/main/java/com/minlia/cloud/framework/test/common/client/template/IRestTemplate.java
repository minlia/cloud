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

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import com.minlia.cloud.framework.client.marshall.IMarshaller;
import com.minlia.cloud.framework.client.template.ITemplateWithUri;
import com.minlia.cloud.framework.common.interfaces.IDto;
import com.minlia.cloud.framework.common.interfaces.IOperations;
import com.minlia.cloud.framework.common.search.ClientOperation;

import com.jayway.restassured.specification.RequestSpecification;

public interface IRestTemplate<T extends IDto> extends IOperations<T>, ITemplateAsResponse<T>, ITemplateWithUri<T> {

    // template

    RequestSpecification givenReadAuthenticated();

    IMarshaller getMarshaller();

    String getUri();

    // search

    List<T> searchPaginated(final Triple<String, ClientOperation, String> idOp, final Triple<String, ClientOperation, String> nameOp, final int page, final int size);

    // util

    Pair<String, String> getReadCredentials();

}
