package com.trackobit.interceptor;

import com.trackobit.dto.UserActivityDTO;
import com.trackobit.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class UserActivityLogger implements HandlerInterceptor {

    @Autowired
    private UserActivityService userActivityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserActivityDTO userActivityDTO = extractUserActivityFromRequest(request);
        userActivityDTO.setLoginTime(LocalDateTime.now()); // Set login time
        userActivityService.logUserActivity(userActivityDTO);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserActivityDTO userActivityDTO = extractUserActivityFromRequest(request);
        userActivityDTO.setLogoutTime(LocalDateTime.now()); // Set logout time
        userActivityService.logUserActivity(userActivityDTO);
    }

    private UserActivityDTO extractUserActivityFromRequest(HttpServletRequest request) {
        UserActivityDTO userActivityDTO = new UserActivityDTO();
        // Extract necessary details from the request to populate userActivityDTO
//        userActivityDTO.setUserEmail(); // Set user email
        userActivityDTO.setIpAddress(getClientIp(request)); // Set user IP address
        // Add other necessary details
        return userActivityDTO;
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
