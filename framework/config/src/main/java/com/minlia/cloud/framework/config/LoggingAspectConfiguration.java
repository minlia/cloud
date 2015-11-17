package com.minlia.cloud.framework.config;

import com.minlia.cloud.framework.common.constants.Constants;
import com.minlia.cloud.framework.config.logging.LoggingAspect;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(Constants.Profiles.DEVELOPMENT)
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
