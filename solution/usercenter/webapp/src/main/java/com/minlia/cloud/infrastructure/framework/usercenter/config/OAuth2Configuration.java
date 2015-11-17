package com.minlia.cloud.infrastructure.framework.usercenter.config;

import com.minlia.cloud.infrastructure.framework.usercenter.constants.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;


/**
 * Configuration for OAuth2 Authorization and Resource servers.
 *
 * Authorization server is the server that listens for token requests in /oauth/token,
 * Resource server is our REST api (/api).
 */
@Configuration
public class OAuth2Configuration {

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        /**
         * Use the AuthenticationManager that has our configured UserDetailsService reading users from database.
         */
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private DataSource dataSource;

        @Autowired
        private PasswordEncoder passwordEncoder;


        /**
         * Store access tokens in a database. Useful for clustered servers that need to share login state between
         * nodes.
         */
        @Bean
        @Primary
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }


        /**
         * Configure controllers to enable OAuth2 password grants, by using our configured AuthenticationManager and
         * JdbcUserDetailsService.
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(this.authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        /**
         * Set the BCrypt password encoder built earlier.
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.passwordEncoder(passwordEncoder);
        }

        /**
         * Supply client details (a client is an application like web, mobile, etc.) from the database.
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .jdbc(dataSource)
                    .passwordEncoder(passwordEncoder);
        }

    }


    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources
                    .resourceId(ApiConstants.API_RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers(ApiConstants.API_PREFIX + "/user/**").hasRole("ADMIN");

            // No user authentication needed for new users registering themselves, but manual client
            // validation is needed (see ProfileService.register)
            http
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, ApiConstants.API_PREFIX + "/profile").anonymous();

            // Any other usage of /profile endpoint needs the user to be authenticated
            http
                    .authorizeRequests()
                    .antMatchers(ApiConstants.API_PREFIX + "/profile/**").authenticated();

        }

    }

}
