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
package com.minlia.cloud.framework.common.web.listeners;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import static com.minlia.cloud.framework.common.util.LinkUtil.*;
import com.minlia.cloud.framework.common.web.IUriMapper;
import com.minlia.cloud.framework.common.web.events.PaginatedResultsRetrievedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static com.minlia.cloud.framework.common.util.LinkUtil.createLinkHeader;
import static com.minlia.cloud.framework.common.web.WebConstants.PATH_SEP;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
class PaginatedResultsRetrievedDiscoverabilityListener implements ApplicationListener<PaginatedResultsRetrievedEvent> {

    private static final String PAGE = "page";

    @Autowired
    private IUriMapper uriMapper;

    public PaginatedResultsRetrievedDiscoverabilityListener() {
        super();
    }

    // API

    @Override
    public final void onApplicationEvent(final PaginatedResultsRetrievedEvent ev) {
        Preconditions.checkNotNull(ev);

        addLinkHeaderOnPagedResourceRetrieval(ev.getUriBuilder(), ev.getResponse(), ev.getClazz(), ev.getPage(), ev.getTotalPages(), ev.getPageSize());
    }

    //

    // - note: at this point, the URI is transformed into plural (added `s`) in a hardcoded way - this will change in the future
    final void addLinkHeaderOnPagedResourceRetrieval(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final Class clazz, final int page, final int totalPages, final int pageSize) {
        plural(uriBuilder, clazz);

        final StringBuilder linkHeader = new StringBuilder();
        if (hasNextPage(page, totalPages)) {
            final String uriForNextPage = constructNextPageUri(uriBuilder, page, pageSize);
            linkHeader.append(createLinkHeader(uriForNextPage, REL_NEXT));
        }
        if (hasPreviousPage(page)) {
            final String uriForPrevPage = constructPrevPageUri(uriBuilder, page, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForPrevPage, REL_PREV));
        }
        if (hasFirstPage(page)) {
            final String uriForFirstPage = constructFirstPageUri(uriBuilder, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForFirstPage, REL_FIRST));
        }
        if (hasLastPage(page, totalPages)) {
            final String uriForLastPage = constructLastPageUri(uriBuilder, totalPages, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForLastPage, REL_LAST));
        }

        if (linkHeader.length() > 0) {
            response.addHeader(HttpHeaders.LINK, linkHeader.toString());
        }
    }

    final String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page + 1).replaceQueryParam("size", size).build().encode().toUriString();
    }

    final String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page - 1).replaceQueryParam("size", size).build().encode().toUriString();
    }

    final String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, 0).replaceQueryParam("size", size).build().encode().toUriString();
    }

    final String constructLastPageUri(final UriComponentsBuilder uriBuilder, final int totalPages, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, totalPages).replaceQueryParam("size", size).build().encode().toUriString();
    }

    final boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages - 1;
    }

    final boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    final boolean hasFirstPage(final int page) {
        return hasPreviousPage(page);
    }

    final boolean hasLastPage(final int page, final int totalPages) {
        return totalPages > 1 && hasNextPage(page, totalPages);
    }

    final void appendCommaIfNecessary(final StringBuilder linkHeader) {
        if (linkHeader.length() > 0) {
            linkHeader.append(", ");
        }
    }

    // template

    protected void plural(final UriComponentsBuilder uriBuilder, final Class clazz) {
        final String resourceName = uriMapper.getUriBase(clazz);
        uriBuilder.path(PATH_SEP + resourceName);
    }

}
