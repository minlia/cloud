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
