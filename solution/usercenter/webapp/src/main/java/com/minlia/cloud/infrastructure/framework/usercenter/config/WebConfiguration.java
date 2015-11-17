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
