package com.minlia.cloud.framework.common.persistence.service;

import com.minlia.cloud.framework.common.persistence.model.IEntity;
import org.springframework.core.ResolvableType;

public abstract class AbstractService<T extends IEntity> extends AbstractRawService<T>implements IService<T> {

    // API

    // find - one

}
