package com.trackobit.service;

import com.trackobit.dto.BloodGroupReportDTO;
import com.trackobit.exception.AdminException;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface AdminService {
    public UserDTO login(String email, String password) throws AdminException;

    public UserDTO getUserByEmail(String email) throws AdminException;

    public List<RequestDTO> getAllReceiverList() throws AdminException;

    public List<RequestDTO> getAllDonationList() throws AdminException;

    public Map<String, Integer> getBloodCountByGroup() throws AdminException;

    public List<UserDTO> getAllUserList() throws AdminException;

    public void accepted(int id) throws AdminException;

    public void rejected(int id) throws AdminException;

    public List<RequestDTO> getBloodRequestHistory(String action);

    public List<RequestDTO> getAllBloodHistory() throws AdminException;

    public void giveRemark(String remark, int uniqueId) throws AdminException;

    public List<BloodGroupReportDTO> bloodUnitsAndDonorsCountReport() throws AdminException;

    public List<RequestDTO> getAllDonorsByBloodGroup(String bloodgrp) throws AdminException;

    public List<BloodGroupReportDTO> getBloodBankReportCard() throws AdminException;
}
