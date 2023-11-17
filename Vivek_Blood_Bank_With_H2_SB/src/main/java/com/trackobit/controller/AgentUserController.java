package com.trackobit.controller;

import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.exception.AgentUserException;
import com.trackobit.service.AgentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/com/BloodBank/v1/agentuser")
@Controller
public class AgentUserController {

    @Autowired
    AgentUserService agentUserService;

    @GetMapping("/login")
    public String showLoginPage(){
        return "agentuser/login";
    }

    @PostMapping("/login")
    public String loginAgentUser( UserDTO userDTO, Model model, HttpSession session){
        System.out.println(userDTO);

        try{
            RequestDTO requestDTO = agentUserService.authenticateAgentUser(userDTO);
            System.out.println(requestDTO);

            if(requestDTO.getPassword().equals("Test@123")){
                model.addAttribute("request",requestDTO);
                return "agentuser/forget";
            }
            else{
                session.setAttribute("request",requestDTO);
                return "redirect:/com/BloodBank/v1/agentuser/dashboard";
            }

        }
        catch (AgentUserException ex){
            model.addAttribute("error", ex.getMessage());
            return "agentuser/login";
        }
    }

    @PostMapping("changepassword/{email}")
    public String showResetPasswordForm(@RequestParam String password,@PathVariable String email){
        agentUserService.changePassword(email,password);
        return "redirect:/com/BloodBank/v1/agentuser/login";
    }

    @GetMapping("dashboard")
    public String getDashboard(Model model,HttpSession session){
        model.addAttribute("request",session.getAttribute("request"));
        return "agentuser/dashboard";
    }

    @GetMapping("profile")
    public String getProfile(Model model,HttpSession session){
        model.addAttribute("request",session.getAttribute("request"));
        return "agentuser/profile";
    }

    @GetMapping("history")
    public String getHistory(Model model,HttpSession session){

        RequestDTO request = (RequestDTO) session.getAttribute("request");


        List<RequestDTO> requestList  =  agentUserService.getHistory(request);

        model.addAttribute("requestList",requestList);
        return "agentuser/history";
    }


}
