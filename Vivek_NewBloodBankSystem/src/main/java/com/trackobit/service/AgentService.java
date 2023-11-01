package com.trackobit.service;

import com.trackobit.exception.RequestException;
import com.trackobit.exception.UserException;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;

import java.util.List;

public interface AgentService {

    public RequestDTO donateBloodToBloodBank(RequestDTO requestDTO, UserDTO currUser) throws RequestException;

    public RequestDTO receiveBloodFromBloodBank(RequestDTO requestDTO, UserDTO currUser) throws RequestException;

    public List<RequestDTO> getUserHistory(int id) throws UserException;
}
