package org.springproject.bloodbank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdk.jshell.execution.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.model.User;
import org.springproject.bloodbank.service.AuthorizationService;
import org.springproject.bloodbank.service.RequestService;
import org.springproject.bloodbank.utils.Utility;

import java.text.ParseException;
import java.util.ArrayList;

// arData is the name of the DTO ArrayList Object , which is abbreviated as arData
// Which i have processed in the Service layer
@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    private static final Logger logger = LogManager.getLogger("vehicles");
    @Autowired
    RequestService requestService;
    @Autowired
    AuthorizationService authorizationService;

    @GetMapping("/profile")
    public String profile(HttpSession httpSession) {
        logger.info("Inside the profile from the dashboard");
        return "admin_profile";
    }
    @GetMapping("/usersList")
    public String usersList(Model model,HttpSession httpSession){
        try{
            model.addAttribute("arData",requestService.getAllUsers());
        }
        catch (Exception e){
            model.addAttribute("arData",new  ArrayList<UserDTO>());
        }
        return "admin_users_list";
    }
    @PostMapping("/unBlock")
    public String userUnBlock(@RequestParam(value = "index",required = false) String  index,HttpSession httpSession){
        ArrayList<UserDTO> users = (ArrayList<UserDTO>)httpSession.getAttribute("arData");
        if(index==null)
            throw new NullPointerException();
        int indice=Integer.valueOf(index);
        UserDTO tmp=users.get(indice);
        tmp.locked=false;
        tmp.chances=4;
        authorizationService.unBlockUser(Utility.dtoToUser(tmp).getEmail());
        return "admin_users_list";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession httpSession) {
        logger.info("Inside the dashboard of the admin");
        return "admin_dashboard";
    }
    @GetMapping(path = "/bloodReport")
    public String bloodReport(HttpSession httpSession, Model model, HttpServletRequest httpServletRequest) {
        logger.info("Inside the bloodReport controller");
        model.addAttribute("blood", requestService.getBloodCount());
        return "admin_blood_report";
    }

    @GetMapping(path = "/overallBloodReport")
    public String overallBloodReport(HttpSession httpSession, Model model) {
        logger.info("Inside the overAllBloodReport controller");
        logger.info("Getting the overall blood report which is the 3rd task , padsing all the arraylistdto to the jsp");
        ArrayList<RequestDTO> all = requestService.getRequestDTOAll();
        logger.info("Passing the bloodCount HashMap to the Jsp as welll");
        model.addAttribute("blood", requestService.getBloodCount());
        model.addAttribute("arData", all);
        return "admin_blood_report_3rd_task";
    }
    @GetMapping(path = "/allrequests")
//    public String allRequests(@RequestParam(name = "type", required = false) String request, Model model, HttpSession session) {
    public String allRequests(
            @RequestParam(name = "bg", required = false) String bg,
            @RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "status", required = false) String status,
            Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            logger.info("no user is logged in so redirecting to the home page");
            return "redirect:/bloodbank/auth/home";
        }
        ArrayList<RequestDTO> all = requestService.getRequestDTOAll();
        logger.info("Retrieved all of the Requests in the DTO format");
        model.addAttribute("blood", requestService.getBloodCount());
        logger.info("The Blood Count hashmap has been attached to the Model object");
        model.addAttribute("arData", requestService.filter(all, action, status, bg));
        logger.info("All of the Dtos have been filtered using the filter Method with parameters -> AllRequests,Donate/Recieve,Status,BloodGroup");
        return "admin_request";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession httpSession) {
        logger.info("Logging out by session.invalidate() ...");
        httpSession.invalidate();
        return "redirect:/bloodbank/auth/home";
    }

    @PostMapping(path = "/candidateProcessor")
    public String candidateDonors(@RequestParam(value = "remark", required = false) String remark, @RequestParam(value = "what", required = false) String what, @RequestParam(value = "index", required = false) String index1, HttpSession httpSession) {
        logger.info("Inside the candidateProcessor controller");
        if (what == null) {// what === "accept" or "reject"
            logger.info(" no accept/reject request is provide, so redirecting to the home");
            return "redirect:/bloodbank/auth/home";
        }
        logger.info("Retrieving the index of the arraylist of RequestDTO object which is to be rejected or accepted");
        int index = Integer.valueOf(index1);
        requestService.doAccept(index, httpSession, what, remark);
        return "redirect:/bloodbank/admin/allrequests";
    }
}