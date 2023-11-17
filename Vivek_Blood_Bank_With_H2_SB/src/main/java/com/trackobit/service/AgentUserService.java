package com.trackobit.service;

import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;

import java.util.List;

public interface AgentUserService {
    RequestDTO authenticateAgentUser(UserDTO userDTO);

    void changePassword(String email,String password);

    List<RequestDTO> getHistory(RequestDTO request);
}
