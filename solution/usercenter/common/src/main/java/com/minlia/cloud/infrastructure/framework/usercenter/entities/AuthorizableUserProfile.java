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


import java.util.List;


public class AuthorizableUserProfile extends CredentialsUserProfile {

    private List<Role> roles;

    public AuthorizableUserProfile() {
    }

    public AuthorizableUserProfile(UserProfile profile) {
        super(profile.getUsername(), profile.getEmail());
    }

    public AuthorizableUserProfile(String username, String email, char[] password, List<Role> roles) {
        super(username, email);
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorizableUserProfile)) return false;
        if (!super.equals(o)) return false;

        AuthorizableUserProfile that = (AuthorizableUserProfile) o;

        return !(roles != null ? !roles.equals(that.roles) : that.roles != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuthorizableUserProfile{" +
                "roles=" + roles +
                "} " + super.toString();
    }
}
