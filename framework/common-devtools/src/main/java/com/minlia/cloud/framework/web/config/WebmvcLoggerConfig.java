/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/framework/2015
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
