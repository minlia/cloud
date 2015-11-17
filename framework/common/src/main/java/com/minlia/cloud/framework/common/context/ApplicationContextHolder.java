package com.minlia.cloud.framework.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by user on 11/15/15.
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicatioContext;

    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        ApplicationContextHolder.applicatioContext = arg0;
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextHolder.applicatioContext;
    }
}
