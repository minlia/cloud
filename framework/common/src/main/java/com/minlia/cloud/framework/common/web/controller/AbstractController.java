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
