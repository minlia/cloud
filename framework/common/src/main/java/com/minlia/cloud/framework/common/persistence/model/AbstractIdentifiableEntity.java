package com.minlia.cloud.framework.common.persistence.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.minlia.cloud.framework.common.interfaces.IDto;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
@MappedSuperclass
public abstract class AbstractIdentifiableEntity implements IEntity, IDto {
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Transient
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdentifiableEntity that = (AbstractIdentifiableEntity) o;
        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    @Transient
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    @Transient
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Transient
    public boolean isEmpty() {
        return getId() == null;
    }
}
