package com.minlia.cloud.framework.common.persistence.filter;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Abstract specification for context supported specifications.
 * Also it uses distinct to remove duplications.
 */

public abstract class AbstractSpecification<T> implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        query.distinct(true);
        return toPredicate(new SpecificationContext(), root, query, cb);
    }

    public abstract Predicate toPredicate(SpecificationContext context, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);

}
