package org.springproject.bloodbank.model;


import org.springproject.bloodbank.dto.RequestDTO;
import org.springproject.bloodbank.utils.Utility;

import java.io.Serializable;
import java.time.LocalDate;

public class Request implements Serializable {
    public static final String[] statuses = {"PENDING", "GRANTED", "REFUSED"};
    public static int objectCOunt = 0;
    private final User placedBy;
    private final String roleOfPlacedBy;
    private final String action;// donate or recieve
    private boolean firstTimePassResetFlag = true;
    private int histId;
    private int requestIterationCount = 0;
    private String placedFor;
    private LocalDate dateTillOrOn;
    private String bloodGroup;
    private int noOfUnits;
    private int defaultStatus = 0;
    private String status;
    private String email = "";
    private int age;
    private String address = "";
    private String remark = "";
    private String password = "text";
    public Request(RequestDTO history) {
        System.err.println("Request Object Created count is " + (++objectCOunt));
        this.placedBy = Utility.getUserObjectFromUsername(history.getPlacedBy());
        this.roleOfPlacedBy = history.getPlacedBy().getRole();
        this.placedFor = history.getPlacedFor();
        this.dateTillOrOn = history.getDateTillOrOn();
        this.bloodGroup = history.getBloodGroup();
        this.noOfUnits = Integer.valueOf(history.getNoOfUnits());
        this.action = history.getAction();
        this.histId = Utility.getRequestHash(history);
        this.status = history.getStatus();
        this.age = Integer.valueOf(history.getAge());
        this.email = history.getEmail();
        this.address = history.getAddress();
        this.remark = history.getRemark();
        requestIterationCount += 1;
    }

    public boolean isFirstTimePassResetFlag() {
        return firstTimePassResetFlag;
    }

    public void changeFirstTimePassResetFlag() {
        System.err.println("Falg value of request  changed");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void changeStatus() {
        requestIterationCount += 1;
        if (defaultStatus + 1 < 3) {
            status = statuses[++defaultStatus];
        } else {
            defaultStatus = 0;
            status = statuses[defaultStatus];
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "placedBy=" + placedBy +
                ", roleOfPlacedBy='" + roleOfPlacedBy + '\'' +
                ", action='" + action + '\'' +
                ", histId=" + histId +
                ", requestIterationCount=" + requestIterationCount +
                ", placedFor='" + placedFor + '\'' +
                ", dateTillOrOn=" + dateTillOrOn +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", noOfUnits=" + noOfUnits +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public User getPlacedBy() {
        return placedBy;
    }

    public String getRoleOfPlacedBy() {
        return roleOfPlacedBy;
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

    public void setDateTillOrOn(LocalDate dateTillOrOn) {
        this.dateTillOrOn = dateTillOrOn;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getNoOfUnits() {
        return noOfUnits;
    }

    public void setNoOfUnits(int noOfUnits) {
        this.noOfUnits = noOfUnits;
    }

    public String getAction() {
        return action;
    }

    public int getHistId() {
        return histId;
    }

    public void setHistId(int histId) {
        this.histId = histId;
    }

    public String getStatus() {
        return status;
    }
}

