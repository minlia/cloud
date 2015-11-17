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
