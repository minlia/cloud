package com.minlia.cloud.infrastructure.framework.usercenter.rest.controllers;

import com.minlia.cloud.infrastructure.framework.usercenter.exception.BootException;
import com.minlia.cloud.infrastructure.framework.usercenter.domain.CredentialsUserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.domain.UserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.rest.RestConstants;
import com.minlia.cloud.infrastructure.framework.usercenter.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Operations for a user's own profile.
 */
@RestController
@RequestMapping(value = RestConstants.API_PREFIX + "/profile")
public class ProfileController {

    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody boolean register(@RequestBody CredentialsUserProfile user, HttpServletRequest request) throws BootException {
        profileService.register(user, request);
        return true;
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody UserProfile getCurrentUser() throws BootException {
        return profileService.getOwnProfile();
    }


    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public @ResponseBody boolean changePassword(
            @RequestParam("oldPassword") char[] oldPassword,
            @RequestParam("newPassword") char[] newPassword) throws BootException {

        profileService.changePassword(oldPassword, newPassword);
        return true;
    }

}
