package com.trackobit.controller;

import com.trackobit.dto.UserDTO;
import com.trackobit.exception.UserException;
import com.trackobit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RequestMapping("/auth")
@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        System.out.println("ho rha=====================>>>>>>>>>>>>>>>");
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserDTO user, Model model) {
        user.setAccountCreationDateTime(LocalDateTime.now());
        System.out.println(user);
        try {
            UserDTO savedUser = userService.signUp(user);
            model.addAttribute("message", "User registration successful for " + savedUser.getEmail() );
            return "messagesRedirect/signupSuccess";
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/signup";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login( UserDTO user, Model model, HttpSession session) {
        try {
            UserDTO authenticatedUser = userService.login(user.getEmail(), user.getPassword());
            session.setAttribute("currentUser", authenticatedUser);
            model.addAttribute("message", "Welcome to dashboard " + authenticatedUser.getFirstName());
            if (user.getEmail().equals("admin@gmail.com"))   return "messagesRedirect/adminSuccess";
            return "messagesRedirect/loginSuccess";
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/login";
        }
    }

    @GetMapping("/forget")
    public String showForgetPasswordForm(){
        return "auth/forget";
    }

    @PostMapping("/forgetpassword")
    public String forgetPassword(UserDTO userdto,Model model){
        try{
            UserDTO userDTO = userService.authenticateSecretCode(userdto);
            System.out.println(userDTO);
            model.addAttribute("name",userDTO.getFirstName());
            model.addAttribute("id",userDTO.getId());
            return "auth/changePassword";
        }
        catch (UserException ex){
            model.addAttribute("error", ex.getMessage());
            return "auth/forget";
        }
    }

    @PostMapping("/changepassword/{id}")
    public String changePassword(UserDTO userdto,@PathVariable int id){

        userService.changePassword(id,userdto.getPassword());

        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        model.addAttribute("msg", "Logout successful");
        session.invalidate();
        return "messagesRedirect/SuccessPage";
    }

    //For Agent users

}
