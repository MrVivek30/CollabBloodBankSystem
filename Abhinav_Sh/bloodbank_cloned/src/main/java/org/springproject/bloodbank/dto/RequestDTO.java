package org.springproject.bloodbank.dto;

import org.springproject.bloodbank.model.Request;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class RequestDTO implements Serializable {
    public String[] statuses = {"PENDING", "GRANTED", "REFUSED"};
    public int histId = 0;
    private int defaultStatus = 0;
    private UserDTO placedBy;
    private String roleOfPlacedBy;
    private String placedFor = null;
    private LocalDate dateTillOrOn;
    private String bloodGroup;
    private String noOfUnits;
    private String action;// donate or recieve
    private String status = statuses[defaultStatus];
    private String email = "";
    private String age = "";
    private String address = "";
    private String remark = "";
    private int requestIterationCount = 0;
    private boolean firstTimePassResetFlag = true;
    private String password = "text";

    public int getHistId() {
        return histId;
    }

    public boolean isFirstTimePassResetFlag() {
        return firstTimePassResetFlag;
    }

    public void changeFirstTimePassResetFlag() {
        System.err.println("Falg value of dto changed");
        if (firstTimePassResetFlag)
            firstTimePassResetFlag = false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        changeFirstTimePassResetFlag();
        this.password = password;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "statuses=" + Arrays.toString(statuses) +
                ", LocalCateTIme='" + LocalDateTime.now() + '\'' +
                ", LocalDate" + LocalDate.now() +
                ", histId=" + histId +
                ", defaultStatus=" + defaultStatus +
                ", placedBy=" + placedBy +
                ", roleOfPlacedBy='" + roleOfPlacedBy + '\'' +
                ", placedFor='" + placedFor + '\'' +
                ", dateTillOrOn=" + dateTillOrOn +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", noOfUnits='" + noOfUnits + '\'' +
                ", action='" + action + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", requestIterationCount=" + requestIterationCount +
                '}';
    }

    public void setRequestToDto(Request d) {
        this.age = String.valueOf(d.getAge());
        this.placedBy = new UserDTO();
        this.placedBy.setUserDTO(d.getPlacedBy());
        this.roleOfPlacedBy = d.getRoleOfPlacedBy();
        this.placedFor = d.getPlacedFor();
        this.address = d.getAddress();
        this.status = d.getStatus();
        this.action = d.getAction();
        this.dateTillOrOn = d.getDateTillOrOn();
        this.roleOfPlacedBy = d.getRoleOfPlacedBy();
        this.bloodGroup = d.getBloodGroup();
        this.noOfUnits = String.valueOf(d.getNoOfUnits());
        this.email = d.getEmail();
        this.histId = d.getHistId();
        this.remark = d.getRemark();
        this.password = d.getPassword();
        this.firstTimePassResetFlag=d.isFirstTimePassResetFlag();
        this.setRequestIterationCount(d.getRequestIterationCount());
    }

    public String getField(String fieldName) {
        if (fieldName.equalsIgnoreCase("Name"))
            return this.getPlacedFor();
        if (fieldName.equalsIgnoreCase("Age"))
            return this.getAge();
        if (fieldName.equalsIgnoreCase("Address"))
            return this.getAddress();
        if (fieldName.equalsIgnoreCase("Email"))
            return this.getEmail();
        if (fieldName.equalsIgnoreCase("Units"))
            return this.getNoOfUnits();
        else
            return "unknown";
    }

    public int getRequestIterationCount() {
        return requestIterationCount;
    }

    public void setRequestIterationCount(int requestIterationCount) {
        this.requestIterationCount = requestIterationCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public UserDTO getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(UserDTO placedBy) {
        this.placedBy = placedBy;
    }

    public String getRoleOfPlacedBy() {
        return roleOfPlacedBy;
    }

    public void setRoleOfPlacedBy(String roleOfPlacedBy) {
        this.roleOfPlacedBy = roleOfPlacedBy;
    }

    public String getPlacedFor() {
        return placedFor;
    }

    public void setPlacedFor(String placedFor) {
        this.placedFor = placedFor;
    }

    public LocalDate getDateTillOrOn() {
        return dateTillOrOn;
    }

    public void setDateTillOrOn(String dateTillOrOn) {
        try {
            this.dateTillOrOn = LocalDate.parse(dateTillOrOn);
        } catch (Exception e) {
            this.dateTillOrOn = null;
        }
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getNoOfUnits() {

        return noOfUnits;
    }

    public void setNoOfUnits(String noOfUnits) {
        this.noOfUnits = noOfUnits;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void changeStatus() {
        if (defaultStatus + 1 < 3) {
            status = statuses[++defaultStatus];
        } else {
            defaultStatus = 0;
            status = statuses[defaultStatus];
        }
    }
}
