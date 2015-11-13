package com.minlia.cloud.infrastructure.framework.usercenter.rest.endpoints;

import com.minlia.cloud.infrastructure.framework.usercenter.constants.ApiConstants;
import com.minlia.cloud.infrastructure.framework.usercenter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = ApiConstants.API_PREFIX + "/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

}
