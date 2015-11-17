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
import com.minlia.cloud.framework.common.util.LinkUtil;
import com.minlia.cloud.framework.common.web.IUriMapper;
import com.minlia.cloud.framework.common.web.WebConstants;
import com.minlia.cloud.framework.common.web.events.MultipleResourcesRetrievedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("rawtypes")
@Component
final class MultipleResourcesRetrievedDiscoverabilityListener implements ApplicationListener<MultipleResourcesRetrievedEvent> {

    @Autowired
    private IUriMapper uriMapper;

    public MultipleResourcesRetrievedDiscoverabilityListener() {
        super();
    }

    //

    @Override
    public final void onApplicationEvent(final MultipleResourcesRetrievedEvent ev) {
        Preconditions.checkNotNull(ev);

        discoverOtherRetrievalOperations(ev.getUriBuilder(), ev.getResponse(), ev.getClazz());
    }

    @SuppressWarnings("unchecked")
    final void discoverOtherRetrievalOperations(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final Class clazz) {
        final String uriForResourceCreation = uriBuilder.path(WebConstants.PATH_SEP + uriMapper.getUriBase(clazz) + "/q=name=something").build().encode().toUriString();

        final String linkHeaderValue = LinkUtil.createLinkHeader(uriForResourceCreation, LinkUtil.REL_COLLECTION);
        response.addHeader(HttpHeaders.LINK, linkHeaderValue);
    }

}
