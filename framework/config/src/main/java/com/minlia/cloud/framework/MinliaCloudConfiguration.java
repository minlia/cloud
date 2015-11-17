package com.minlia.cloud.framework;

import com.minlia.cloud.framework.common.context.ApplicationContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Created by user on 11/15/15.
 */
@Configuration
@ComponentScan
public class MinliaCloudConfiguration {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ApplicationContextHolder applicationContextHolder() {
        ApplicationContextHolder applicationContextHolder = new ApplicationContextHolder();
        return applicationContextHolder;
    }
}
