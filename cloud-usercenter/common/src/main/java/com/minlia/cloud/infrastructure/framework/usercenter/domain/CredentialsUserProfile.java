package com.minlia.cloud.infrastructure.framework.usercenter.domain;


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
