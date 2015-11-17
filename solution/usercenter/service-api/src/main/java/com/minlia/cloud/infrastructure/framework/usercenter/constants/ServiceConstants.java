/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/solution/usercenter/2015
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
package com.minlia.cloud.infrastructure.framework.usercenter.constants;



import com.minlia.cloud.infrastructure.framework.usercenter.entities.Role;

import java.util.Collections;
import java.util.List;


public abstract class ServiceConstants {

    private ServiceConstants() {}

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public static final List<Role> DEFAULT_USER_ROLES = Collections.singletonList(new Role(ROLE_USER));
    public static final List<String> DEFAULT_USER_ROLE_NAMES = Collections.singletonList(ROLE_USER);

}
