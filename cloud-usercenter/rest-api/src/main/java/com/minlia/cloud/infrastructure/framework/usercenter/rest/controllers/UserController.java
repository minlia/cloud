package com.minlia.cloud.infrastructure.framework.usercenter.rest.controllers;

import com.minlia.cloud.infrastructure.framework.usercenter.services.UserService;
import com.minlia.cloud.infrastructure.framework.usercenter.rest.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = RestConstants.API_PREFIX + "/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

}
