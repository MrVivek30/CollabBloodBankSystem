package com.trackobit.service;

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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    DataBase dataBase;

    @Autowired
    ServiceHelper serviceHelper;

    @Override
    public UserDTO signUp(UserDTO userDTO) throws UserException {
        serviceHelper.validateUser(userDTO);
        for (User user : DataBase.userRepoList) {
            if (user.getEmail().equals(userDTO.getEmail())) {
                throw new UserException("User already exists. Please log in with your username and password.");
            }
        }
        DataBase.userRepoList.add(serviceHelper.convertUserDTOToUser(userDTO));
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) throws UserException {
        for (User user : DataBase.userRepoList) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {

                    return serviceHelper.convertUserToUserDTO(user);

                } else {
                    throw new UserException("Wrong password. Please try again.");
                }
            }
        }
        throw new UserException("Invalid username. Please try again.");
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        for (User u : DataBase.userRepoList) {
            if (!(u.getEmail().equals(email))) throw new UserException("Email not found " + email);

            else return serviceHelper.convertUserToUserDTO(u);
        }
        return null;
    }

    @Override
    public List<RequestDTO> getUserHistory(String email) throws UserException {
        List<Request> userHistory = DataBase.requestRepoList;
        List<RequestDTO> history = new ArrayList<>();
        if (userHistory.isEmpty()) {
            throw new UserException("History is empty from email: " + email + " donate or receive to see history");
        } else {
            for (Request request : userHistory) {
                if (request.getEmail().equals(email)) {
                    history.add(serviceHelper.convertRequestToRequestDTO(request));
                }
            }
        }
        return history;
    }


}
