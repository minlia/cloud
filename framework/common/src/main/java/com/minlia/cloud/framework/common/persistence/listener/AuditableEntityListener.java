package com.minlia.cloud.framework.common.persistence.listener;


import com.minlia.cloud.framework.common.persistence.model.AbstractAuditableEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;


public class AuditableEntityListener {

    @PrePersist
    public void prePersist(AbstractAuditableEntity entity) {
        entity.setDateCreated(new Date());
        entity.setDateModified(new Date());
    }

    @PreUpdate
    public void preUpdate(AbstractAuditableEntity entity) {
        entity.setDateModified(new Date());
    }

}
