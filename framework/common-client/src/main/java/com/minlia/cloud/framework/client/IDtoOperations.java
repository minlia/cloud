package com.minlia.cloud.framework.client;


import com.minlia.cloud.framework.common.interfaces.IDto;

public interface IDtoOperations<T extends IDto> {

    T createNewResource();

    void invalidate(final T entity);

    void change(final T resource);

}
