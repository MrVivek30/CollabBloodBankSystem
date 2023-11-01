package com.trackobit.utility;

import com.trackobit.dto.BloodGroupReportDTO;
import com.trackobit.exception.RequestException;
import com.trackobit.exception.UserException;
import com.trackobit.model.Request;
import com.trackobit.model.User;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.repository.DataBase;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServiceHelper {
    public List<UserDTO> convertUserToUserDTOList(List<User> userRepoList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userRepoList) {
            UserDTO userDTO = new UserDTO();

            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setDob(user.getDob());
            userDTO.setPassword(user.getPassword());
            userDTO.setBloodGroup(user.getBloodGroup());
            userDTO.setRole(user.getRole());
            userDTO.setAccountCreationDateTime(user.getAccountCreationDateTime());
            userDTO.setAddress(user.getAddress());

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
        userDTO.setDob(user.getDob());
        userDTO.setPassword(user.getPassword());
        userDTO.setBloodGroup(user.getBloodGroup());
        userDTO.setRole(user.getRole());
        userDTO.setAccountCreationDateTime(user.getAccountCreationDateTime());
        userDTO.setAddress(user.getAddress());

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
            user.setDob(userDTO.getDob());
            user.setPassword(userDTO.getPassword());
            user.setBloodGroup(userDTO.getBloodGroup());
            user.setRole(userDTO.getRole());
            user.setAccountCreationDateTime(userDTO.getAccountCreationDateTime());
            user.setAddress(userDTO.getAddress());

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
        user.setDob(userDTO.getDob());
        user.setPassword(userDTO.getPassword());
        user.setBloodGroup(userDTO.getBloodGroup());
        user.setRole(userDTO.getRole());
        user.setAccountCreationDateTime(userDTO.getAccountCreationDateTime());
        user.setAddress(userDTO.getAddress());

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

    public void validateRequest(RequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new RequestException("Request object cannot be null.");
        }

        if (requestDTO.getName() == null || !requestDTO.getName().matches("^[A-Za-z]*$")) {
            throw new RequestException("Invalid name. Name should not contain numbers or special characters.");
        }

        if (requestDTO.getAge() < 18 || requestDTO.getAge() > 90) {
            throw new RequestException("Age must be between 18 and 90 years.");
        }

        if (requestDTO.getEmail() == null || !requestDTO.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new RequestException("Invalid email address. Please enter a valid email address.");
        }

        if (requestDTO.getUnit() < 0) {
            throw new RequestException("Unit must be greater than 0.");
        }

        if (requestDTO.getBloodGroup() == null || !requestDTO.getBloodGroup().matches("^(A|B|AB|O)[+-]$")) {
            throw new RequestException("Invalid blood group. Please enter a valid blood group.");
        }

        if (requestDTO.getAddress() == null || requestDTO.getAddress().trim().isEmpty()) {
            throw new RequestException("Address cannot be blank.");
        }

        if (requestDTO.getRole() == null || requestDTO.getRole().trim().isEmpty()) {
            throw new RequestException("Role cannot be blank.");
        }

        if (requestDTO.getType() == null || requestDTO.getType().trim().isEmpty()) {
            throw new RequestException("Type cannot be blank.");
        }

    }

    public void validateUser(UserDTO userDTO) {
        Integer age = LocalDate.now().getYear() - LocalDate.parse(userDTO.getDob()).getYear();
        if (userDTO == null) {
            throw new UserException("User object cannot be null.");
        }

        if (userDTO.getFirstName() == null || !userDTO.getFirstName().matches("^[A-Za-z]+$")) {
            throw new UserException("Invalid first name. First name should contain only Alphabets .");
        }

        if (userDTO.getLastName() == null || !userDTO.getLastName().matches("^[A-Za-z]+$")) {
            throw new UserException("Invalid last name. Last name should contain only  Alphabets .");
        }

        if (age <= 18 || age >= 90) {
            throw new UserException("Age must be between 18 - 90 to register.");
        }

        if (userDTO.getEmail() == null) {
            throw new UserException("Invalid email address. Please enter a valid email address.");
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().length() < 6) {
            throw new UserException("Password must be at least 6 characters.");
        }

        if (!userDTO.getBloodGroup().matches("^(A|B|AB|O)[+-]$")) {
            throw new UserException("Invalid blood group. Please enter a valid blood group.");
        }

        if (userDTO.getRole() == null) {
            throw new UserException("Role cannot be blank.");
        }

        if (userDTO.getAccountCreationDateTime() == null) {
            throw new UserException("Account creation date and time cannot be null.");
        }

        if (userDTO.getAddress() == null) {
            throw new UserException("Address cannot be blank.");
        }
    }

    public boolean checkUserDuplicateDonate(RequestDTO requestDTO) {

        for (Request existingRequest : DataBase.requestRepoList) {
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

        for (Request existingRequest : DataBase.requestRepoList) {
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

        for (Request request : DataBase.requestRepoList) {
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

        for (Request request : DataBase.requestRepoList) {
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

        for (Request request : DataBase.requestRepoList) {
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
}