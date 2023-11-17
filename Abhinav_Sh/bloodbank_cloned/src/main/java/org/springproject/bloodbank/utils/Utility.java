package org.springproject.bloodbank.utils;

import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springproject.bloodbank.dao.DataBaseDAO;
import org.springproject.bloodbank.dao.RequestDAO;
import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.model.Request;
import org.springproject.bloodbank.model.User;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Utility {
    private static final Logger logger = LogManager.getLogger("vehicles");
    public static boolean decideIfUserOrRequest(String string ){
        if(string.toLowerCase().contains("blocked") || string.toLowerCase().contains("you are left with"))
            return  true; // if the user exists
        else
            return false; // if the user don't exist in the user's list and exist in the requests list
    }

    public static String getHashFromString(String password) {
        byte[] salt = new byte[16];
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public static User geUserObjectFromSession(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("loggedInUser");
        return user;
    }

    public static User getUserObjectFromUsername(UserDTO userDTO) {
        return DataBaseDAO.getUsers().stream().
                filter(a ->
                        (a.getRole().equalsIgnoreCase(userDTO.getRole()))
                                && (a.getEmail().equalsIgnoreCase(userDTO.getEmail()))
                ).findFirst().get();
    }
    public static UserDTO userToDto(HttpSession httpSession) {
        User user = geUserObjectFromSession(httpSession);
        UserDTO usss = new UserDTO();
        usss.setUserDTO(user);
        return usss;
    }
    public static UserDTO userToDto(User user) {
        UserDTO usss = new UserDTO();
        usss.setUserDTO(user);
        return usss;
    }
    public static User dtoToUser(UserDTO userForm) {
        return new User(userForm);
    }
    public static RequestDTO requestToDTO(Request request) {
        RequestDTO d = new RequestDTO();
        d.setRequestToDto(request);
        return d;
    }
    public static int getRequestHash(RequestDTO request) {
        // Convert the RequestDTO object to binary data
        try {
            byte[] binaryData = request.toString().getBytes();

            // Create a new instance of MessageDigest with SHA-512 algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Update the digest using the specified byte
            md.update(binaryData);

            // Complete the hash computation by performing final operations such as padding
            byte[] hash = md.digest();

            // Convert byte array into signum representation
            BigInteger bi = new BigInteger(1, hash);

            // Get the integer value
            return bi.intValue();
        } catch (Exception e) {
            return request.histId;
        }
    }
public static void agentEditUtility(RequestDTO requestDTO,Request request1){
    request1.setNoOfUnits(Integer.valueOf(requestDTO.getNoOfUnits())); // units
    request1.setDateTillOrOn((requestDTO.getDateTillOrOn())); //date
    request1.setEmail(requestDTO.getEmail()); //email
    request1.setAge(Integer.valueOf(requestDTO.getAge())); //age
    request1.setBloodGroup(requestDTO.getBloodGroup()); //bloodgroup
    request1.setAddress(requestDTO.getAddress()); //address
    request1.setPlacedFor(requestDTO.getPlacedFor()); //name
}
public static boolean combineFlagResultOfFirstTimeLoginOfAll(ArrayList<RequestDTO> arl){
        arl.stream().forEach(requestDTO -> System.out.println(requestDTO.isFirstTimePassResetFlag()));
        return arl.stream().reduce(true,(a,b)->a&&b.isFirstTimePassResetFlag(),(a,b)->a&b);
}
    public static Request dtoToHistory(RequestDTO requestDTO) {
        logger.info("Getting the correcting Request Object from the dto");
        logger.info(" The query parameters are that the userObject attached in the RequestDTO should match with the User Object stored in the request DAO ");
        return RequestDAO.getRequests().stream().
                filter(a -> a.getPlacedBy().getRole().equalsIgnoreCase(requestDTO.getPlacedBy().getRole())).
                filter(a -> a.getHistId() == requestDTO.histId).
                findFirst().orElseGet(() -> new Request(requestDTO));
    }
}
