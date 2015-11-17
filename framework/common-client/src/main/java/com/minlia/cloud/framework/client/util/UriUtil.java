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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.web.util.UriTemplate;

public final class UriUtil {

    private UriUtil() {
        throw new AssertionError();
    }

    // API

    public static URI createSearchUri(final String uriBase, final String paramToExpand) {
        URL url = null;
        try {
            url = new UriTemplate(uriBase).expand(paramToExpand).toURL();
        } catch (final MalformedURLException ex) {
            throw new IllegalArgumentException(ex);
        }

        try {
            return url.toURI();
        } catch (final URISyntaxException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
