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
package com.minlia.cloud.framework.common.persistence.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.cloud.framework.common.interfaces.IDto;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
@MappedSuperclass
public abstract class AbstractIdentifiableEntity implements IEntity, IDto {

    @JsonProperty
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
