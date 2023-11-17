package com.trackobit.service;

import com.trackobit.dto.UserActivityDTO;
import com.trackobit.model.UserActivity;

public interface UserActivityService {


    public void logUserActivity(UserActivityDTO userActivityDTO);
}
