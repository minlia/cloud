package com.minlia.cloud.solution.mobile.frontend.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Configuration
public class CorsConfiguration {

    @Bean
    public SimpleCorsFilter simpleCorsFilter() {
        return new SimpleCorsFilter();
    }

    public class SimpleCorsFilter implements Filter {

        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            Optional<String> originHeaderValue = Optional.ofNullable(request.getHeader("Origin"));

            final String allowOrigin = originHeaderValue.orElse("*");
            response.setHeader("Access-Control-Allow-Origin", allowOrigin);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

            originHeaderValue.ifPresent(foo -> response.setHeader("Access-Control-Allow-Credentials", "true"));

            chain.doFilter(req, res);
        }

        public void init(FilterConfig filterConfig) {
        }

        public void destroy() {
        }

    }
}
