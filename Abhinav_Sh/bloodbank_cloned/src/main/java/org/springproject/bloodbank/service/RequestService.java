package org.springproject.bloodbank.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.model.Request;
import org.springproject.bloodbank.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface RequestService {
    public HashMap<String, Integer> getBloodCount();
    public void doAccept(int index, HttpSession httpSession, String decision, String remark);

    public void doReject(int index, ArrayList<RequestDTO> requestDTO, String str);

    public ArrayList<RequestDTO> getRequestDTO(HttpSession session);
    public ArrayList<RequestDTO> getRequestsUsingUsersEmailAndPass(String username,String password);
    public void passwordReset(String email,String newPassword);
    public ArrayList<RequestDTO> getRequestDTOAll();
    public ArrayList<UserDTO> getAllUsers();

    String requestValidator(UserDTO userDTO, Model model, RequestDTO requestDTO);
    public String updateRequestAndRedirectForUser(String indice, RequestDTO requestDTO, HttpSession httpSession);
    public String processRequestAndRedirectForAgent(String indice, RequestDTO requestDTO, HttpSession httpSession);

    public void doPending(RequestDTO requestDTOS, Request request);

    public ArrayList<RequestDTO> getRequestDTOPendingDonate();

    public ArrayList<RequestDTO> getRequestDTOPendingRecieve();

    public ArrayList<RequestDTO> filter(ArrayList<RequestDTO> req, String action, String status, String bg);
}