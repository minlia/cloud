/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/solution/usercenter/2015
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
package com.minlia.cloud.infrastructure.framework.usercenter.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "User")
public class UserEntity extends AbstractEntity implements UserDetails {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<RoleEntity> roles;


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public UserProfile toUserProfile() {
        return new UserProfile(username, email);
    }

    public AuthorizableUserProfile toAuthorizableUserProfile() {
        List<Role> myRoles = new ArrayList<>();
        for (RoleEntity roleEntity : roles) {
            myRoles.add(new Role(roleEntity.getRoleName()));
        }

        return new AuthorizableUserProfile(username, email, null, myRoles);
    }

    public static UserEntity fromUserProfile(UserProfile userProfile) {
        if (userProfile != null) {
            UserEntity entity = new UserEntity();
            entity.setUsername(userProfile.getUsername());
            entity.setEmail(userProfile.getEmail());
            return entity;

        } else {
            return null;
        }
    }


    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        if (!super.equals(o)) return false;

        UserEntity that = (UserEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return !(roles != null ? !roles.equals(that.roles) : that.roles != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + username + '\'' +
                ", roles=" + roles +
                "} " + super.toString();
    }
}
