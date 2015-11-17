package com.minlia.cloud.framework.web.config;

import com.minlia.cloud.framework.common.constants.Constants.Profiles;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by user on 11/14/15.
 */
@Configuration
@Profile(value = Profiles.DEVELOPMENT)
public class ValidationConfig {
}
