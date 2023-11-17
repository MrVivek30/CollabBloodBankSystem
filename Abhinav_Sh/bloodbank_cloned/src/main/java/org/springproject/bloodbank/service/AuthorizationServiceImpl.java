package org.springproject.bloodbank.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springproject.bloodbank.dao.DataBaseDAO;
import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.exceptions.UserEntryException;
import org.springproject.bloodbank.model.User;
import org.springproject.bloodbank.utils.Utility;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.springproject.bloodbank.dao.DataBaseDAO.createAdmin;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private static final Logger logger = LogManager.getLogger("vehicles");
    @Autowired
    RequestService requestService;

    public String checkUserCred(String username, String password, Model model, HttpSession httpSession) throws LoginException {
        String genHash = Utility.getHashFromString(password);
        boolean isEmail = username.contains("@");
        boolean userExists = DataBaseDAO.isEmailExist(username);

        if (userExists) {
            User user = DataBaseDAO.getUser(username);
            if (isBlocked(username))
                throw new LoginException("The user Login has been blocked");
            if (user.getPassword().equalsIgnoreCase(genHash)) {
                UserDTO viewDTO = new UserDTO(); // create a dto object
                viewDTO.setUserDTO(user); // store it in the session
                httpSession.setAttribute("loggedInUser", user);
                // i am setting the actual user object in the session
                httpSession.setAttribute("user", viewDTO); // and the dto to the session too
                return "";
            } else {
                throw new LoginException("Password incorrect" + ", you are left with " + changeChances(username) + " login attempts");
            }
        } else {
            throw new LoginException("No user found with the login details");
        }
    }

    public boolean resetPass(String email, String securityCode) throws UserEntryException {
        User bloodBankUser; // Here i authenticate the user email and the security code
        try {
            bloodBankUser = DataBaseDAO.
                    getUsers().stream().
                    filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst().get();
        } catch (Exception e) {
            throw new UserEntryException("No such email is found");
        }
        if (bloodBankUser.securityCode.equalsIgnoreCase(securityCode)) {
            return true;
        } else {
            throw new UserEntryException("Wrong Security Code");
        }
    }

    public void resetPass(String email, String securityCode, String newPass) throws UserEntryException {
        User bloodBankUser; // Here i recieve the authenticated email and the security code and  display a password reset page
        try {
            bloodBankUser = DataBaseDAO.
                    getUsers().stream().
                    filter(user -> user.getEmail().equalsIgnoreCase(email)).
                    filter(user -> user.securityCode.equalsIgnoreCase(securityCode)).
                    findFirst().get();
            bloodBankUser.setPassword(Utility.getHashFromString(newPass));
        } catch (Exception e) {
            throw new UserEntryException("Bad request");
        }
    }

    public String checkCredInRequest(String username, String password, Model model, HttpSession httpSession) {
        ArrayList<RequestDTO> isTheRequestUserPresent = requestService.getRequestsUsingUsersEmailAndPass(username, password);
        httpSession.setAttribute("arrayList", isTheRequestUserPresent);
        if (!isTheRequestUserPresent.isEmpty()) { // if the arraylist is not empty
            boolean isFirstTimeLogin = Utility.combineFlagResultOfFirstTimeLoginOfAll(isTheRequestUserPresent);
            if (isFirstTimeLogin) {
                logger.info(" The user is first time logging in  ??" + isFirstTimeLogin);
                model.addAttribute("username", username);
                return "requestUserPasswordReset"; // if user login first time , then password reset
            } else {
                logger.info("User is not first time logging in " + isFirstTimeLogin);
                return "requestUserHistory"; // directly show it the history
            }
        } else {
            logger.info("The Request username could not be found");
            logger.info("So the current value of the error inside the Model attribute is  " + model.getAttribute("error"));
            model.addAttribute("error", "The user credentials not valid");
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            logger.info("There was an error ");
            return "login";
        }

    }

    public String loginResolver(String user, String pass, Model model, HttpSession httpSession) {
        logger.info("The login request has been recieved");
        if (user == null)
            return "redirect:/bloodbank/auth/home";
        logger.info("Checking if the login details are right");
        try {
            ifLoginPassElse(user, pass, model, httpSession);
            logger.info("Your creds are rights so logging in and moving to the dashboard");
            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
            return "redirect:/bloodbank/" + userDTO.getRole().toLowerCase() + "/dashboard";
        } catch (LoginException e) {
            model.addAttribute("userName", user);
            model.addAttribute("userPass", pass);
            logger.info("The USer's list authentication was failed so redirecting to the Request list ");
            logger.info("The value of error that was retrieved" + e.getMessage());
            if (Utility.decideIfUserOrRequest(e.getMessage())) {
                model.addAttribute("error", e.getMessage());
                return "login";
            }// if user esists
            else {
                return checkCredInRequest(user, pass, model, httpSession);
            }
        }
    }

    public void unBlockUser(String email) {
        User bloodBankUser = DataBaseDAO.
                getUsers().stream().
                filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst().get();
        bloodBankUser.chances = 4;
        bloodBankUser.locked = false;
    }

    public int changeChances(String username) {

        User bloodBankUser = DataBaseDAO.getUsers().
                stream().
                filter(user -> user.getEmail().equalsIgnoreCase(username)).findFirst().get();
        if (!bloodBankUser.locked) {// if it is not locked
            if (bloodBankUser.chances == 1) {
                bloodBankUser.chances -= 1; // claim the last chance
                bloodBankUser.locked = true; // and block the account
                DataBaseDAO.saveArrayListToFile(DataBaseDAO.getUsers());
            } else {
                bloodBankUser.chances -= 1;
                DataBaseDAO.saveArrayListToFile(DataBaseDAO.getUsers());
            }
        }
        return bloodBankUser.chances;
    }

    public boolean isBlocked(String email) {
        return DataBaseDAO.getUsers().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst().get().locked;
    }

    public String ifLoginPassElse(String user,
                                  String pass,
                                  Model model,
                                  HttpSession httpSession) throws LoginException {
        createAdmin();
        String str = this.checkUserCred(user, pass, model, httpSession);
        return str;
    }
}
