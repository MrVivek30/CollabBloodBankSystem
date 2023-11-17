package com.trackobit.service;

import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.exception.UserException;

import java.util.List;

public interface UserService {
    public UserDTO signUp(UserDTO userDTO) throws UserException;

    public UserDTO login(String email, String password) throws UserException;

    public UserDTO getUserByEmail(String email) throws UserException;

    public List<RequestDTO> getUserHistory(String email) throws UserException;

    public UserDTO authenticateSecretCode(UserDTO userDTO);

    public void changePassword(int id,String password);
}