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
package com.minlia.cloud.framework.client.util;

import com.minlia.cloud.framework.client.marshall.IMarshaller;
import com.minlia.cloud.framework.common.security.SpringSecurityUtil;
import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

    private HeaderUtil() {
        throw new AssertionError();
    }

    // API

    public static HttpHeaders createContentTypeHeaders(final IMarshaller marshaller) {
        final HttpHeaders headers = new HttpHeaders() {
            {
                set(com.google.common.net.HttpHeaders.CONTENT_TYPE, marshaller.getMime());
            }
        };
        return headers;
    }

    public static HttpHeaders createAcceptHeaders(final IMarshaller marshaller) {
        final HttpHeaders headers = new HttpHeaders() {
            {
                set(com.google.common.net.HttpHeaders.ACCEPT, marshaller.getMime());
            }
        };
        return headers;
    }

    public static HttpHeaders createAcceptAndBasicAuthHeaders(final IMarshaller marshaller, final String username, final String password) {
        final HttpHeaders headers = HeaderUtil.createAcceptHeaders(marshaller);
        final String basicAuthorizationHeader = HeaderUtil.createBasicAuthenticationAuthorizationHeader(username, password);
        headers.set(com.google.common.net.HttpHeaders.AUTHORIZATION, basicAuthorizationHeader);

        return headers;
    }

    public static HttpHeaders createContentTypeAndBasicAuthHeaders(final IMarshaller marshaller, final String username, final String password) {
        final String basicAuthorizationHeader = HeaderUtil.createBasicAuthenticationAuthorizationHeader(username, password);
        return createContentTypeAndBasicAuthHeaders(marshaller, basicAuthorizationHeader);
    }

    public static HttpHeaders createContentTypeAndBasicAuthHeaders(final IMarshaller marshaller, final String basicAuthorizationHeader) {
        final HttpHeaders headers = HeaderUtil.createContentTypeHeaders(marshaller);
        headers.set(com.google.common.net.HttpHeaders.AUTHORIZATION, basicAuthorizationHeader);
        return headers;
    }

    public static String createBasicAuthenticationAuthorizationHeader(final String username, final String password) {
        return "Basic " + SpringSecurityUtil.encodeAuthorizationKey(username, password);
    }

}
