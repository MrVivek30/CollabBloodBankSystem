package com.trackobit.service;

import com.trackobit.exception.RequestException;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;

public interface RequestService {

    public RequestDTO donateBloodToBloodBank(RequestDTO request, UserDTO currUser) throws RequestException;

    public RequestDTO receiveBloodFromBloodBank(RequestDTO request, UserDTO currUser) throws RequestException;
    public RequestDTO getRequestById(int uniqueId)throws RequestException;

    public RequestDTO editRequest(int uniqueId,RequestDTO requestDTO) throws RequestException;

}
