package com.minlia.cloud.framework.client.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.minlia.cloud.framework.common.client", "com.minlia.cloud.framework.client" })
public class CommonClientConfig {

    public CommonClientConfig() {
        super();
    }

}
