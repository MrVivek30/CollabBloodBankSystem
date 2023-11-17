package com.trackobit.service;

import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.exception.AgentUserException;
import com.trackobit.model.Request;

import com.trackobit.repository.RequestRepository;
import com.trackobit.utility.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentUserServiceImpl implements AgentUserService {

    @Autowired
    ServiceHelper serviceHelper;
@Autowired
    RequestRepository requestRepoList;
    @Override
    public RequestDTO authenticateAgentUser(UserDTO userDTO) {
       serviceHelper.validateAgentUser(userDTO);
       Request request =  requestRepoList.findByEmail(userDTO.getEmail()).orElseThrow(()->new AgentUserException("Agent user not found with this email "+userDTO.getEmail()));
if (request.getRole().equals("agent"))
    return serviceHelper.convertRequestToRequestDTO(request);
else throw new AgentUserException("invalid details ");

    }

    @Override
    public void changePassword(String email,String password) {
      Request request=  requestRepoList.findByEmail(email).orElseThrow(()->new AgentUserException("Agent user not found with this email "+email));
        request.setPassword(password);
        requestRepoList.save(request);

    }

    @Override
    public  List<RequestDTO> getHistory(RequestDTO request) {
        List<RequestDTO> requestDTOS = serviceHelper.convertRequestToRequestDTOList(requestRepoList.findAll());
        return requestDTOS.stream().filter(e->e.getEmail().equals(request.getEmail())).collect(Collectors.toList());


    }
}
