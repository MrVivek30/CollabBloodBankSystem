package com.trackobit.controller;

import com.trackobit.exception.RequestException;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequestMapping("/agent")
@Controller
public class AgentController {
    @Autowired
    AgentService agentService;

    @GetMapping("/donate")
    public String showDonateFrom(Model model) {
        RequestDTO request = new RequestDTO();
        model.addAttribute("donate", request);
        return "agent/agentDonate";
    }

    @PostMapping("/donate")
    public String donateBlood(RequestDTO request, Model model, HttpSession session) {
        UserDTO currUser = (UserDTO) session.getAttribute("UserSession");
        try {
            agentService.donateBloodToBloodBank(request, currUser);
            model.addAttribute("msg", "Thank You " + request.getName() + " For Blood Donation! Your contribution is invaluable and can save lives. Your act of kindness and generosity will make a significant difference in someone's life. Please consider donating again in the future.");
            return "messagesRedirect/successDashBoard";
        } catch (RequestException e) {
            model.addAttribute("error", e.getMessage());
            System.out.println("---from  agent controller donateBlood");
            return "agent/agentDonate";
        }
    }

    @GetMapping("/receive")
    public String showReceiveFrom(Model model) {
        RequestDTO request = new RequestDTO();
        model.addAttribute("receive", request);
        return "agent/agentReceive";
    }

    @PostMapping("/receive")
    public String receiveBlood(RequestDTO request, Model model, HttpSession session) {
        UserDTO currUser = (UserDTO) session.getAttribute("UserSession");
        try {
            agentService.receiveBloodFromBloodBank(request, currUser);
            model.addAttribute("msg", "Hey " + currUser.getFirstName() + " we get your Blood Request ! we will resolve your blood request as soon as possible plz be patience ! Thank you ");
            System.out.println(request + " from agent");
            return "messagesRedirect/successDashBoard";
        } catch (RequestException e) {
            model.addAttribute("error", e.getMessage());
            return "agent/agentReceive";
        }
    }

    @GetMapping("/history")
    public String getUserHistoryHandler(HttpSession session, Model model) {
        UserDTO curr = (UserDTO) session.getAttribute("user");
        List<RequestDTO> userHistory = agentService.getUserHistory(curr.getId());
        model.addAttribute("history", userHistory);
        return "user/history";

    }
}
