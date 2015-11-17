package com.minlia.cloud.framework.common.web.controller;

import com.minlia.cloud.framework.common.api.Api;
import com.minlia.cloud.framework.common.persistence.model.IEntity;
import com.minlia.cloud.framework.common.persistence.service.IRawService;
import com.minlia.cloud.framework.common.web.RestPreconditions;
import com.minlia.cloud.framework.common.web.events.AfterResourceCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public abstract class AbstractController<T extends IEntity,S extends IRawService<T>> extends AbstractReadOnlyController<T,S> {

    // save/create/persist

    protected final void createInternal(final T resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        final T existingResource = getService().create(resource);

        // - note: mind the autoboxing and potential NPE when the resource has null id at this point (likely when working with DTOs)
        eventPublisher.publishEvent(new AfterResourceCreatedEvent<T>(clazz, uriBuilder, response, existingResource.getId().toString()));
    }

    // update

    /**
     * - note: the operation is IDEMPOTENT <br/>
     */
    protected final void updateInternal(final Serializable id, final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkRequestState(resource.getId() == id);
        RestPreconditions.checkNotNull(getService().findOne(resource.getId()));

        getService().update(resource);
    }

    // delete/remove

    protected final void deleteByIdInternal(final long id) {
        // InvalidDataAccessApiUsageException - ResourceNotFoundException
        // IllegalStateException - ResourceNotFoundException
        // DataAccessException - ignored
        getService().delete(id);
    }

}
