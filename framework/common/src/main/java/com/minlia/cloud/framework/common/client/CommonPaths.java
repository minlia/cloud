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
package com.minlia.cloud.framework.common.client;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Profile("client")
public final class CommonPaths implements InitializingBean {

    @Autowired
    private Environment env;

    @Value("${http.protocol}")
    private String protocol;
    @Value("${http.host}")
    private String host;
    @Value("${http.port}")
    private String port;

    public CommonPaths() {
        super();
    }

    // API

    public final String getServerRoot() {
        if (port.equals("80")) {
            return protocol + "://" + host;
        }
        return protocol + "://" + host + ":" + port;
    }

    //

    @Override
    public void afterPropertiesSet() {
        if (protocol == null || protocol.equals("${http.protocol}")) {
            protocol = Preconditions.checkNotNull(env.getProperty("http.protocol"));
        }
        if (host == null || host.equals("${http.host}")) {
            host = Preconditions.checkNotNull(env.getProperty("http.host"));
        }
        if (port == null || port.equals("${http.port}")) {
            port = Preconditions.checkNotNull(env.getProperty("http.port"));
        }
    }

}
