package com.minlia.cloud.framework.web.config;

import com.minlia.cloud.framework.web.logging.LoggingFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/23/15.
 */
@Configuration
public class MvcLoggerConfig {


    @Bean
    public FilterRegistrationBean contextFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        LoggingFilter loggingFilter = new LoggingFilter();
        registrationBean.setFilter(loggingFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/api/*");
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(222);
        return registrationBean;
    }
}
