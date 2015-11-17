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
package com.minlia.cloud.infrastructure.framework.usercenter.rest.interceptors;

import com.minlia.cloud.infrastructure.framework.usercenter.exceptions.UserCenterException;
import com.minlia.cloud.infrastructure.framework.usercenter.exceptions.ThrottlingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ThrottlingInterceptor implements HandlerInterceptor {

    @Value("${throttling.maxRequests}")
    private int throttlingMaxRequests;

    @Value("${throttling.timeLimitMillis}")
    private int throttlingTimeLimitMillis;

    private Map<String, Map<String, List<Long>>> requestsByUsernameByUri = new HashMap<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentAuthentication == null) {
            return true;
        }

        String username;
        Object principal = currentAuthentication.getPrincipal();
        if (principal instanceof String) {
            username = (String) principal;
        } else if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new UserCenterException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown principal instance: " + principal.getClass());
        }

        String requestURI = request.getRequestURI();

        if (requestsByUsernameByUri.get(username) == null) {
            requestsByUsernameByUri.put(username, new HashMap<>());
        }

        if (requestsByUsernameByUri.get(username).get(requestURI) == null) {
            requestsByUsernameByUri.get(username).put(requestURI, new ArrayList<>());
        }

        long currentTimeMillis = System.currentTimeMillis();
        List<Long> requestTimestamps = requestsByUsernameByUri.get(username).get(requestURI);
        requestTimestamps.add(currentTimeMillis);

        clearExpiredRequests(requestTimestamps, currentTimeMillis);

        if (requestTimestamps.size() < throttlingMaxRequests) {
            return true;

        } else {
            throw new ThrottlingException();
        }
    }

    private void clearExpiredRequests(List<Long> requestTimestamps, long currentTimeMillis) {
        for (Long requestTimeMillis : new ArrayList<>(requestTimestamps)) {
            if ((currentTimeMillis - requestTimeMillis) > throttlingTimeLimitMillis) {
                requestTimestamps.remove(requestTimeMillis);
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
