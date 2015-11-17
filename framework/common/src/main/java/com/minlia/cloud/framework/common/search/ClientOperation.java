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
package com.minlia.cloud.framework.common.search;

public enum ClientOperation {
    EQ, NEG_EQ, CONTAINS, NEG_CONTAINS, STARTS_WITH, NEG_STARTS_WITH, ENDS_WITH, NEG_ENDS_WITH;

    public boolean isNegated() {
        switch (this) {
        case EQ:
            return false;
        case NEG_EQ:
            return true;

        case CONTAINS:
            return false;
        case STARTS_WITH:
            return false;
        case ENDS_WITH:
            return false;

        case NEG_CONTAINS:
            return true;
        case NEG_STARTS_WITH:
            return true;
        case NEG_ENDS_WITH:
            return true;

        default:
            return false;
        }
    }

}
