package com.minlia.cloud.solution.mobile.frontend.webapp;

import com.minlia.cloud.solution.mobile.frontend.webapp.config.CorsConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(Application.class)
                .sources(CorsConfiguration.class)
                .web(true)
                .showBanner(true)
                .run(args);
    }
}
