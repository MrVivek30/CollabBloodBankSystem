package com.trackobit.service;

import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.exception.RequestException;
import com.trackobit.exception.UserException;
import com.trackobit.model.Request;

import com.trackobit.repository.RequestRepository;
import com.trackobit.utility.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

@Autowired
    RequestRepository requestRepoList;

    @Autowired
    ServiceHelper serviceHelper;

    @Override
    public RequestDTO donateBloodToBloodBank(RequestDTO requestDTO, UserDTO currUser) throws RequestException {
        int count = requestDTO.getIterationCount() + 1;
        if (!(currUser == null)) {
            requestDTO.setRole(currUser.getRole());//agent
            requestDTO.setId(currUser.getId());
            requestDTO.setType("Donate");
            requestDTO.setLocalDateTime(LocalDateTime.now());
            requestDTO.setIterationCount(count);

        } else throw new RequestException("error in donating the blood user does not exist,server down try again later");


        if (serviceHelper.checkUserDuplicateDonate(requestDTO)) {
            System.out.println("inside agent donate method-------no duplicate");
           requestRepoList.save((serviceHelper.convertRequestDTOToRequest(requestDTO)));
            return requestDTO;
        }

        return requestDTO;
    }

    @Override
    public RequestDTO receiveBloodFromBloodBank(RequestDTO requestDTO, UserDTO currUser) throws RequestException {

        int count = requestDTO.getIterationCount() + 1;
        if (!(currUser == null)) {
            requestDTO.setRole(currUser.getRole());//agent
            requestDTO.setId(currUser.getId());
            requestDTO.setType("Receive");
            requestDTO.setLocalDateTime(LocalDateTime.now());
            requestDTO.setIterationCount(count);
        } else throw new RequestException("error in Receiving the blood,server down try again later");

        if (serviceHelper.checkUserDuplicateReceive(requestDTO)) {
            System.out.println("inside agent reciver method-------no duplicate");
            requestRepoList.save((serviceHelper.convertRequestDTOToRequest(requestDTO)));
            return requestDTO;
        }

        return requestDTO;
    }


    public List<RequestDTO> getUserHistory(int id) throws UserException {
        List<Request> userHistory = requestRepoList.findAll();

        List<RequestDTO> history = new ArrayList<>();
        if (userHistory.isEmpty()) {
            throw new UserException("History is empty from the Agent with id: " + id + " donate or receive to see history");
        } else {
            for (Request r : userHistory) {
                if (r.getId() == id) {
                    history.add(serviceHelper.convertRequestToRequestDTO(r));
                }
            }
        }

        return history;
    }

}
