package org.springproject.bloodbank.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springproject.bloodbank.dao.DataBaseDAO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.exceptions.UserEntryException;
import org.springproject.bloodbank.service.AuthorizationService;
import org.springproject.bloodbank.service.RequestService;
import org.springproject.bloodbank.utils.Validation;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LogManager.getLogger("vehicles");
    @Autowired
    RequestService requestService;
    @Autowired
    AuthorizationService authorizationService;

    @PostMapping(path = "/registeruser")
    public String registerUser(@ModelAttribute("user") UserDTO user, Model model) {
        logger.info("A new registration request has been initiated");
        logger.info("Checking the validation using the SignupValidation.signUpValidationResolver(user)");
        logger.info("If there are any error messages appended to the string then \n its size will be more than 0 \n so error page is displayed \n");
        try {
            Validation.signUpValidationResolver(user);
            logger.info("No errors in the validation has been detected so adding it to the DataBaseDAO");
            DataBaseDAO.addUser(user);
            logger.info("Redirecting to the home page");
            return "home_page";
        } catch (Exception e) {
            logger.info("Errors on validaion has been detected so check again the mistakes");
            model.addAttribute("error", e.getMessage());
            logger.info("The signup page has been dispatched from this method");
            return "signup";
        }
    }

    @PostMapping("/requestUserPassReset")
    public String requestUserPassReset(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "newPassword", required = false) String newPassword) {
        requestService.passwordReset(username, newPassword);
        return "requestUserLogin";
    }

    @PostMapping(path = {"/authenticate"})
    public String authenticate(@RequestParam(value = "username", required = false) String user,
                               @RequestParam(value = "password", required = false) String pass,
                               Model model,
                               HttpSession httpSession, HttpServletResponse servletResponse) {
        String dashBoardOrNot=authorizationService.loginResolver(user, pass, model, httpSession);
        if(dashBoardOrNot.contains("dashboard")){
            servletResponse.addCookie("token",);
        }
        return
    }

    @GetMapping("/forgetPass")
    public String forgetPass() {
        return "forgetPass";
    }

    @PostMapping("/forgetPass")
    public String forgetPass(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "securityCode", required = false) String securityCode, Model model) throws UserEntryException {
        model.addAttribute("email", email);
        model.addAttribute("securityCode", securityCode);
        try {
            authorizationService.resetPass(email, securityCode);
            return "resetPass";
        } catch (UserEntryException e) {
            model.addAttribute("error", e.getMessage());
            return "forgetPass";
        }
    }

    @PostMapping("/newPass")
    public String newPass(@RequestParam(value = "email", required = false) String email,
                          @RequestParam(value = "securityCode", required = false) String securityCode,
                          @RequestParam(value = "newPass", required = false) String newPass,
                          Model model
    ) {
        try {
            authorizationService.resetPass(email, securityCode, newPass);
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "resetPass";
        }
    }

    @GetMapping(path = "/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(HttpSession httpSession) {
        return "home_page";
    }
}
