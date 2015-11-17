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
package com.minlia.cloud.framework.common.spring.util;

public final class Profiles {

    // these 3 profiles are modeling the environments - deployed is active for any deployment, dev and production for these specific environments
    public static final String DEPLOYED = "deployed";
    public static final String DEV = "dev";

    // common
    public static final String TEST = "test";
    public static final String CLIENT = "client";

    private Profiles() {
        throw new AssertionError();
    }

}
