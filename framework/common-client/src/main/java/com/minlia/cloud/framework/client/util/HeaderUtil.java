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
