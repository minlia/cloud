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
package com.minlia.cloud.framework.common.util;

import javax.servlet.http.HttpServletResponse;

/**
 * Provides some constants and utility methods to build a Link Header to be stored in the {@link HttpServletResponse} object
 */
public final class LinkUtil {

    public static final String REL_COLLECTION = "collection";
    public static final String REL_NEXT = "next";
    public static final String REL_PREV = "prev";
    public static final String REL_FIRST = "first";
    public static final String REL_LAST = "last";

    private LinkUtil() {
        throw new AssertionError();
    }

    //

    /**
     * Creates a Link Header to be stored in the {@link HttpServletResponse} to provide Discoverability features to the user
     *
     * @param uri
     *            the base uri
     * @param rel
     *            the relative path
     *
     * @return the complete url
     */
    public static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }

    public static String gatherLinkHeaders(final String... uris) {
        final StringBuilder linkHeaderValue = new StringBuilder();
        for (final String uri : uris) {
            linkHeaderValue.append(uri);
            linkHeaderValue.append(", ");
        }
        return linkHeaderValue.substring(0, linkHeaderValue.length() - 2).toString();
    }

}
