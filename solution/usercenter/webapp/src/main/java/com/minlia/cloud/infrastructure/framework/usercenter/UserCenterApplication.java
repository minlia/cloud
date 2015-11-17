package com.minlia.cloud.infrastructure.framework.usercenter;

import com.minlia.cloud.infrastructure.framework.usercenter.config.OAuth2Configuration;
import com.minlia.cloud.infrastructure.framework.usercenter.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({OAuth2Configuration.class, WebConfiguration.class})
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}
