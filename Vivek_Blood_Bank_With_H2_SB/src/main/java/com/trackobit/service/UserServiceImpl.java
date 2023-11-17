package com.trackobit.service;

import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    UserRepository userRepoList;
    @Autowired
    ServiceHelper serviceHelper;

    @Override
    public UserDTO signUp(UserDTO userDTO) throws UserException {

        for (User user :userRepoList.findAll()) {
            if (user.getEmail().equals(userDTO.getEmail())) {
                throw new UserException("User already exists. Please log in with your username and password.");
            }
        }
       userRepoList.save(serviceHelper.convertUserDTOToUser(userDTO));
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) throws UserException {
        for (User user :userRepoList.findAll()) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                   if(user.getStatus().equals("block")){
                        throw new UserException("You are blocked by the admin");
                   }
                    user.setLoginCount(3);
                   userRepoList.save(user);
                    return serviceHelper.convertUserToUserDTO(user);
                }
                else {
                    if(!user.getRole().equals("Admin") && user.getLoginCount()>0){
                        user.setLoginCount(user.getLoginCount()-1);
                        userRepoList.save(user);
                    }
                    else if(user.getLoginCount()==0){
                        user.setStatus("block");
                        userRepoList.save(user);
                        throw new UserException("You are blocked by the admin");
                    }
                    throw new UserException("Wrong password. Please try again.");
                }
            }
        }
        throw new UserException("Invalid username. Please try again.");
    }

    @Override
    public UserDTO getUserByEmail(String email) {
     User user=  userRepoList.findByEmail(email).orElseThrow(()->new UserException("User not found with this email "+email));
       return serviceHelper.convertUserToUserDTO(user);
    }

    @Override
    public List<RequestDTO> getUserHistory(String email) throws UserException {
        List<Request> userHistory = requestRepository.findAll();
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

    public UserDTO authenticateSecretCode(UserDTO userDTO){
        System.out.println(userRepoList.findAll());
        for (User user : userRepoList.findAll()) {
            if(user.getStatus().equals("block")){
                throw new UserException("You are blocked by the admin");
            }
            if (user.getEmail().equals(userDTO.getEmail())) {
                if (user.getSecretCode().equals(userDTO.getSecretCode())) {
                    return serviceHelper.convertUserToUserDTO(user);
                }
                else {
                    throw new UserException("Wrong Secret Code entered");
                }
            }
        }
        throw new UserException("Invalid username. Please try again.");
    }

    public void changePassword(int id, String password) {

        User user =userRepoList.findById(id).orElseThrow(()->new UserException("user not found with this id "+id));

        user.setPassword(password);
userRepoList.save(user);


    }
}
