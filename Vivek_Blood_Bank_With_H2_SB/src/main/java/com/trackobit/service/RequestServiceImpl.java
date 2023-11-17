package com.trackobit.service;

import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.exception.RequestException;
import com.trackobit.model.Request;

import com.trackobit.repository.RequestRepository;
import com.trackobit.utility.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class RequestServiceImpl implements RequestService {

   @Autowired
    RequestRepository requestRepository;
    @Autowired
    ServiceHelper serviceHelper;

    @Override
    public RequestDTO donateBloodToBloodBank(RequestDTO requestDTO, UserDTO currUser) throws RequestException {
        int age = LocalDate.now().getYear() - LocalDate.parse(currUser.getDob()).getYear();

        int count = requestDTO.getIterationCount() + 1;

        if (!(currUser == null)) {
            requestDTO.setRole(currUser.getRole());
            requestDTO.setId(currUser.getId());
            requestDTO.setAge(age);
            requestDTO.setName(currUser.getFirstName());
            requestDTO.setEmail(currUser.getEmail());
            requestDTO.setType("Donate");
            requestDTO.setBloodGroup(currUser.getBloodGroup());
            requestDTO.setAddress(currUser.getAddress());
            requestDTO.setLocalDateTime(LocalDateTime.now());
            requestDTO.setIterationCount(count);
        } else throw new RequestException("error in donating the blood,server down try again later");


        if (serviceHelper.checkUserDuplicateDonate(requestDTO)) {
            System.out.println("inside user donate method-------no duplicate");
           requestRepository.save(serviceHelper.convertRequestDTOToRequest(requestDTO));
            return requestDTO;
        }
        return requestDTO;
    }

    @Override
    public RequestDTO receiveBloodFromBloodBank(RequestDTO requestDTO, UserDTO currUser) throws RequestException {
        int count = requestDTO.getIterationCount() + 1;
        int age = LocalDate.now().getYear() - LocalDate.parse(currUser.getDob()).getYear();
        if (!(currUser == null)) {
            requestDTO.setRole(currUser.getRole());
            requestDTO.setId(currUser.getId());
            requestDTO.setAge(age);
            requestDTO.setName(currUser.getFirstName());
            requestDTO.setEmail(currUser.getEmail());
            requestDTO.setType("Receive");
            requestDTO.setBloodGroup(currUser.getBloodGroup());
            requestDTO.setAddress(currUser.getAddress());
            requestDTO.setLocalDateTime(LocalDateTime.now());
            requestDTO.setIterationCount(count);
        } else throw new RequestException("error in receiving  the blood,server down try again later");

        if (serviceHelper.checkUserDuplicateReceive(requestDTO)) {
            System.out.println("inside user reciver method-------no duplicate");
            requestRepository.save(serviceHelper.convertRequestDTOToRequest(requestDTO));
            return requestDTO;
        }
        return requestDTO;
    }

    @Override
    public RequestDTO getRequestById(int uniqueId) throws RequestException {
        RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(requestRepository.findByUniqueId(uniqueId).orElseThrow(()-> new RequestException("Request not found with unique id " + uniqueId)));
        return requestDTO;
    }

    @Override
    public RequestDTO editRequest(int uniqueId, RequestDTO requestDTO) throws RequestException {
        Request request = requestRepository.findByUniqueId(uniqueId).orElseThrow(()-> new RequestException("Request not found with unique id " + uniqueId));
        //if without edit user already register one more request then it will check dupilcate accordingly
        if (request.getType().equals("Receive")) {
            serviceHelper.checkUserDuplicateReceive(serviceHelper.convertRequestToRequestDTO(request));
            request.setAddress(requestDTO.getAddress());
            request.setStatus("Pending");
            int count = request.getIterationCount() + 1;
            request.setIterationCount(count);
            request.setUnit(requestDTO.getUnit());
            requestRepository.save(request);
        }
        if (request.getType().equals("Donate")) {
            serviceHelper.checkUserDuplicateDonate(serviceHelper.convertRequestToRequestDTO(request));
            request.setAddress(requestDTO.getAddress());
            request.setStatus("Pending");
            int count = request.getIterationCount() + 1;
            request.setIterationCount(count);
            request.setUnit(requestDTO.getUnit());
            requestRepository.save(request);
        }

        System.out.println(request + "=-=-=-=-=--from edit RequestService ******************************");
        return requestDTO;
    }
}
