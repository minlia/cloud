package com.minlia.cloud.framework.web.config;

import com.minlia.cloud.framework.common.constants.Constants.Profiles;
import com.minlia.cloud.framework.web.logging.LoggingFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.RequestContextFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/14/15.
 */
@Configuration
@Profile(value = Profiles.DEVELOPMENT)
public class WebmvcLoggerConfig {

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
