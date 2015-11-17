package com.minlia.cloud.infrastructure.framework.usercenter.services;

import com.minlia.cloud.infrastructure.framework.usercenter.exceptions.UserCenterException;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.CredentialsUserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.UserProfile;

import javax.servlet.http.HttpServletRequest;


/**
 * Service for interacting with a user's own profile.
 */
public interface ProfileService {

    UserProfile getOwnProfile() throws UserCenterException;

    void register(CredentialsUserProfile profile, HttpServletRequest request) throws UserCenterException;

    void changePassword(char[] newPassword) throws UserCenterException;

    void changePassword(char[] oldPassword, char[] newPassword) throws UserCenterException;

}
