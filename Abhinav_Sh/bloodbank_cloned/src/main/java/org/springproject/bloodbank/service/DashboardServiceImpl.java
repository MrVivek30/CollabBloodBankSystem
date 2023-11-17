package org.springproject.bloodbank.service;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springproject.bloodbank.dao.DataBaseDAO;
import org.springproject.bloodbank.model.User;

@Service
public class DashboardServiceImpl implements DashBoardService {
    public void delUser(HttpSession httpSession) {
            User user = (User) httpSession.getAttribute("loggedInUser");
            if (user != null)
                DataBaseDAO.delUser(user);
            httpSession.invalidate();
    }
}
