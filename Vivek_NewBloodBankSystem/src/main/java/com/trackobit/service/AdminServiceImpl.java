package com.trackobit.service;

import com.trackobit.dto.BloodGroupReportDTO;
import com.trackobit.exception.AdminException;
import com.trackobit.exception.UserException;
import com.trackobit.utility.ServiceHelper;
import com.trackobit.model.Request;
import com.trackobit.model.User;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.repository.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    DataBase dataBase;

    @Autowired
    ServiceHelper serviceHelper;

    @Override
    public UserDTO login(String email, String password) throws AdminException {

        for (User u : DataBase.userRepoList) {
            if (u.getEmail().equals(email)) {
                if (u.getPassword().equals(password)) {
                    return serviceHelper.convertUserToUserDTO(u);
                } else {
                    throw new UserException("Wrong password. Please try again.");
                }
            }
        }
        throw new UserException("Invalid username. Please try again.");
    }

    @Override
    public UserDTO getUserByEmail(String email) throws AdminException {

        for (User user : DataBase.userRepoList) {
            if (!(user.getEmail().equals(email))) throw new UserException("Email not found " + email);

            else return serviceHelper.convertUserToUserDTO(user);
        }
        return null;
    }

    @Override
    public List<RequestDTO> getAllReceiverList() throws AdminException {
        List<RequestDTO> receiveRequestDTOList = new ArrayList<>();

        if (DataBase.requestRepoList.isEmpty()) throw new AdminException("Receive Requested List is empty");
        for (Request request : DataBase.requestRepoList) {
            if (request.getType().equals("Receive")) {
                receiveRequestDTOList.add(serviceHelper.convertRequestToRequestDTO(request));
            }
        }


        return receiveRequestDTOList;
    }

    @Override
    public List<RequestDTO> getAllDonationList() throws AdminException {
        List<RequestDTO> receiveRequestDTOList = new ArrayList<>();

        if (DataBase.requestRepoList.isEmpty()) throw new AdminException("Donate Requested List is empty");
        for (Request request : DataBase.requestRepoList) {
            if (request.getType().equals("Donate")) {
                receiveRequestDTOList.add(serviceHelper.convertRequestToRequestDTO(request));
            }
        }


        return receiveRequestDTOList;
    }

    @Override
    public Map<String, Integer> getBloodCountByGroup() throws AdminException {

        Map<String, Integer> bloodUnitsByGroup = serviceHelper.getBloodUnitsByGroup();

        return bloodUnitsByGroup;
    }

    @Override
    public List<UserDTO> getAllUserList() throws AdminException {
        if (DataBase.userRepoList.isEmpty()) throw new AdminException("User  List is empty");
        return serviceHelper.convertUserToUserDTOList(DataBase.userRepoList);
    }

    @Override
    public void accepted(int id) throws AdminException {
        Request request = DataBase.getRequestById(id);
        Map<String, Integer> bloodBank = serviceHelper.getBloodUnitsByGroup();
        int value = bloodBank.get(request.getBloodGroup());
        if (request.getType() == "Receive") {
            if (value >= request.getUnit()) {
                request.setStatus("Accepted");
                RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(request);
                System.out.println(requestDTO + " from service admin receiving blood");
            } else {
                throw new AdminException("Insufficient blood of this group " + request.getBloodGroup());
            }
        }
        request.setStatus("Accepted");
        RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(request);
        System.out.println(requestDTO + " from service admin");

    }

    @Override
    public void rejected(int id) throws AdminException {
        Request request = DataBase.getRequestById(id);
        request.setStatus("Rejected");
        RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(request);
        System.out.println(requestDTO + " from service adimn");

    }

    @Override
    public List<RequestDTO> getBloodRequestHistory(String action) {

        List<RequestDTO> requestDTOList = new ArrayList<>();

        if (action == null || action.isEmpty()) {

            return serviceHelper.convertRequestToRequestDTOList(DataBase.requestRepoList);
        }
        //filtering-------
        // status k hisab se
        for (Request request : DataBase.requestRepoList) {
            if (request.getStatus().equals(action)) {
                requestDTOList.add(serviceHelper.convertRequestToRequestDTO(request));
            }
            // type k hisba se
            if (request.getType().equals(action)) {
                requestDTOList.add(serviceHelper.convertRequestToRequestDTO(request));
            }
        }
        System.out.println(requestDTOList + " from adminservice----------->");
        return requestDTOList;
    }

    @Override
    public List<RequestDTO> getAllBloodHistory() throws AdminException {
        if (DataBase.requestRepoList.isEmpty()) throw new AdminException("List is Empty");
        return serviceHelper.convertRequestToRequestDTOList(DataBase.requestRepoList);
    }

    @Override
    public void giveRemark(String remark, int uniqueId) throws AdminException {
        Request request = DataBase.getRequestById(uniqueId);
        request.setRemark(remark);
        System.out.println(request + "form admin servivce=================");
        RequestDTO requestDTO = serviceHelper.convertRequestToRequestDTO(request);
        System.out.println(requestDTO + "================");
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
        for (Request request : DataBase.requestRepoList) {
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

}
