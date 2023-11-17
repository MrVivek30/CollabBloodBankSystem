package com.trackobit.service;

import com.trackobit.dto.BloodGroupReportDTO;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.exception.AdminException;
import com.trackobit.exception.UserException;
import com.trackobit.model.Request;
import com.trackobit.model.User;
import com.trackobit.repository.RequestRepository;
import com.trackobit.repository.UserRepository;
import com.trackobit.utility.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ServiceHelper serviceHelper;


    @Override
    public UserDTO login(String email, String password) throws AdminException {
        User user = userRepository.findByEmail(email).orElseThrow(()->new AdminException("wrong credentials check email "+email));
        if (!(user.getPassword().equals(password))) {
            throw new UserException("wrong password.");
        }
        return serviceHelper.convertUserToUserDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) throws AdminException {
        User user = userRepository.findByEmail(email).orElseThrow(()->new AdminException("Email not found: " + email));

        return serviceHelper.convertUserToUserDTO(user);
    }

    @Override
    public List<RequestDTO> getAllReceiverList() throws AdminException {
        List<Request> requests = requestRepository.findByType("Receive");
        return serviceHelper.convertRequestToRequestDTOList(requests);
    }

    @Override
    public List<RequestDTO> getAllDonationList() throws AdminException {
        List<Request> requests = requestRepository.findByType("Donate");
        return serviceHelper.convertRequestToRequestDTOList(requests);
    }

    @Override
    public Map<String, Integer> getBloodCountByGroup() throws AdminException {
        // Implement logic using BloodBankRepository methods or custom queries
        return null;
    }

    @Override
    public List<UserDTO> getAllUserList() throws AdminException {
        List<User> users = userRepository.findAll();
        return serviceHelper.convertUserToUserDTOList(users);
    }

    @Override
    public void accepted(int id) throws AdminException {
        Request request = requestRepository.getById(id);

        Map<String, Integer> bloodBank = serviceHelper.getBloodUnitsByGroup();
        int value = bloodBank.get(request.getBloodGroup());
        if (request.getType() == "Receive") {
            if (value >= request.getUnit()) {
                request.setStatus("Accepted");
                RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(request);
                requestRepository.save(request);

            } else {
                throw new AdminException("Insufficient blood of this group " + request.getBloodGroup());
            }
        }

        RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(request);

    }

    @Override
    public void rejected(int id) throws AdminException {
        Request request = requestRepository.getById(id);
        request.setStatus("Rejected");
        requestRepository.save(request);
        RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(request);

    }
//================================================

    @Override
    public List<RequestDTO> getBloodRequestHistory(String action) {

        List<RequestDTO> requestDTOList = new ArrayList<>();

        if ( action.isEmpty()) {

            return serviceHelper.convertRequestToRequestDTOList(requestRepository.findAll());
        }
        //filtering-------
        // status k hisab se
        for (Request request : requestRepository.findAll()) {
            if (request.getStatus().equals(action)) {
                requestDTOList.add(serviceHelper.convertRequestToRequestDTO(request));
            }
            // type k hisba se
            if (request.getType().equals(action)) {
                requestDTOList.add(serviceHelper.convertRequestToRequestDTO(request));
            }
        }

        return requestDTOList;
    }

    @Override
    public List<RequestDTO> getAllBloodHistory() throws AdminException {
        if (requestRepository.findAll().isEmpty()) throw new AdminException("List is Empty");
        return serviceHelper.convertRequestToRequestDTOList(requestRepository.findAll());
    }

    @Override
    public void giveRemark(String remark, int uniqueId) throws AdminException {
        Request request = requestRepository.findByUniqueId(uniqueId).orElseThrow(()->new AdminException("not found with this unique id "+uniqueId));
        request.setRemark(remark);
        requestRepository.save(request);

        RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(request);

    }

    @Override
    public List<BloodGroupReportDTO> bloodUnitsAndDonorsCountReport() throws AdminException {
        if (serviceHelper.getBloodUnitsByGroupAndUserCount() == null) throw new AdminException("Blood Report no found");
        return serviceHelper.getBloodUnitsByGroupAndUserCount();
    }

    @Override
    public List<RequestDTO> getAllDonorsByBloodGroup(String bloodgrp) throws AdminException {
        System.out.println(bloodgrp.length());
        //special chartaeer issue
        if (bloodgrp.trim().equals("A")) bloodgrp = "A+";
        if (bloodgrp.trim().equals("AB")) bloodgrp = "AB+";
        if (bloodgrp.trim().equals("B")) bloodgrp = "B+";
        if (bloodgrp.trim().equals("O")) bloodgrp = "O+";

        List<RequestDTO> requestDTOList = new ArrayList<>();
        for (Request request : requestRepository.findAll()) {
            System.out.println(request.getBloodGroup().equals(bloodgrp) && request.getStatus().equals("Accepted") && request.getType().equals("Donate"));
            System.out.println(request.getBloodGroup() + "  " + (bloodgrp) + request.getStatus() + " " + ("Accepted") + request.getType() + " " + ("Donate"));
            if (request.getBloodGroup().equals(bloodgrp) && request.getStatus().equals("Accepted") && request.getType().equals("Donate")) {
                requestDTOList.add(serviceHelper.convertRequestToRequestDTO(request));
            }
        }
        return requestDTOList;
    }


    public List<BloodGroupReportDTO> getBloodBankReportCard() throws AdminException {
        if (serviceHelper.getBloodBankReportCard() == null) throw new AdminException("Blood Report not found");
        System.out.println(serviceHelper.getBloodBankReportCard()+"-==-=-=--------------------");
        return serviceHelper.getBloodBankReportCard();
    }

    public void unBlock(int id){
        for (User user : userRepository.findAll()) {
            if(user.getId()==id){
                user.setStatus("unblock");
                user.setLoginCount(3);
            }
        }
    }
}
