package org.springproject.bloodbank.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.model.Request;
import org.springproject.bloodbank.service.DashBoardService;
import org.springproject.bloodbank.service.RequestService;
import org.springproject.bloodbank.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    RequestService requestService;
    @Autowired
    DashBoardService dashBoardService;

    @GetMapping(path = "/profile")
    public String profile(HttpSession httpSession) {
        return "user_profile";
    }

    @GetMapping(path = "/dashboard")
    public String dashboard(HttpSession httpSession) {
        return "user_dashboard";
    }

    @GetMapping(path = {"/deleteAccount"})
    public String deleteUser(HttpSession httpSession) throws ServletException, IOException {
        dashBoardService.delUser(httpSession);
        return "forward:/bloodbank/auth/home";
    }

    @PostMapping(path = "/editRequestSubmitTo")
    public String editRequestSubmitTo(@RequestParam(value = "index") String indice, @ModelAttribute("requestData") RequestDTO requestDTO
            , HttpSession httpSession) {
        return requestService.updateRequestAndRedirectForUser(indice, requestDTO, httpSession);
    }

    @PostMapping(path = "/editRequest")
    public String editRequest(HttpSession httpSession, @RequestParam(value = "index", required = false) String index, Model model) {
        ArrayList<RequestDTO> dtos = (ArrayList<RequestDTO>) httpSession.getAttribute("arData");
        // if the arrayList retrieved from the choices
        int arrayListIndex = Integer.valueOf(index);
        System.err.println("Found index : " + arrayListIndex);
        RequestDTO requestDTO = dtos.get(arrayListIndex);
        String submitTheEditedTo = "user/editRequestSubmitTo";
        model.addAttribute("index", index);
        model.addAttribute("date", requestDTO.getDateTillOrOn().toString());
        model.addAttribute("units", requestDTO.getNoOfUnits());
        model.addAttribute("editRequestSubmitTo", submitTheEditedTo);
        if (requestDTO.getAction().equalsIgnoreCase("donate"))
            return "user_side_donate";
        else
            return "user_side_recieve";
    }

    @GetMapping(path = "/request")
    public String request(@RequestParam(name = "type", required = false)
                          String request,
                          Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        String role = user.getRole().equalsIgnoreCase("AGENT") ? "agent" : "user";
        String action = request.equalsIgnoreCase("recieve") ? "recieve" : "donate";

        return role + "_side_" + action;
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "forward:/bloodbank/auth/home";
    }

    @GetMapping(path = "/history")
    public String history(Model model, HttpSession httpSession) {
        ArrayList<RequestDTO> dtos = requestService.getRequestDTO(httpSession);
        System.err.println("Its working , is the arraylist retrieved ");
        model.addAttribute("arData", dtos);
        return "user_history";
    }
}
