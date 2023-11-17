package org.springproject.bloodbank.controller;

import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.service.RequestService;
import org.springproject.bloodbank.utils.Validation;

@Controller
@RequestMapping("/request")
public class RequestController {
    private static final Logger logger = LogManager.getLogger("vehicles");
    @Autowired
    RequestService requestService;
    @PostMapping("/requestToDonateOrRecieve")
    public String requestToDonateOrRecieve(@ModelAttribute("req") RequestDTO requestDTO, Model model, HttpSession httpSession){

        UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
        if (userDTO == null) {
            logger.info("No user session is found so redirecting to home");
            throw new NullPointerException(" No user is found");
        }
        logger.info("User is "+userDTO.getRole()+" request type is "+requestDTO.getAction());
        return requestService.requestValidator(userDTO,model,requestDTO);
    }

}
