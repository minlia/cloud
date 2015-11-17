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
package com.minlia.cloud.framework.common.persistence.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.minlia.cloud.framework.common.context.ApplicationContextHolder;
import com.minlia.cloud.framework.common.persistence.service.util.ServicePreconditions;
import com.minlia.cloud.framework.common.persistence.event.*;
import com.minlia.cloud.framework.common.persistence.model.IEntity;
import com.minlia.cloud.framework.common.search.ClientOperation;
import com.minlia.cloud.framework.common.util.SearchCommonUtil;
import com.minlia.cloud.framework.common.web.exception.MyBadRequestException;
import com.minlia.cloud.framework.common.web.exception.MyConflictException;
import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractRawService<T extends IEntity> implements IRawService<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Class<T> clazz;

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    public AbstractRawService(){
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        clazz = (Class<T>) resolvableType.getSuperType().getGeneric().resolve();
    }


    // API

    // search

    @SuppressWarnings("unchecked")
    @Override
    public List<T> searchAll(final String queryString) {
        Preconditions.checkNotNull(queryString);
        List<Triple<String, ClientOperation, String>> parsedQuery = null;
        try {
            parsedQuery = SearchCommonUtil.parseQueryString(queryString);
        } catch (final IllegalStateException illState) {
            logger.error("IllegalStateException on find operation");
            logger.warn("IllegalStateException on find operation", illState);
            throw new MyBadRequestException(illState);
        }

        final List<T> results = searchAll(parsedQuery.toArray(new ImmutableTriple[parsedQuery.size()]));
        return results;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<T> searchPaginated(final String queryString, final int page, final int size) {
        List<Triple<String, ClientOperation, String>> parsedQuery = null;
        try {
            parsedQuery = SearchCommonUtil.parseQueryString(queryString);
        } catch (final IllegalStateException illState) {
            logger.error("IllegalStateException on find operation");
            logger.warn("IllegalStateException on find operation", illState);
            throw new MyConflictException(illState);
        }

        final Page<T> resultPage = searchPaginated(page, size, parsedQuery.toArray(new ImmutableTriple[parsedQuery.size()]));
        return Lists.newArrayList(resultPage.getContent());
    }

    @Override
    public List<T> searchAll(final Triple<String, ClientOperation, String>... constraints) {
        Preconditions.checkState(constraints != null);
        Preconditions.checkState(constraints.length > 0);
        final Specification<T> firstSpec = resolveConstraint(constraints[0]);
        Specifications<T> specifications = Specifications.where(firstSpec);
        for (int i = 1; i < constraints.length; i++) {
            specifications = specifications.and(resolveConstraint(constraints[i]));
        }
        if (firstSpec == null) {
            return Lists.newArrayList();
        }

        return getSpecificationExecutor().findAll(specifications);
    }

    @Override
    public T searchOne(final Triple<String, ClientOperation, String>... constraints) {
        Preconditions.checkState(constraints != null);
        Preconditions.checkState(constraints.length > 0);
        final Specification<T> firstSpec = resolveConstraint(constraints[0]);
        Specifications<T> specifications = Specifications.where(firstSpec);
        for (int i = 1; i < constraints.length; i++) {
            specifications = specifications.and(resolveConstraint(constraints[i]));
        }
        if (firstSpec == null) {
            return null;
        }

        return getSpecificationExecutor().findOne(specifications);
    }

    @Override
    public Page<T> searchPaginated(final int page, final int size, final Triple<String, ClientOperation, String>... constraints) {
        final Specification<T> firstSpec = resolveConstraint(constraints[0]);
        Preconditions.checkState(firstSpec != null);
        Specifications<T> specifications = Specifications.where(firstSpec);
        for (int i = 1; i < constraints.length; i++) {
            specifications = specifications.and(resolveConstraint(constraints[i]));
        }

        return getSpecificationExecutor().findAll(specifications, new PageRequest(page, size, null));
    }

    // find - one

    @Override
    @Transactional(readOnly = true)
    public T findOne(final Long id) {
        return getDao().findOne(id);
    }

    // find - all

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        return getDao().findAll(new PageRequest(page, size, sortInfo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        final List<T> content = getDao().findAll(new PageRequest(page, size, sortInfo)).getContent();
        if (content == null) {
            return Lists.newArrayList();
        }
        return content;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAllPaginated(final int page, final int size) {
        final List<T> content = getDao().findAll(new PageRequest(page, size, null)).getContent();
        if (content == null) {
            return Lists.newArrayList();
        }
        return content;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAllSorted(final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        return Lists.newArrayList(getDao().findAll(sortInfo));
    }

    // save/create/persist

    @Override
    public T create(final T entity) {
        Preconditions.checkNotNull(entity);

        eventPublisher.publishEvent(new BeforeEntityCreateEvent<T>(this, clazz, entity));
        final T persistedEntity = getDao().save(entity);
        eventPublisher.publishEvent(new AfterEntityCreateEvent<T>(this, clazz, persistedEntity));

        return persistedEntity;
    }

    // update/merge

    @Override
    public void update(final T entity) {
        Preconditions.checkNotNull(entity);

        eventPublisher.publishEvent(new BeforeEntityUpdateEvent<T>(this, clazz, entity));
        getDao().save(entity);
        eventPublisher.publishEvent(new AfterEntityUpdateEvent<T>(this, clazz, entity));
    }

    // delete

    @Override
    public void deleteAll() {
        getDao().deleteAll();
        eventPublisher.publishEvent(new AfterEntitiesDeletedEvent<T>(this, clazz));
    }

    @Override
    public void delete(final Long id) {
        final T entity = getDao().findOne(id);
        ServicePreconditions.checkEntityExists(entity);

        eventPublisher.publishEvent(new BeforeEntityDeleteEvent<T>(this, clazz, entity));
        getDao().delete(entity);
        eventPublisher.publishEvent(new AfterEntityDeleteEvent<T>(this, clazz, entity));
    }

    // count

    @Override
    public long count() {
        return getDao().count();
    }

    // template method

    protected PagingAndSortingRepository<T, Long> getDao(){
        return (PagingAndSortingRepository) getRepository();
    }

    protected JpaSpecificationExecutor<T> getSpecificationExecutor(){

        return (JpaSpecificationExecutor) getRepository();
    }


    private Object getRepository(){
        org.springframework.context.ApplicationContext applicationContext=ApplicationContextHolder.getApplicationContext();
        Object object =   applicationContext.getBean(StringUtils.uncapitalize(this.clazz.getSimpleName())+"Repository");
        return object;
    }



    @SuppressWarnings({ "static-method", "unused" })
    public Specification<T> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        throw new UnsupportedOperationException();
    }

    // template

    protected final Sort constructSort(final String sortBy, final String sortOrder) {
        Sort sortInfo = null;
        if (sortBy != null) {
            sortInfo = new Sort(Direction.fromString(sortOrder), sortBy);
        }
        return sortInfo;
    }

}
