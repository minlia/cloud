/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/framework/2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minlia.cloud.framework.common.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * <p>Makes the web application name and context available as MDC (log context) information to improve logging interpretation.</p>
 * <p>This is set in the parent thread and seems to be inherited by all children threads -i.e. the threads that handle the user requests.</p>
 * <p>It also logs web application startup and shutdown events.</p>
 *
 */
public class LogContextInitializer implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // API

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        // Logging context information
        MDC.put("appName", sce.getServletContext().getServletContextName());
        MDC.put("contextPath", sce.getServletContext().getContextPath());

        if (logger.isInfoEnabled()) {
            logger.info("INITIALIZING APPLICATION \"{}\" ON WEB CONTEXT \"{}\".", sce.getServletContext().getServletContextName(), sce.getServletContext().getContextPath());
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        if (logger.isInfoEnabled()) {
            logger.info("DESTROYING APPLICATION \"{}\" ON WEB CONTEXT \"{}\".", sce.getServletContext().getServletContextName(), sce.getServletContext().getContextPath());
        }
    }

}
