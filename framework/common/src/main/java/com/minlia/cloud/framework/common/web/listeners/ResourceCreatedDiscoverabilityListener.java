package com.minlia.cloud.framework.common.web.listeners;

import com.google.common.base.Preconditions;
import com.minlia.cloud.framework.common.web.IUriMapper;
import org.apache.http.HttpHeaders;
import com.minlia.cloud.framework.common.web.events.AfterResourceCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static com.minlia.cloud.framework.common.web.WebConstants.PATH_SEP;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class ResourceCreatedDiscoverabilityListener implements ApplicationListener<AfterResourceCreatedEvent> {

    @Autowired
    private IUriMapper uriMapper;

    public ResourceCreatedDiscoverabilityListener() {
        super();
    }

    //

    @Override
    public final void onApplicationEvent(final AfterResourceCreatedEvent ev) {
        Preconditions.checkNotNull(ev);

        final String idOfNewResource = ev.getIdOfNewResource();
        addLinkHeaderOnEntityCreation(ev.getUriBuilder(), ev.getResponse(), idOfNewResource, ev.getClazz());
    }

    /**
     * - note: at this point, the URI is transformed into plural (added `s`) in a hardcoded way - this will change in the future
     */
    protected void addLinkHeaderOnEntityCreation(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final String idOfNewEntity, final Class clazz) {
        final String path = calculatePathToResource(clazz);
        final String locationValue = uriBuilder.path(path).build().expand(idOfNewEntity).encode().toUriString();

        response.setHeader(HttpHeaders.LOCATION, locationValue);
    }

    protected String calculatePathToResource(final Class clazz) {
        final String resourceName = uriMapper.getUriBase(clazz);
        final String path = PATH_SEP + resourceName + "/{id}";
        return path;
    }

}
