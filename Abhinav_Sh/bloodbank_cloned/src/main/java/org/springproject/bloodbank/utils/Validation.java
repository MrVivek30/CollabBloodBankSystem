package org.springproject.bloodbank.utils;

import org.springframework.ui.Model;
import org.springproject.bloodbank.dao.DataBaseDAO;
import org.springproject.bloodbank.dao.RequestDAO;
import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.exceptions.FormException;
import org.springproject.bloodbank.exceptions.RequestException;
import org.springproject.bloodbank.exceptions.UserEntryException;
import org.springproject.bloodbank.model.Request;
import org.springproject.bloodbank.service.RequestServiceImpl;

import java.time.LocalDate;
import java.time.Period;


public class Validation {
    private static String globalAction = "";
    public static String signUpValidation(UserDTO user) throws RequestException,FormException,UserEntryException {
        String str = "";

        // Check if user credentials are unique
        // Check if username already exists
        // Check if email already exists
        if (DataBaseDAO.isEmailExist(user.getEmail()))
            throw  new UserEntryException("The Email has been registered by another user");
        // if the user is unique then some followup validation
        // Helper method for additional sign up validations
        str += (helperForSignUpValidation(str.toString(), user));

        return str.toString();
    }
    public static String helperForSignUpValidation(String str, UserDTO user) throws FormException, UserEntryException {
        checkName(user.getName());
        try { // email check
            Validation.checkEmail(user.getEmail());
        } catch (Exception e) {
            throw new FormException("The email entered is incorrect");
        }
        try { // dob check
            Validation.validateDOB(user.getDateOfBirth());
        } catch (Exception e) {
            throw new FormException("The Age must not exceed 100 year and not less than 16 years");
        }
        return str;
    }
    public static String signUpValidationResolver(UserDTO user) throws Exception {
        return signUpValidation(user);
    }

    public static String requestValidation(RequestDTO requestDTO) throws Exception {
        String[] fields = {"Name", "Age", "Address", "Email", "Units"};
        // getting the list of agents
        boolean isEmailFromRegisteredUsers = DataBaseDAO.getUsers().stream().anyMatch(user->user.getEmail().equalsIgnoreCase(requestDTO.getEmail()));
        if(isEmailFromRegisteredUsers && !requestDTO.getRoleOfPlacedBy().equalsIgnoreCase("user"))
            throw new RequestException("Pre-Registered Agent's or User's are not allowed to place their request from the Agent Portal");
        globalAction = requestDTO.getAction();
        ValidationFunction<String, String>[] validations;
        validations = new ValidationFunction[]{
                name -> checkName((String) name),
                age -> checkAge((String) age),
                addr -> checkAddress((String) addr),
                email -> checkEmail((String) email),
                units -> checkUnits((String) units),

        };
        if ("AGENT".equalsIgnoreCase(requestDTO.getPlacedBy().getRole()))
            for (int i = 0; i < fields.length; i++)
                    validations[i].apply(requestDTO.getField(fields[i]));

         else  // if a normal user then just check the number of units is a number
                Validation.checkUnits(requestDTO.getNoOfUnits());

        if(!(requestDTO.getDateTillOrOn().compareTo(LocalDate.now())>=0))
            throw new RequestException("Only valid dates can be accepted , after the current date ");

        RequestServiceImpl requestService = new RequestServiceImpl();
        System.err.println("This is a "+requestDTO.getAction()+" request");
        if (!requestService.getPendingRequestsOfTheUser(requestDTO.getAction(), requestDTO.getEmail(), requestDTO.getPlacedBy().getRole()).isEmpty()) {
            System.err.println("This is a "+requestDTO.getAction()+" request");
            throw new RequestException(" Another " + requestDTO.getAction() + " request of the user is still pending ");
        }
        // Decision Making
        globalAction = "";
        if (!"AGENT".equalsIgnoreCase(requestDTO.getPlacedBy().getRole())) { // if its not a user
            requestDTO.setAge(String.valueOf(calcAge(requestDTO.getDateTillOrOn())));
            requestDTO.setBloodGroup(requestDTO.getPlacedBy().getBloodGroup());
            requestDTO.setEmail(requestDTO.getPlacedBy().getEmail());
        }

        Request hist = new Request(requestDTO);
        RequestDAO.add(hist);

        return "redirect:/bloodbank/" + requestDTO.getPlacedBy().getRole().toLowerCase() + "/dashboard";
    }

    public static int calcAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public static String checkName(String name) throws UserEntryException {
        if (name == null || !name.matches("^[a-zA-Z]*$")) {
            throw new UserEntryException("Name must not contain special characters or numbers");
        }
        if (name.length() < 3 || name.length() > 50) {
            throw new UserEntryException("Name must be between 3 and 50 characters");
        }
        return name;
    }

    public static String checkAddress(String addr) throws RequestException {
        if (addr == null || addr.trim().isEmpty()) {
            throw new RequestException("Address cannot be empty");
        }
        if (addr.length() < 3 || addr.length() > 50) {
            throw new RequestException("Address must be between 3 and 50 characters");
        }
        return addr;
    }

    public static String checkUnits(String units) throws FormException,NullPointerException{
        if (units == null) {
            throw new NullPointerException("Units cannot be null");
        }
        if (!units.matches("\\d+")) {
            throw new FormException("Units must be a positive number");
        }
        int unitInt = Integer.parseInt(units);
        if (!(unitInt > 0)) {
            throw new FormException("no of units must be greater than 0 ");
        }
        return units;
    }

    public static String checkAge(String age) throws NullPointerException, NumberFormatException, RequestException {
        if (age == null) {
            throw new NullPointerException("Age must not be null");
        }
        if (!age.matches("-?\\d+")) {
            throw new NumberFormatException("Age must be a number");
        }
        int ageInt = Integer.parseInt(age);
        if (ageInt <= 0) {
            throw new RequestException("Age must be a positive number and greater than 0");
        }
        if (ageInt > 100) {
            throw new RequestException("Age must be less than 100");
        }
        if (globalAction.equalsIgnoreCase("donate") && ageInt < 16)
            throw new RequestException("Age must be more than 16 to donate");
        return age;
    }
    public static String validateName(String name) throws FormException,NullPointerException {
        if (name == null) {
            throw new NullPointerException("Name must not be null");
        }

        // Regular expression for name validation
        if (!name.matches("^[A-Za-z]{1}[A-Za-z\\s]{1,48}[A-Za-z]{1}$")) {
            String instr = "Name should not start with a number";
            throw new FormException(instr);
        }

        // Check for name length
        if (name.length() < 3 || name.length() > 50) {
            throw new FormException("Name must be between 3 and 50 characters long");
        }

        return name;
    }

    // This method validates the date of birth of the user
    public static LocalDate validateDOB(LocalDate d) throws FormException {
        long years = Validation.calcAge(d);

        // Check for age limit
        if (years < 16 || years > 100) {
            throw new FormException("Invalid DOB, the age must be between 16 to 100 years");
        }

        return d;
    }
    public static String checkEmail(String email) throws Exception {
        if (email == null || email.trim().isEmpty()) {
            throw new FormException("Email cannot be empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new FormException("Enter a proper email");
        }
        return email;
    }

    @FunctionalInterface
    public interface ValidationFunction<T, R> {
        R apply(T t) throws Exception;
    }
}
