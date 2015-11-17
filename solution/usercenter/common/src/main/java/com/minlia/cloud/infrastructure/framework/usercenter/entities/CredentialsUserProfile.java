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


import java.util.Arrays;

/**
 * CredentialsUserProfile Entity
 */
public class CredentialsUserProfile extends UserProfile {

    private char[] password;

    public CredentialsUserProfile() {

    }

    public CredentialsUserProfile(String username, String email) {
        super(username, email);
    }

    public CredentialsUserProfile(String username, String email, char[] password) {
        super(username, email);
        this.password = password;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsUserProfile)) return false;
        if (!super.equals(o)) return false;

        CredentialsUserProfile that = (CredentialsUserProfile) o;

        return Arrays.equals(password, that.password);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "CredentialsUserProfile{" +
                "password=" + Arrays.toString(password) +
                "} " + super.toString();
    }
}
