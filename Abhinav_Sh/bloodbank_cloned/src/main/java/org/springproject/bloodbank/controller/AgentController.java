package org.springproject.bloodbank.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

// arData is the name of the DTO ArrayList Object , which is abbreviated as arData
// Which i have processed in the Service layer
@Controller
@RequestMapping(path = "/agent")
public class AgentController {
    private static final Logger logger = LogManager.getLogger("vehicles");
    @Autowired
    RequestService requestService;
    @Autowired
    DashBoardService dashBoardService;

    @GetMapping(path = "/profile")
    public String profile(HttpSession httpSession) {
        return "agent_profile";
    }

    @GetMapping(path = "/dashboard")
    public String dashboard(HttpSession httpSession) {
        return "agent_dashboard";
    }

    @GetMapping(path = "/deleteAccount")
    public String deleteUser(HttpSession httpSession) throws ServletException, IOException {
        logger.info("An account deleting request has been created by the agent ..");
        logger.info("The agent's acount is being deleted fromt the database");
        dashBoardService.delUser(httpSession);
        return "redirect:/bloodbank/auth/home";
    }

    @PostMapping(path = "/editRequestSubmitTo")
    public String editRequestSubmitTo(@RequestParam(value = "index", required = false) String indice, @ModelAttribute("requestData") RequestDTO requestDTO, HttpSession httpSession) {
        try {
            return requestService.processRequestAndRedirectForAgent(indice, requestDTO, httpSession);
        }catch (Exception e){
            throw new NullPointerException("Invalid request");
        }

    }

    @PostMapping(path = "/editRequest")
    public String editRequest(HttpSession httpSession, @RequestParam(value = "index", required = false) String index, Model model) {
        try {
            ArrayList<RequestDTO> dtos = (ArrayList<RequestDTO>) httpSession.getAttribute("arData");
            // if the arrayList retrieved from the choices
            logger.info("called the editRequest() controller from the agent");
            logger.info("Now cetting the all the details of the RequestDTO to the corresponding form values");
            int arrayListIndex = Integer.valueOf(index);
            RequestDTO requestDTO = dtos.get(arrayListIndex);
            String submitTheEditedTo = "agent/editRequestSubmitTo";
            model.addAttribute("request", requestDTO);
            model.addAttribute("index", index);// set the index in the session
            model.addAttribute("editRequestSubmitTo", submitTheEditedTo);
            if (requestDTO.getAction().equalsIgnoreCase("donate"))
                return "agent_side_donate";
            else
                return "agent_side_recieve";
        }catch (Exception e){
            throw new NullPointerException("Invalid Edit Request");
        }
    }

    @GetMapping(path = "/request")
    public String request(@RequestParam(name = "type", required = false) String request, Model model, HttpSession session) {
        try {
            logger.info("Type is "+request);
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user.getRole().equalsIgnoreCase("AGENT")) {
                if (request.equalsIgnoreCase("recieve")) {
                    logger.info("Its a recieve request");
                    return "agent_side_recieve";
                } else {
                    logger.info("Its a donate request");
                    return "agent_side_donate";
                }
            }
            return "login";
        }catch (Exception e){
            return "login";
        }
    }

    @GetMapping(path = "/history")
    public String history(Model model, HttpSession session) {
        logger.info("Inside the /history");
        ArrayList<RequestDTO> requestDTOArrayList=requestService.getRequestDTO(session);
        logger.info("Retrieved the arraylist");
        logger.info("The /history recieves arraylist with value"+requestDTOArrayList==null?null:requestDTOArrayList.size());
        model.addAttribute("arData", requestDTOArrayList);
        return "agent_history";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession httpSession, Model model) {
        httpSession.invalidate();
        return "forward:/bloodbank/auth/home";
    }
}
