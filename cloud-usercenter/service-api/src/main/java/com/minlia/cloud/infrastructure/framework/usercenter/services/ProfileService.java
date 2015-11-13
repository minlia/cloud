package com.minlia.cloud.infrastructure.framework.usercenter.services;

import com.minlia.cloud.infrastructure.framework.usercenter.exception.BootException;
import com.minlia.cloud.infrastructure.framework.usercenter.domain.CredentialsUserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.domain.UserProfile;

import javax.servlet.http.HttpServletRequest;


/**
 * Service for interacting with a user's own profile.
 */
public interface ProfileService {

    UserProfile getOwnProfile() throws BootException;

    void register(CredentialsUserProfile profile, HttpServletRequest request) throws BootException;

    void changePassword(char[] newPassword) throws BootException;

    void changePassword(char[] oldPassword, char[] newPassword) throws BootException;

}
