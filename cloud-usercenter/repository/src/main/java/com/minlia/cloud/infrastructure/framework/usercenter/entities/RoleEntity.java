package com.minlia.cloud.infrastructure.framework.usercenter.entities;

import com.minlia.cloud.infrastructure.framework.usercenter.domain.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Role")
public class RoleEntity extends AbstractEntity implements GrantedAuthority {

    private static final String AUTHORITY_PREFIX = "ROLE_";


    @Column(name = "role_name", unique = true)
    private String roleName;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return AUTHORITY_PREFIX + roleName;
    }

    public Role toRole() {
        return new Role(roleName);
    }

    public static RoleEntity fromAuthority(GrantedAuthority authority) {
        if (authority != null) {
            RoleEntity entity = new RoleEntity();
            entity.setRoleName(authority.getAuthority());
            return entity;

        } else {
            return null;
        }
    }

    public static RoleEntity fromRole(Role role) {
        if (role != null) {
            RoleEntity entity = new RoleEntity();
            entity.setRoleName(role.getName());
            return entity;

        } else {
            return null;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity)) return false;
        if (!super.equals(o)) return false;

        RoleEntity that = (RoleEntity) o;

        return !(roleName != null ? !roleName.equals(that.roleName) : that.roleName != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleName='" + roleName + '\'' +
                "} " + super.toString();
    }

}
