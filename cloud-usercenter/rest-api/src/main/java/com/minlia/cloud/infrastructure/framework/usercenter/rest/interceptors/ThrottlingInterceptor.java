package com.minlia.cloud.infrastructure.framework.usercenter.rest.interceptors;

import com.minlia.cloud.infrastructure.framework.usercenter.exception.BootException;
import com.minlia.cloud.infrastructure.framework.usercenter.exception.ThrottlingException;
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
            throw new BootException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown principal instance: " + principal.getClass());
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
