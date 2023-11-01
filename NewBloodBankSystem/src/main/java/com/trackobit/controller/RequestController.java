package com.trackobit.controller;

import com.trackobit.exception.RequestException;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/request")
public class RequestController {
    @Autowired
    RequestService requestService;

    @GetMapping("/donate")
    public String showDonateFrom(Model model) {
        RequestDTO request = new RequestDTO();
        model.addAttribute("donate", request);
        return "user/donate";
    }

    @PostMapping("/donate")
    public String donateBlood(RequestDTO request, Model model, HttpSession session) {
        UserDTO currUser = (UserDTO) session.getAttribute("UserSession");
        try {
            requestService.donateBloodToBloodBank(request, currUser);
            model.addAttribute("msg", "Thank You " + currUser.getFirstName() + "For Blood Donation! Your contribution is invaluable and can save lives. Your act of kindness and generosity will make a significant difference in someone's life. Please consider donating again in the future.");
            return "messagesRedirect/successDashBoard";
        } catch (RequestException e) {
            model.addAttribute("msg", e.getMessage());
            return "validation/error";
        }
    }

    @GetMapping("/receive")
    public String showReceiveFrom(Model model) {
        RequestDTO request = new RequestDTO();
        model.addAttribute("receive", request);
        return "user/receive";
    }

    @PostMapping("/receive")
    public String receiveBlood(RequestDTO request, Model model, HttpSession session) {
        UserDTO currUser = (UserDTO) session.getAttribute("UserSession");
        try {
            requestService.receiveBloodFromBloodBank(request, currUser);
            model.addAttribute("msg", "Hey " + currUser.getFirstName() + " we get your Blood Request ! we will resolve your blood request as soon as possible plz be patience ! Thank you ");
            return "messagesRedirect/successDashBoard";
        } catch (RequestException e) {
            model.addAttribute("msg", e.getMessage());
            return "validation/error";
        }
    }



    @GetMapping("/editRequest")
    public String showEditForm(@RequestParam("uniqueId") int requestId,Model model,HttpSession session) {
        RequestDTO requestDTO = requestService.getRequestById(requestId);
        session.setAttribute("uniqueId",requestId);
        System.out.println(requestDTO+"====================from editform");
        model.addAttribute("editRequest",requestDTO);
        return "user/editRequest";
    }


    @PostMapping("/editRequest")
    public String editRequest(@ModelAttribute("request") RequestDTO editedRequest,HttpSession session) {
     int uniqueId= (int) session.getAttribute("uniqueId");
        requestService.editRequest(uniqueId,editedRequest);
        System.out.println(editedRequest+" ===========> resubmit");
        return "redirect:/user/history";
    }
}
