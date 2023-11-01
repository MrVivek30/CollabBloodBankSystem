package com.trackobit.controller;

import com.trackobit.dto.BloodGroupReportDTO;
import com.trackobit.exception.AdminException;
import com.trackobit.dto.RequestDTO;
import com.trackobit.dto.UserDTO;
import com.trackobit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "admin/adminLogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO user, Model model, HttpSession session) {
        try {
            UserDTO admin = adminService.login(user.getEmail(), user.getPassword());
            session.setAttribute("currentUser", admin);
            model.addAttribute("message", "Welcome to dashboard " + admin.getFirstName());
            return "messagesRedirect/adminSuccess";
        } catch (AdminException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/adminLogin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        model.addAttribute("msg", "Logout successful");
        session.invalidate(); // Invalidates the session
        return "messagesRedirect/SuccessPage";
    }

    @GetMapping("/dashboard")
    public String showUserDashboard(Model model, HttpSession session, HttpSession session2) {
        UserDTO currentUser = (UserDTO) session.getAttribute("currentUser");
        session2.setAttribute("user", currentUser);
        model.addAttribute("user", currentUser);
        return "admin/adminDashboard";
    }


    @GetMapping("/getReceiverList")
    public String getAllReceiverListHandler(Model model) throws AdminException {
        List<RequestDTO> requestDTOListList = adminService.getAllReceiverList();
        System.out.println(requestDTOListList + " from adminController");
        model.addAttribute("requestedReceiverList", requestDTOListList);
        return "admin/adminRequestReceiverList";
    }

    @GetMapping("/getDonationList")
    public String getAllDonationListHandler(Model model) throws AdminException {
        List<RequestDTO> requestDTOListList = adminService.getAllDonationList();
        System.out.println(requestDTOListList + " from adminController");
        model.addAttribute("requestedDonationList", requestDTOListList);
        return "admin/adminRequestDonationList";
    }

    @GetMapping("/getBloodCuntByGroup")
    public String getBloodCountByGroupHandler(Model model) throws AdminException {
        Map<String, Integer> reqBloodCount = adminService.getBloodCountByGroup();
        System.out.println(reqBloodCount + " from adminController");
        model.addAttribute("reqBloodCount", reqBloodCount);
        return "admin/adminBloodCountReport";
    }

    @GetMapping("/getAllUserList")
    public String getAllUSerListHandler(Model model) throws AdminException {
        List<UserDTO> userDTOList = adminService.getAllUserList();
        model.addAttribute("userList", userDTOList);
        return "admin/adminAllUserList";
    }


    @GetMapping("/donateAccept/{uniqueId}")
    public String donateApproval(@PathVariable(value = "uniqueId") int uniqueId) {
        adminService.accepted(uniqueId);
        System.out.println(uniqueId + " from approval adminController");
        return "redirect:/admin/getDonationList";
    }

    @GetMapping("/donateReject/{uniqueId}")
    public String donateDecline(@PathVariable(value = "uniqueId") int uniqueId) {
        adminService.rejected(uniqueId);
        System.out.println(uniqueId + " from approval adminController");
        return "redirect:/admin/getDonationList";
    }

    @GetMapping("/receiveAccept/{uniqueId}")
    public String receiveApproval(@PathVariable(value = "uniqueId") int uniqueId) {
        adminService.accepted(uniqueId);
        System.out.println(uniqueId + " from approval adminController");
        return "redirect:/admin/getReceiverList";
    }

    @GetMapping("/receiveReject/{uniqueId}")
    public String receiveDecline(@PathVariable(value = "uniqueId") int uniqueId) {
        adminService.rejected(uniqueId);
        System.out.println(uniqueId + " from approval adminController");
        return "redirect:/admin/getReceiverList";

    }

    @GetMapping("/getAllBloodHistoryHandler")
    public String getAllBloodHistoryHandler(Model model) {
        List<RequestDTO> allBloodHistory = adminService.getAllBloodHistory();
        model.addAttribute("allBloodHistory", allBloodHistory);
        return "admin/adminAllDonateReceiveHistory";
    }

    @PostMapping("/bloodHistory")
    public String getFilteredBloodHistory(@RequestParam(name = "action", required = false) String action, Model model) {
        List<RequestDTO> requestDTOList = adminService.getBloodRequestHistory(action);
        model.addAttribute("allBloodHistory", requestDTOList);
        return "admin/adminAllDonateReceiveHistory";
    }

    @PostMapping("/donateRemark")
    public String donateRemark(@RequestParam String donateRemark,@RequestParam int uniqueId, Model model){
        adminService.giveRemark(donateRemark,uniqueId);
        return "redirect:/admin/getDonationList";
    } 
    @PostMapping("/receiveRemark")
    public String receiveRemark(@RequestParam String receiveRemark,@RequestParam int uniqueId, Model model){
        adminService.giveRemark(receiveRemark,uniqueId);
        return "redirect:/admin/getReceiverList";
    }

    @GetMapping("/bloodUnitsAndDonorsCountReport")
    public String bloodUnitsAndDonorsCountReport(Model model){
        List<BloodGroupReportDTO> bloodGroupReportDTOList = adminService.bloodUnitsAndDonorsCountReport();
        model.addAttribute("bloodGroupDataDTOList", bloodGroupReportDTOList);
        System.out.println(bloodGroupReportDTOList +" BloodUnitUerCount adminController");
        return "admin/bloodUnitsAndDonorsCountReport";

    }

    @GetMapping("/getAllDonorsByBloodGroup")
    public String getAllDonorsByBloodGroup(@RequestParam("bloodGroup") String bloodGroup, Model model) {
        System.out.println(bloodGroup);
        List<RequestDTO> getAllDonorsByBloodGroup = adminService.getAllDonorsByBloodGroup(bloodGroup);
        System.out.println(getAllDonorsByBloodGroup+"=-==================");
        model.addAttribute("getAllDonorsByBloodGroup", getAllDonorsByBloodGroup);
        return "admin/getAllDonorsByBloodGroup";
    }

    @GetMapping("/bloodBankReportCard")
    public String getBloodBankReport(Model model){
        List<BloodGroupReportDTO> bloodBankReportCard = adminService.getBloodBankReportCard();
        System.out.println(bloodBankReportCard+"**************************");
        model.addAttribute("bloodBankReportCard",bloodBankReportCard);
        return "admin/bloodBankReportCard";
    }
}
