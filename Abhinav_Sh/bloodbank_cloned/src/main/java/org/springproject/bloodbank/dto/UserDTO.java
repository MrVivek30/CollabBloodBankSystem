package org.springproject.bloodbank.dto;

import org.springproject.bloodbank.model.User;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDTO implements Serializable {
    public boolean locked = false;
    public int chances = 4;
    public String securityCode = "";
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String role;
    private String id;
    private String bloodGroup = "NA";
    private String password = "";

    public boolean isLocked() {
        return locked;
    }

    public int getChances() {
        return chances;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public void setUserDTO(User user) {
        this.name = user.getName();
        this.dateOfBirth = user.getDateOfBirth();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.id = String.valueOf(user.getId());
        this.bloodGroup = user.getBloodGroup();
        this.locked = user.locked;
        this.chances = user.chances;
        this.securityCode = user.securityCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


    public void setDateOfBirth(String dateOfBirth) {
        try {
            this.dateOfBirth = LocalDate.parse(dateOfBirth);
        } catch (Exception e) {
            this.dateOfBirth = null;
        }
    }

    public void setDATE_OF_BIRTH(String date) {
        this.dateOfBirth = LocalDate.parse(date);
    }

    public String getDOB() {
        if (dateOfBirth == null)
            return "";
        return dateOfBirth.toString();
    }

    public void setDOB(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
