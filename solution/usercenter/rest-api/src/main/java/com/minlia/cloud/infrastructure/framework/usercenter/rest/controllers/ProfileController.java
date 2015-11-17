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
package com.minlia.cloud.infrastructure.framework.usercenter.rest.controllers;

import com.minlia.cloud.infrastructure.framework.usercenter.exceptions.UserCenterException;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.CredentialsUserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.UserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.constants.ApiConstants;
import com.minlia.cloud.infrastructure.framework.usercenter.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Operations for a user's own profile.
 */
@RestController
@RequestMapping(value = ApiConstants.API_PREFIX + "/profile")
public class ProfileController {

    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody boolean register(@RequestBody CredentialsUserProfile user, HttpServletRequest request) throws UserCenterException {
        profileService.register(user, request);
        return true;
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody UserProfile getCurrentUser() throws UserCenterException {
        return profileService.getOwnProfile();
    }


    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public @ResponseBody boolean changePassword(
            @RequestParam("oldPassword") char[] oldPassword,
            @RequestParam("newPassword") char[] newPassword) throws UserCenterException {

        profileService.changePassword(oldPassword, newPassword);
        return true;
    }

}
