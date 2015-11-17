package com.minlia.cloud.framework.common.web;

import com.minlia.cloud.framework.common.persistence.model.IEntity;

public interface IUriMapper {

    <T extends IEntity> String getUriBase(final Class<T> clazz);

}
