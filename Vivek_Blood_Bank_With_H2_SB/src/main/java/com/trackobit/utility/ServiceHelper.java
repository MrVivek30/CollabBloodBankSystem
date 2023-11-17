package com.trackobit.utility;

import com.trackobit.dto.BloodGroupReportDTO;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.exception.AgentUserException;
import com.trackobit.exception.RequestException;

import com.trackobit.model.Request;
import com.trackobit.model.User;

import com.trackobit.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServiceHelper {

    @Autowired
    RequestRepository requestRepoList;
    public List<UserDTO> convertUserToUserDTOList(List<User> userRepoList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userRepoList) {
            UserDTO userDTO = new UserDTO();

            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setDob(String.valueOf(LocalDate.parse(user.getDob())));
            userDTO.setPassword(user.getPassword());
            userDTO.setBloodGroup(user.getBloodGroup());
            userDTO.setRole(user.getRole());
            userDTO.setAccountCreationDateTime(user.getAccountCreationDateTime());
            userDTO.setAddress(user.getAddress());
            userDTO.setStatus(user.getStatus());
            userDTO.setLoginCount(user.getLoginCount());
            userDTO.setSecretCode(user.getSecretCode());

            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDob(String.valueOf(LocalDate.parse(user.getDob())));
        userDTO.setPassword(user.getPassword());
        userDTO.setBloodGroup(user.getBloodGroup());
        userDTO.setRole(user.getRole());
        userDTO.setAccountCreationDateTime(user.getAccountCreationDateTime());
        userDTO.setAddress(user.getAddress());
        userDTO.setStatus(user.getStatus());
        userDTO.setLoginCount(user.getLoginCount());
        userDTO.setSecretCode(user.getSecretCode());

        return userDTO;
    }

    public List<RequestDTO> convertRequestToRequestDTOList(List<Request> requestList) {

        List<RequestDTO> receiveDTORequestList = new ArrayList<>();

        for (Request request : requestList) {

            RequestDTO requestDTO = new RequestDTO();

            requestDTO.setId(request.getId());
            requestDTO.setName(request.getName());
            requestDTO.setAge(request.getAge());
            requestDTO.setEmail(request.getEmail());
            requestDTO.setUnit(request.getUnit());
            requestDTO.setBloodGroup(request.getBloodGroup());
            requestDTO.setAddress(request.getAddress());
            requestDTO.setLocalDateTime(request.getLocalDateTime());
            requestDTO.setRole(request.getRole());
            requestDTO.setType(request.getType());
            requestDTO.setStatus(request.getStatus());
            requestDTO.setUniqueId(request.getUniqueId());
            requestDTO.setRemark(request.getRemark());
            requestDTO.setIterationCount(request.getIterationCount());
            if(request.getRole().equals("agent")){
                requestDTO.setPassword(request.getPassword());
            }
            receiveDTORequestList.add(requestDTO);
        }
        return receiveDTORequestList;
    }

    public RequestDTO convertRequestToRequestDTO(Request request) {
        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setId(request.getId());
        requestDTO.setName(request.getName());
        requestDTO.setAge(request.getAge());
        requestDTO.setEmail(request.getEmail());
        requestDTO.setUnit(request.getUnit());
        requestDTO.setBloodGroup(request.getBloodGroup());
        requestDTO.setAddress(request.getAddress());
        requestDTO.setLocalDateTime(request.getLocalDateTime());
        requestDTO.setRole(request.getRole());
        requestDTO.setType(request.getType());
        requestDTO.setStatus(request.getStatus());
        requestDTO.setUniqueId(request.getUniqueId());
        requestDTO.setRemark(request.getRemark());
        requestDTO.setIterationCount(request.getIterationCount());
        if(request.getRole().equals("agent")){
            requestDTO.setPassword(request.getPassword());
        }
        return requestDTO;
    }

    public List<User> convertUserDTOToUserList(List<UserDTO> userDTOList) {
        List<User> userList = new ArrayList<>();

        for (UserDTO userDTO : userDTOList) {
            User user = new User();
            user.setId(userDTO.getId());
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setDob(String.valueOf(userDTO.getDob()));
            user.setPassword(userDTO.getPassword());
            user.setBloodGroup(userDTO.getBloodGroup());
            user.setRole(userDTO.getRole());
            user.setAccountCreationDateTime(userDTO.getAccountCreationDateTime());
            user.setAddress(userDTO.getAddress());
            user.setSecretCode(userDTO.getSecretCode());

            userList.add(user);
        }

        return userList;
    }


    public User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDob(String.valueOf(userDTO.getDob()));
        user.setPassword(userDTO.getPassword());
        user.setBloodGroup(userDTO.getBloodGroup());
        user.setRole(userDTO.getRole());
        user.setAccountCreationDateTime(userDTO.getAccountCreationDateTime());
        user.setAddress(userDTO.getAddress());
        user.setSecretCode(userDTO.getSecretCode());


        return user;
    }


    public List<Request> convertRequestDTOToRequestList(List<RequestDTO> requestDTOList) {
        List<Request> requestList = new ArrayList<>();

        for (RequestDTO requestDTO : requestDTOList) {
            Request request = new Request();
            request.setId(requestDTO.getId());
            request.setName(requestDTO.getName());
            request.setAge(requestDTO.getAge());
            request.setEmail(requestDTO.getEmail());
            request.setUnit(requestDTO.getUnit());
            request.setBloodGroup(requestDTO.getBloodGroup());
            request.setAddress(requestDTO.getAddress());
            request.setLocalDateTime(requestDTO.getLocalDateTime());
            request.setRole(requestDTO.getRole());
            request.setType(requestDTO.getType());
            request.setStatus(requestDTO.getStatus());
            request.setUniqueId(requestDTO.getUniqueId());
            request.setRemark(requestDTO.getRemark());
            request.setIterationCount(requestDTO.getIterationCount());
            requestList.add(request);
        }

        return requestList;
    }

    public Request convertRequestDTOToRequest(RequestDTO requestDTO) {
        Request request = new Request();
        request.setId(requestDTO.getId());
        request.setName(requestDTO.getName());
        request.setAge(requestDTO.getAge());
        request.setEmail(requestDTO.getEmail());
        request.setUnit(requestDTO.getUnit());
        request.setBloodGroup(requestDTO.getBloodGroup());
        request.setAddress(requestDTO.getAddress());
        request.setLocalDateTime(requestDTO.getLocalDateTime());
        request.setRole(requestDTO.getRole());
        request.setType(requestDTO.getType());
        request.setStatus(request.getStatus());
        request.setUniqueId(requestDTO.getUniqueId());
        request.setRemark(requestDTO.getRemark());
        request.setIterationCount(requestDTO.getIterationCount());

        System.out.println(request + " from utility=== ");
        return request;

    }


    public boolean checkUserDuplicateDonate(RequestDTO requestDTO) {

        for (Request existingRequest : requestRepoList.findAll()) {
            if (existingRequest.getEmail().equals(requestDTO.getEmail()) &&
                    existingRequest.getType().equals("Donate") &&
                    existingRequest.getStatus().equals("Pending")) {
                System.out.println("counting+++++++++++++++++++++ " + existingRequest);
                throw new RequestException("Duplicate Donor: Your previous request for this blood unit is pending. Wait for clearance.");
            }
        }
        System.out.println("new========+++++++++++++++++++++++++====");
        return true;
    }

    public boolean checkUserDuplicateReceive(RequestDTO requestDTO) {

        for (Request existingRequest : requestRepoList.findAll()) {
            if (existingRequest.getEmail().equals(requestDTO.getEmail()) &&
                    existingRequest.getType().equals("Receive") &&
                    existingRequest.getStatus().equals("Pending")) {
                System.out.println("counting+++++++++++++++++++++ " + existingRequest);
                throw new RequestException("Duplicate Receiver: Your previous request for this blood unit is pending. Wait for clearance.");
            }
        }
        System.out.println("new========+++++++++++++++++++++++++====");
        return true;
    }

    public Map<String, Integer> getBloodUnitsByGroup() {


        Map<String, Integer> bloodUnitsByGroup = new LinkedHashMap<>();
        bloodUnitsByGroup.put("A+", 0);
        bloodUnitsByGroup.put("A-", 0);
        bloodUnitsByGroup.put("B+", 0);
        bloodUnitsByGroup.put("B-", 0);
        bloodUnitsByGroup.put("AB+", 0);
        bloodUnitsByGroup.put("AB-", 0);
        bloodUnitsByGroup.put("O+", 0);
        bloodUnitsByGroup.put("O-", 0);

        for (Request request : requestRepoList.findAll()) {
            if (request.getStatus() == "Accepted") {
                String bloodGroup = request.getBloodGroup();
                int units = request.getUnit();
                if (request.getType().equals("Donate")) {
                    bloodUnitsByGroup.put(bloodGroup, bloodUnitsByGroup.getOrDefault(bloodGroup, 0) + units);
                } else {
                    bloodUnitsByGroup.put(bloodGroup, bloodUnitsByGroup.getOrDefault(bloodGroup, 0) - units);
                }
            }
        }

        return bloodUnitsByGroup;
    }

    public List<BloodGroupReportDTO> getBloodUnitsByGroupAndUserCount() {
        Map<String, Integer> bloodUnitsByGroup = new LinkedHashMap<>();
        Map<String, Integer> donorCountByGroup = new LinkedHashMap<>();
        bloodUnitsByGroup.put("A+", 0);
        bloodUnitsByGroup.put("A-", 0);
        bloodUnitsByGroup.put("B+", 0);
        bloodUnitsByGroup.put("B-", 0);
        bloodUnitsByGroup.put("AB+", 0);
        bloodUnitsByGroup.put("AB-", 0);
        bloodUnitsByGroup.put("O+", 0);
        bloodUnitsByGroup.put("O-", 0);

        for (Request request : requestRepoList.findAll()) {
            if (request.getStatus().equals("Accepted")) {
                String bloodGroup = request.getBloodGroup();
                int units = request.getUnit();
                if (request.getType().equals("Donate")) {
                    bloodUnitsByGroup.put(bloodGroup, bloodUnitsByGroup.getOrDefault(bloodGroup, 0) + units);

                } else {
                    bloodUnitsByGroup.put(bloodGroup, bloodUnitsByGroup.getOrDefault(bloodGroup, 0) - units);
                }

                donorCountByGroup.put(bloodGroup, donorCountByGroup.getOrDefault(bloodGroup, 0) + 1);
            }
        }
        List<BloodGroupReportDTO> bloodGroupReportDTOListList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : bloodUnitsByGroup.entrySet()) {
            String bloodGroup = entry.getKey();
            int units = entry.getValue();
            int donorCount = donorCountByGroup.getOrDefault(bloodGroup, 0);
            bloodGroupReportDTOListList.add(new BloodGroupReportDTO(bloodGroup, units, donorCount));
        }
        return bloodGroupReportDTOListList;
    }

    public List<BloodGroupReportDTO> getBloodBankReportCard() {

        Map<String, Integer> bloodUnitsByGroup = new LinkedHashMap<>();
        Map<String, Integer> bloodGroupByDonateReq = new LinkedHashMap<>();
        Map<String, Integer> bloodGroupByReceiveReq = new LinkedHashMap<>();
        Map<String, Integer> bloodGroupByAcceptReq = new LinkedHashMap<>();
        Map<String, Integer> bloodGroupByRejectReq = new LinkedHashMap<>();
        Map<String, Integer> bloodGroupByPendingReq = new LinkedHashMap<>();

        bloodUnitsByGroup.put("A+", 0);
        bloodUnitsByGroup.put("A-", 0);
        bloodUnitsByGroup.put("B+", 0);
        bloodUnitsByGroup.put("B-", 0);
        bloodUnitsByGroup.put("AB+", 0);
        bloodUnitsByGroup.put("AB-", 0);
        bloodUnitsByGroup.put("O+", 0);
        bloodUnitsByGroup.put("O-", 0);

        for (Request request : requestRepoList.findAll()) {
            String bloodGroup = request.getBloodGroup();
            if (request.getStatus() == "Accepted") {

                int units = request.getUnit();
                if (request.getType().equals("Donate")) {
                    bloodUnitsByGroup.put(bloodGroup, bloodUnitsByGroup.getOrDefault(bloodGroup, 0) + units);
                } else {
                    bloodUnitsByGroup.put(bloodGroup, bloodUnitsByGroup.getOrDefault(bloodGroup, 0) - units);
                }

                if (request.getType().equals("Donate")) {
                    bloodGroupByDonateReq.put(bloodGroup, bloodGroupByDonateReq.getOrDefault(bloodGroup, 0) - 1);

                }
                if (request.getType().equals("Receive")) {
                    bloodGroupByReceiveReq.put(bloodGroup, bloodGroupByReceiveReq.getOrDefault(bloodGroup, 0) - 1);
                }

            }else if(request.getStatus()=="Rejected"){

                if (request.getType().equals("Donate")) {
                    bloodGroupByDonateReq.put(bloodGroup, bloodGroupByDonateReq.getOrDefault(bloodGroup, 0) - 1);

                }
                if (request.getType().equals("Receive")) {
                    bloodGroupByReceiveReq.put(bloodGroup, bloodGroupByReceiveReq.getOrDefault(bloodGroup, 0) - 1);
                }
            }

            if (request.getType().equals("Donate")) {
                bloodGroupByDonateReq.put(bloodGroup, bloodGroupByDonateReq.getOrDefault(bloodGroup, 0) + 1);
            }
            if (request.getType().equals("Receive")) {
                bloodGroupByReceiveReq.put(bloodGroup, bloodGroupByReceiveReq.getOrDefault(bloodGroup, 0) + 1);
            }

            if (request.getStatus().equals("Accepted")) {
                bloodGroupByAcceptReq.put(bloodGroup, bloodGroupByAcceptReq.getOrDefault(bloodGroup, 0) + 1);
            }
            if (request.getStatus().equals("Rejected")) {
                bloodGroupByRejectReq.put(bloodGroup, bloodGroupByRejectReq.getOrDefault(bloodGroup, 0) + 1);
            }
            if (request.getStatus().equals("Pending")) {
                bloodGroupByPendingReq.put(bloodGroup, bloodGroupByPendingReq.getOrDefault(bloodGroup, 0) + 1);
            }
        }

        List<BloodGroupReportDTO> bloodGroupReportDTOS = new ArrayList<>();

        for (String bloodGroup : bloodUnitsByGroup.keySet()) {
            BloodGroupReportDTO reportDTO = new BloodGroupReportDTO();
            reportDTO.setBloodGroup(bloodGroup);
            reportDTO.setUnits(bloodUnitsByGroup.get(bloodGroup));
            reportDTO.setDonateReq(bloodGroupByDonateReq.getOrDefault(bloodGroup, 0));
            reportDTO.setReceiveReq(bloodGroupByReceiveReq.getOrDefault(bloodGroup, 0));
            reportDTO.setAcceptReq(bloodGroupByAcceptReq.getOrDefault(bloodGroup, 0));
            reportDTO.setRejectReq(bloodGroupByRejectReq.getOrDefault(bloodGroup, 0));
            reportDTO.setPendingReq(bloodGroupByPendingReq.getOrDefault(bloodGroup, 0));
            bloodGroupReportDTOS.add(reportDTO);
        }

        return bloodGroupReportDTOS;
    }

    public void validateAgentUser(UserDTO userDTO){
        Request request =requestRepoList.findByEmail(userDTO.getEmail()).orElseThrow(()-> new AgentUserException("You don't have an agent user account!! Please contact your agent")) ;
        if (request.getRole().equals("agent")) {
            if (!request.getPassword().equals(userDTO.getPassword())) {
                throw new AgentUserException("Wrong password entered");
        }
        }
    }
}