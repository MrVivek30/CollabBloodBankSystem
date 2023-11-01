package com.trackobit.controller;


import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/userDashboard")
    public String showUserDashboard(Model model, HttpSession session, HttpSession session2) {
        UserDTO currentUser = (UserDTO) session.getAttribute("currentUser");
        session.setAttribute("UserSession", currentUser);
        session2.setAttribute("user", currentUser);
        model.addAttribute("user", currentUser);
        return "user/userDashboard";
    }

    @GetMapping("/profile")
    public String getUserProfileDetails(HttpSession session, Model model) {
        UserDTO currUser = (UserDTO) session.getAttribute("user");
        model.addAttribute("curr", currUser);
        return "user/profile";
    }

    @GetMapping("/history")
    public String getUserHistoryHandler(HttpSession session, Model model) {
        UserDTO curr = (UserDTO) session.getAttribute("user");
        List<RequestDTO> userHistory = userService.getUserHistory(curr.getEmail());
        model.addAttribute("history", userHistory);
        return "user/history";

    }



}
