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
package com.minlia.cloud.infrastructure.framework.usercenter.config;

import com.minlia.cloud.infrastructure.framework.usercenter.rest.interceptors.ThrottlingInterceptor;
import com.minlia.cloud.infrastructure.framework.usercenter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
public class WebConfiguration {

    @Configuration
    public static class WebMvcConfig extends WebMvcConfigurerAdapter {

        @Autowired
        private ThrottlingInterceptor throttlingInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(throttlingInterceptor);
        }
    }


    /**
     * Configures security settings for web container.
     */
    @Configuration
    @EnableWebSecurity
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private DataSource dataSource;

        @Autowired
        @Qualifier("userService")
        private UserService userService;


        /**
         * Setup a UserDetailsService that reads users and roles from predefined database tables.
         * Table schemas can be seen in {@link org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl}
         */
        @Bean
        public UserDetailsService userDetailsService() {
            return userService;
        }

        /**
         * Use the stronger known password encoder, do not store plain text passwords!!.
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        /**
         * Configure the JDBC UserDetailsService to be used by Spring AuthenticationManager.
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(userDetailsService())
                    .passwordEncoder(passwordEncoder());
        }

        /**
         * Expose the configured AuthenticationManager that reads users from database.
         */
        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

}
