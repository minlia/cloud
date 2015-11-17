package com.minlia.cloud.framework.common.web.controller;

import com.google.common.collect.Lists;
import com.minlia.cloud.framework.common.context.ApplicationContextHolder;
import com.minlia.cloud.framework.common.persistence.model.IEntity;
import com.minlia.cloud.framework.common.persistence.service.IRawService;
import com.minlia.cloud.framework.common.util.QueryConstants;
import com.minlia.cloud.framework.common.web.RestPreconditions;
import com.minlia.cloud.framework.common.web.WebConstants;
import com.minlia.cloud.framework.common.web.events.MultipleResourcesRetrievedEvent;
import com.minlia.cloud.framework.common.web.events.PaginatedResultsRetrievedEvent;
import com.minlia.cloud.framework.common.web.events.SingleResourceRetrievedEvent;
import com.minlia.cloud.framework.common.web.exception.MyResourceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public abstract class AbstractReadOnlyController<T extends IEntity,S extends IRawService<T>> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<T> clazz;

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    public AbstractReadOnlyController(){
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        clazz = (Class<T>) resolvableType.getSuperType().getGeneric().resolve();
    }

    // search

    public List<T> searchAllInternal(@RequestParam(QueryConstants.Q_PARAM) final String queryString) {
        return getService().searchAll(queryString);
    }

    public List<T> searchAllPaginatedInternal(@RequestParam(QueryConstants.Q_PARAM) final String queryString, final int page, final int size) {
        return getService().searchPaginated(queryString, page, size);
    }

    // find - one

    protected final T findOneInternal(final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final T resource = findOneInternal(id);
        eventPublisher.publishEvent(new SingleResourceRetrievedEvent<T>(clazz, uriBuilder, response));
        return resource;
    }

    protected final T findOneInternal(final Long id) {
        return RestPreconditions.checkNotNull(getService().findOne(id));
    }

    // find - all

    protected final List<T> findAllInternal(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        if (request.getParameterNames().hasMoreElements()) {
            throw new MyResourceNotFoundException();
        }

        eventPublisher.publishEvent(new MultipleResourcesRetrievedEvent<T>(clazz, uriBuilder, response));
        return getService().findAll();
    }

    protected final void findAllRedirectToPagination(final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final String resourceName = clazz.getSimpleName().toString().toLowerCase();
        final String locationValue = uriBuilder.path(WebConstants.PATH_SEP + resourceName).build().encode().toUriString() + QueryConstants.QUESTIONMARK + "page=0&size=10";

        response.setHeader(HttpHeaders.LOCATION, locationValue);
    }

    protected final List<T> findPaginatedAndSortedInternal(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<T> resultPage = getService().findAllPaginatedAndSortedRaw(page, size, sortBy, sortOrder);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<T>(clazz, uriBuilder, response, page, resultPage.getTotalPages(), size));

        return Lists.newArrayList(resultPage.getContent());
    }

    protected final List<T> findPaginatedInternal(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<T> resultPage = getService().findAllPaginatedAndSortedRaw(page, size, sortBy, sortOrder);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<T>(clazz, uriBuilder, response, page, resultPage.getTotalPages(), size));

        return Lists.newArrayList(resultPage.getContent());
    }

    protected final List<T> findAllSortedInternal(final String sortBy, final String sortOrder) {
        final List<T> resultPage = getService().findAllSorted(sortBy, sortOrder);
        return resultPage;
    }

    // count

    protected final long countInternal() {
        // InvalidDataAccessApiUsageException dataEx - ResourceNotFoundException
        return getService().count();
    }

    // generic REST operations

    // count

    /**
     * Counts all {@link } resources in the system
     * @return
     */
//    @RequestMapping(method = RequestMethod.GET, value = "/count")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public long count() {
        return countInternal();
    }

    // template method

    protected S getService(){
        ApplicationContext applicationContext=ApplicationContextHolder.getApplicationContext();
        Object object =   applicationContext.getBean(StringUtils.uncapitalize(this.clazz.getSimpleName()) + "ServiceImpl");
        return (S)object;
    }

}
