package org.springproject.bloodbank.service;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springproject.bloodbank.exceptions.UserEntryException;

public interface AuthorizationService {
    public String checkUserCred(String username,
                                String password,
                                Model model,
                                HttpSession httpSession)throws LoginException;
    public void  unBlockUser(String email);
    public String checkCredInRequest(String username, String password, Model model, HttpSession httpSession);
    public boolean resetPass(String email, String securityCode) throws UserEntryException;
    public void resetPass(String email, String securityCode,String newPass) throws UserEntryException;
    public boolean isBlocked(String email);
    public int changeChances(String username);
    public String loginResolver(String user, String pass, Model model,HttpSession httpSession);

    public String ifLoginPassElse(String us,
                                  String pass,
                                  Model model,
                                  HttpSession httpSession) throws LoginException;
}
