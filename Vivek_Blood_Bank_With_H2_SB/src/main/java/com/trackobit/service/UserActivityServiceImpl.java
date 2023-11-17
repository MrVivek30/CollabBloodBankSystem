package com.trackobit.service;

import com.trackobit.dto.UserActivityDTO;
import com.trackobit.model.UserActivity;
import com.trackobit.repository.UserActivityRepository;
import org.h2.store.FileLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActivityServiceImpl implements UserActivityService{

    @Autowired
    UserActivityRepository userActivityRepository;


    public void logUserActivity(UserActivityDTO userActivityDTO) {
        UserActivity userActivity = convertDTOToEntity(userActivityDTO);
        userActivityRepository.save(userActivity);
    }

    private UserActivity convertDTOToEntity(UserActivityDTO userActivityDTO) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUserEmail(userActivityDTO.getUserEmail());
        userActivity.setActivity(userActivityDTO.getActivity());
        userActivity.setIpAddress(userActivityDTO.getIpAddress());
        userActivity.setLoginTime(userActivityDTO.getLoginTime());
        userActivity.setLogoutTime(userActivityDTO.getLogoutTime());
        userActivity.setLastLoginTime(userActivityDTO.getLastLoginTime());
        return userActivity;
    }


}
