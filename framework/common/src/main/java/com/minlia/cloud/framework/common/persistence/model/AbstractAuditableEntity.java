package com.minlia.cloud.framework.common.persistence.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.minlia.cloud.framework.common.persistence.listener.AuditableEntityListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
@EntityListeners(AuditableEntityListener.class)
@MappedSuperclass
public abstract class AbstractAuditableEntity extends AbstractIdentifiableEntity {

    private Date dateCreated;
    private Date dateModified;

    @CreatedDate
    @Column(updatable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    @LastModifiedDate
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
