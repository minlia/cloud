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
