package org.springproject.bloodbank.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springproject.bloodbank.dao.DataBaseDAO;
import org.springproject.bloodbank.dao.RequestDAO;
import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.model.Request;
import org.springproject.bloodbank.model.User;
import org.springproject.bloodbank.utils.BloodGroupRule;
import org.springproject.bloodbank.utils.Utility;
import org.springproject.bloodbank.utils.Validation;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RequestServiceImpl implements RequestService {
    private static final Logger logger = LogManager.getLogger("vehicles");

    public HashMap<String, Integer> getBloodCount() {
        return RequestDAO.getBloodKeeper();
    }

    public ArrayList<UserDTO> getAllUsers() {
        return DataBaseDAO.getUsers().stream().map(user -> Utility.userToDto(user)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RequestDTO> getRequestsUsingUsersEmailAndPass(String username, String password) {
        try {
            return RequestDAO.getRequests().stream().
                    filter(request -> request.getRoleOfPlacedBy().equalsIgnoreCase("agent")).
                    filter(request -> request.getEmail().equalsIgnoreCase(username)).
                    filter(request -> request.getPassword().equalsIgnoreCase(password)).
                    peek(request -> {logger.info("Inside the getRequestsUsingUsersEmailAndPass");logger.info(request.isFirstTimePassResetFlag());}).
                    map(request -> Utility.requestToDTO(request)).
                    collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            return new ArrayList<RequestDTO>();
        }
    }
    public void passwordReset(String email,String newPassword){
        try{
            RequestDAO.getRequests().
                    stream().
                    filter(request->request.getEmail().equalsIgnoreCase(email)).
                    forEach(request -> request.setPassword(newPassword));
            RequestDAO.getRequests().
                    stream().
                    filter(request->request.getEmail().equalsIgnoreCase(email)).
                    forEach(request -> {logger.info("Before "+request.isFirstTimePassResetFlag());request.changeFirstTimePassResetFlag();logger.info("After :"+request.isFirstTimePassResetFlag());});
            System.out.printf("Displaying the modified result");
            RequestDAO.getRequests().
                    stream().
                    filter(request->request.getEmail().equalsIgnoreCase(email)).
                    forEach(request -> logger.info("The info requets is pass is "+request.getPassword()+" the flag is "+request.isFirstTimePassResetFlag()));
            RequestDAO.saveArrayListToFile();
        }catch (Exception e){
            // do nothing
            logger.info("Could not set password");
            throw  new NullPointerException("No users could be found with given user name and pass");
        }
    }
    public ArrayList<RequestDTO> filter(ArrayList<RequestDTO> requestDTOS, String action, String status, String bg) {
        logger.info("Inside the filter method");
        if (action == null && status == null && bg == null) {
            logger.info("Fresh Requests are being displayed");
            logger.info("Exiting the filter method");
            return requestDTOS;
        } else if (action.equalsIgnoreCase("all") && status.equalsIgnoreCase("all")) {
            if (bg.isEmpty()) {
                logger.info("All of the parameters are set to default so no filtering is done");
                logger.info("Exiting the filter method");
                return requestDTOS;
            } else {
                logger.info("Found that the bloodGroup parameter is there to filter by: " + bg);
                logger.info("Exiting the filter method");
                return requestDTOS.stream().filter(request -> request.getBloodGroup().equalsIgnoreCase(bg)).collect(Collectors.toCollection(ArrayList::new));
//                return donorCandidates(req,bg);

            }
        } else if (action.equalsIgnoreCase("all") && !status.equalsIgnoreCase("all")) {

            ArrayList<RequestDTO> tmp = requestDTOS.stream().filter(a -> a.getStatus().equalsIgnoreCase(status)).collect(Collectors.toCollection(ArrayList::new));
            if (bg.isEmpty()) {
                logger.info("All Donate and Recieve requests are requested but only " + status + " are requested no bloodGroup Filterin");
                logger.info("Exiting the filter method");
                return tmp;
            } else {
                logger.info("All Donate and Recieve requests are requested but only " + status + " are requested with bloodGroup Filtering by :" + bg);
                logger.info("Exiting the filter method");
                return tmp.stream().filter(request -> request.getBloodGroup().equalsIgnoreCase(bg)).collect(Collectors.toCollection(ArrayList::new));
//                return donorCandidates(tmp,bg);
            }
        } else if (status.equalsIgnoreCase("all") && !action.equalsIgnoreCase("all")) {
            ArrayList<RequestDTO> tmp = requestDTOS.stream().filter(a -> a.getAction().equalsIgnoreCase(action)).collect(Collectors.toCollection(ArrayList::new));
            if (bg.isEmpty()) {
                logger.info("All Status request are requested but only " + action + " requests are filtered");
                logger.info("Exiting the filter method");
                return tmp;
            } else {
//                return donorCandidates(tmp,bg);
                logger.info("All Status request are requested but only " + action + " requests are filtered with bloodgroup : " + bg);
                logger.info("Exiting the filter method");
                return tmp.stream().filter(request -> request.getBloodGroup().equalsIgnoreCase(bg)).collect(Collectors.toCollection(ArrayList::new));
            }
        } else {
            ArrayList<RequestDTO> tmp = requestDTOS.stream().filter(a -> a.getStatus().equalsIgnoreCase(status)).filter(a -> a.getAction().equalsIgnoreCase(action)).collect(Collectors.toCollection(ArrayList::new));
            if (bg.isEmpty()) {
                logger.info("Filtering by " + status + " and reqiests are " + action + " all bloodgroup ");
                logger.info("Exiting the filter method");
                return tmp;
            } else {
                logger.info("Filtering by " + status + " and reqiests are " + action + " by bloodgroup  :" + bg);
                logger.info("Exiting the filter method");
                return tmp.stream().filter(request -> request.getBloodGroup().equalsIgnoreCase(bg)).collect(Collectors.toCollection(ArrayList::new));
            }
        }
    }

    public ArrayList<RequestDTO> donorCandidates(RequestDTO requestDTO) { // the reciever
        logger.info("Inside the donorCandidates(RequestDTO requestDTO) function inside the RequestServiceImpl");
        logger.info("This function filters and find the candidate donors which satisfy some conditions which are");
        logger.info("   1: ) Must have noOfUnits>=required by the Reciever ");
        logger.info("   2: ) Must have a bloodGroup that can be recieved by the reciever ");
        logger.info("   3: ) Must have a DateUpto donation of the donor to bemore or equal to the date of the reciever");
        logger.info("   4: ) Must be granted by the admin");

        Stream<Request> tmp = RequestDAO.getRequests().stream();
        //
        return tmp.
                filter(a -> BloodGroupRule.canRecieveFrom.get(requestDTO.getBloodGroup()).contains(a.getBloodGroup())).
                filter(a -> a.getAction().equalsIgnoreCase("donate")).
                filter(a -> a.getStatus().equalsIgnoreCase("granted")).
                filter(a -> !a.getDateTillOrOn().isBefore(requestDTO.getDateTillOrOn())).
                filter(a -> a.getNoOfUnits() >= Integer.valueOf(requestDTO.getNoOfUnits())).
                map(a -> Utility.requestToDTO(a)).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public void doAccept(int index, HttpSession httpSession, String acceptOrReject, String remark) {
        logger.info(" You have clicked on the accept button ");
        logger.info("Getting the ArrayList of the RequestDTO stored in the Requests jsp page");
        ArrayList<RequestDTO> requestDTO = (ArrayList<RequestDTO>) httpSession.getAttribute("history");
        logger.info(" Got the RequestDTO ArrayList");
        logger.info("The Index number of the ArrayList is " + index + " and  Here is the summary of all the requests ");
        try {
            for (RequestDTO i : requestDTO)
                logger.info(i.toString());
            logger.info("We have to deal with only " + requestDTO.get(index).toString());
        } catch (Exception e) {
            logger.info("Unfortunately the logger object didnot have anything inside it ");
            return;
        }
        logger.info("Now checking what type of request is it Accept or Reject");
        if (acceptOrReject.equalsIgnoreCase("accept")) { // if accept button
            logger.info("It is an accept request");
            if (requestDTO.get(index).getAction().equalsIgnoreCase("recieve")) {
                logger.info("It is a recieve type of accept request");
                logger.info("Now trying to get the candidate donors for the recieve type of the request");
                ArrayList<RequestDTO> candidates = this.donorCandidates(requestDTO.get(index));
                logger.info("Found the candidate donors");
                if (requestDTO.get(index).getStatus().equalsIgnoreCase("granted")) {
                    logger.info("Unfortunately the required request has alread been granted so cannot be granted again");
                    return;
                }// if reciever already granted blood then the reciever is not allowed to change
                // status
                if (candidates == null || candidates.isEmpty()) {
                    logger.info("Unfortunately not candidate donors could be found for this request");
                    // no candidate donors
                    if (requestDTO.get(index).getStatus().equalsIgnoreCase("refused")) {
                        logger.info(" Even if no candidate donors are there and the status is rejected then we do nothing");

                        // if reciever has no candidate donor and its status is refused then it
                        // is allowed to change the status from refused to pending
                    } else {// if a reciever has no candidate donor and its status is niether refused
                        // nor granted then it shall be turned to rejected status
//                        requestDTO.get(index).changeStatus(); //granted
//                        requestDTO.get(index).changeStatus();//refused
                        logger.info(" If the status is not rejected then the status of the Reciever is rejected since no candidate donors could be found");

                        doReject(index, requestDTO, "No candidate Donors satisfying the requirement");
                    }
                } else {
                    logger.info("The reciever has cadidate donors");
                    // a reciever having candidate donors
                    // there are candidates
                    // Update the bloodbank registry
                    // is the reciever has already recieved the blood then its status must be granted
                    // then the status will not be changed
                    if (requestDTO.get(index).getStatus().equalsIgnoreCase("granted")) {
                        logger.info("The reciever is already granted to nothing is done");
                        return;
                    }
                    // else if the status is not granted then  we shall move forward by removing the blood
                    // from the blood bank
                    logger.info("We are removing the equivalent blood requirement from the bloodbank counter");
                    RequestDAO.removeBlood(candidates.get(0).getBloodGroup(), Integer.valueOf(requestDTO.get(index).getNoOfUnits()));
                    logger.info("We are getting the actual candidate donor from the acquire CandidateDTO arraylist and select the first Candidate from the arraylist");
                    // Update the candidate
                    // candidate donot no. of units
                    Request candidate = Utility.dtoToHistory(candidates.get(0));
                    logger.info("We update the blood Available by the candidate donor by negating the same amount of blood from its request");
                    candidate.setNoOfUnits(
                            candidate.getNoOfUnits()
                                    -
                                    Integer.valueOf(requestDTO.get(index).getNoOfUnits()));
                    // update the RequestDTO
                    // update the dto status from pending to granted
                    requestDTO.get(index).changeStatus(); // if it was pending
                    logger.info("Changing the status of the requestDTO to granted");
                    // also update the actual history
                    logger.info("Also update the actual object from RequestDAO object");
                    Utility.dtoToHistory(requestDTO.get(index)).changeStatus();
                }
            } else {
                logger.info("Its a donate request and also an accept donate request");
                // a donor
                // if a donor is granted then it shall not be modified

                if (requestDTO.get(index).getStatus().equalsIgnoreCase("granted")) {
                    logger.info("The Donate request has already been granted");
                    return;
                }
                logger.info("The blood banks counter is being icremented by the donor's dnoation unit of corrsponding bloodgroup");
                RequestDAO.addBlood(requestDTO.get(index).getBloodGroup(), Integer.valueOf(requestDTO.get(index).getNoOfUnits()));
                requestDTO.get(index).changeStatus();
                logger.info("Changing the status of the donorDTO to granted");
                logger.info("Refleting the same to the actual DONOR object from RequestDAO ");
                Utility.dtoToHistory(requestDTO.get(index)).changeStatus();

            }
        } else {
            logger.info("Its a reject request");
            doReject(index, requestDTO, remark);
        }
    }

    public String updateRequestAndRedirectForUser(String indice, RequestDTO requestDTO, HttpSession httpSession) {
        ArrayList<RequestDTO> dtos = (ArrayList<RequestDTO>) httpSession.getAttribute("arData");
        Integer index = Integer.valueOf(indice);
        httpSession.removeAttribute("index");
        httpSession.removeAttribute("arData");
        RequestDTO dto = dtos.get(index);
        dto.setNoOfUnits(requestDTO.getNoOfUnits());
        dto.setDateTillOrOn((requestDTO.getDateTillOrOn()) == null ? null : (requestDTO.getDateTillOrOn()).toString());
        Request request1 = Utility.dtoToHistory(dto);
        doPending(dto, request1);
        request1.setNoOfUnits(Integer.valueOf(requestDTO.getNoOfUnits()));
        request1.setDateTillOrOn((requestDTO.getDateTillOrOn()));
        return "redirect:/bloodbank/user/history";
    }

    public String processRequestAndRedirectForAgent(String indice, RequestDTO requestDTO, HttpSession httpSession) {
        logger.info("The modification in the form has been recieved and will be persiseted");
        ArrayList<RequestDTO> dtos = (ArrayList<RequestDTO>) httpSession.getAttribute("arData");
        int index = Integer.valueOf(indice);
        logger.info("The remoark will be removed and the modification will be done on corresponding Request Object");
        logger.info("The Request's Sttaus will be changed to Pending");
        httpSession.removeAttribute("index");
        httpSession.removeAttribute("arData");
        RequestDTO dto = dtos.get(index);
        Request request1 = Utility.dtoToHistory(dto); // just for getting the exact request object
        // changing the actual object
        doPending(dto, request1); //i change the pending status refused status of the request to pending
        Utility.agentEditUtility(requestDTO, request1);
        return "redirect:/bloodbank/agent/history";
    }

    @Override
    public String requestValidator(UserDTO userDTO, Model model, RequestDTO requestDTO) {
        logger.info("User session is found and checking if the request is made by User or Agent");
        if (!userDTO.getRole().equalsIgnoreCase("agent")) {
            logger.info("The request peson is an user then set the user's email to the request's email");
            requestDTO.setEmail(userDTO.getEmail());
        }
        logger.info("Append the user's dto object to the request's  dto object");
        logger.info("Check if the validation is cleared by the request or not");
        requestDTO.setPlacedBy(userDTO);
        requestDTO.setRoleOfPlacedBy(requestDTO.getPlacedBy().getRole());
        try {
            return Validation.requestValidation(requestDTO);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.info("It is a "+  requestDTO.getAction()+"request");
            String action = requestDTO.getAction().equalsIgnoreCase("donate") ? "donate" : "recieve";
            String role = requestDTO.getRoleOfPlacedBy().equalsIgnoreCase("agent") ? "agent" : "user";
            return role + "_side_" + action;
        }
    }

    public void doPending(RequestDTO requestDTOS, Request request) { // perform edition  and apply remark
        if (requestDTOS.getStatus().equalsIgnoreCase("pending"))
            return;
        if (requestDTOS.getStatus().equalsIgnoreCase("refused")) {
            requestDTOS.setRemark("");
            requestDTOS.changeStatus();
            request.setRemark("");
            request.changeStatus();
        }
    }

    public void doReject(int index, ArrayList<RequestDTO> requestDTO, String remark) {
        // reject the request
        logger.info("Inside the doReject() function of the RequestServiceImpl");
        if ((requestDTO.get(index).getStatus().equalsIgnoreCase("granted") // granted
                && requestDTO.get(index).getAction().equalsIgnoreCase("donate"))
                ||
                (requestDTO.get(index).getStatus().equalsIgnoreCase("granted")
                        && requestDTO.get(index).getAction().equalsIgnoreCase("recieve"))
        ) {
            logger.info("the request is donate/Recieve and is granted then we do nothing");
        } else if (requestDTO.get(index).getStatus().equalsIgnoreCase("refused")) {
            logger.info("the request is already refused then we do nothing");
            // if refused
        } else { // if pending
            if (remark.length() == 0) {
                logger.info("No remark From admin");
                logger.info("So no edit is allowed");
//                remark="No remark From admin";
            }
            logger.info("The request's status is pending so since we change the status in cycles which is intially is pending");
            logger.info("pending -> granted -> refused -> pending -> .... so on as long as we keep on calling changeStatus() function of request");
            requestDTO.get(index).changeStatus();//granted
            logger.info("Now setting the remark of the requestDTO");
            requestDTO.get(index).setRemark(remark);
            requestDTO.get(index).changeStatus();//refused
            logger.info("We have changed the status to refused and now setting the requestIterationCount and negating by -1");
            logger.info("Since we called the changeStatus() two times so we have to minus it because the change is happen only one time not two");
            logger.info("We do it because the the RequestIterationCount increment by 1 every time we call the ChangeStatus function");
            requestDTO.get(index).setRequestIterationCount(requestDTO.get(index).getRequestIterationCount() - 1);
            logger.info("NOw we are getting the actual  Request object from the RequestDAO to reflect the changes of the DTO to the actual Request object");
            logger.info("Now i query the object Form the RequestDAO using the Utility.dtoToHistory(The RequestDTO object we have)");
            Request request = Utility.dtoToHistory(requestDTO.get(index));
            request.changeStatus();//granted
            request.changeStatus();//refused
            logger.info("Chaning the status of the actual Request object to refused");
            logger.info("Adjusting the request Iteration count of the Actual object the same way i did with the DTO object");
            request.setRequestIterationCount(Utility.dtoToHistory(requestDTO.get(index)).getRequestIterationCount() - 1);
            logger.info("Setting the remark to the actual request object");
            request.setRemark(remark);
        }
    }

    public ArrayList<RequestDTO> getRequestDTO(HttpSession session) {  // Request of Particular user
        try {
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            User user = Utility.getUserObjectFromUsername(userDTO);
            logger.info("User has been retrieved");
            return RequestDAO.
                    getRequests().stream().
                    filter(a -> a.getPlacedBy().getName().equalsIgnoreCase(user.getName())).
                    filter(a -> a.getPlacedBy().getEmail().equalsIgnoreCase(user.getEmail())).
                    filter(a -> a.getPlacedBy().getRole().equalsIgnoreCase(user.getRole())).
                    filter(a -> a.getPlacedBy().getBloodGroup().equalsIgnoreCase(user.getBloodGroup())).
                    filter(a -> a.getPlacedBy().getDateOfBirth().isEqual(user.getDateOfBirth())).
                    map(a -> Utility.requestToDTO(a)).
                    collect(Collectors.toCollection(ArrayList::new));
        }catch (Exception e){
            logger.info("The user getRequestDTO could not be executed because its invalid request "+e.getMessage());
            return new ArrayList<RequestDTO>();
        }
    }

    public ArrayList<RequestDTO> getRequestDTOAll() {
        return RequestDAO.
                getRequests().stream()
                .map(a -> Utility.requestToDTO(a))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RequestDTO> getRequestDTOPendingDonate() {
        return RequestDAO.
                getRequests().
                stream().
                filter(a -> a.getStatus().equalsIgnoreCase("pending")).
                filter(a -> a.getAction().equalsIgnoreCase("donate")).
                map(a -> Utility.requestToDTO(a)).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RequestDTO> getRequestDTOPendingRecieve() {
        return RequestDAO.
                getRequests().stream().
                filter(a -> a.getStatus().equalsIgnoreCase("pending")).
                filter(a -> a.getAction().equalsIgnoreCase("recieve")).
                map(a -> Utility.requestToDTO(a)).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RequestDTO> getPendingRequestsOfTheUser(String type, String email, String role) {
        if (type.equalsIgnoreCase("donate"))
            return getRequestDTOPendingDonate().stream().filter(a -> a.getPlacedBy().getRole().equalsIgnoreCase(role)).filter(a -> a.getEmail().equalsIgnoreCase(email)).collect(Collectors.toCollection(ArrayList::new));
        else
            return getRequestDTOPendingRecieve().stream().filter(a -> a.getPlacedBy().getRole().equalsIgnoreCase(role)).filter(a -> a.getEmail().equalsIgnoreCase(email)).collect(Collectors.toCollection(ArrayList::new));
    }
}