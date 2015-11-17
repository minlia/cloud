package com.minlia.cloud.framework;

import com.minlia.cloud.framework.config.MinliaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by user on 11/15/15.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties({MinliaProperties.class})
@Import({MinliaCloudConfiguration.class})
public @interface EnableMinliaCloud {
}
