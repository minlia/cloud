package com.minlia.cloud.framework.common.web.controller;

import com.minlia.cloud.framework.common.persistence.model.IEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ISortingController<T extends IEntity> {

    public List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    public List<T> findAllPaginated(final int page, final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    public List<T> findAllSorted(final String sortBy, final String sortOrder);

    public List<T> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

}
