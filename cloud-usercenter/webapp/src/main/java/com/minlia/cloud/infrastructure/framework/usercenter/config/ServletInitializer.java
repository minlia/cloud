package com.minlia.cloud.infrastructure.framework.usercenter.config;

import com.minlia.cloud.infrastructure.framework.usercenter.UserCenterApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


/**
 * Used for deploying tha app war into an existing container.
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UserCenterApplication.class);
    }

}
