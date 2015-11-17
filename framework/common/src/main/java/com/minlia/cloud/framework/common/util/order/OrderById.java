package com.minlia.cloud.framework.common.util.order;

import com.google.common.collect.Ordering;
import com.minlia.cloud.framework.common.interfaces.IWithId;

public final class OrderById<T extends IWithId> extends Ordering<T> {

    public OrderById() {
        super();
    }

    // API

    @Override
    public final int compare(final T left, final T right) {
        return new Long(left.getId().toString()).compareTo((Long)right.getId());
    }

}
