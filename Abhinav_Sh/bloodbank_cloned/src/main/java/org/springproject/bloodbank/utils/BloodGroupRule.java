package org.springproject.bloodbank.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;

public class BloodGroupRule {
    private static final Logger logger = LogManager.getLogger("vehicles");
    public static HashMap<String, HashSet<String>> canRecieveFrom = new HashMap<>();

    static {
        logger.info("Blood Group rule is used");
        canRecieveFrom.put("A+", new HashSet<String>());
        canRecieveFrom.get("A+").add("A+");
        canRecieveFrom.get("A+").add("A-");
        canRecieveFrom.get("A+").add("O+");
        canRecieveFrom.get("A+").add("O-");
        canRecieveFrom.put("A-", new HashSet<String>());
        canRecieveFrom.get("A-").add("A-");
        canRecieveFrom.get("A-").add("O-");
        canRecieveFrom.put("B+", new HashSet<String>());
        canRecieveFrom.get("B+").add("B+");
        canRecieveFrom.get("B+").add("B-");
        canRecieveFrom.get("B+").add("O+");
        canRecieveFrom.get("B+").add("O-");
        canRecieveFrom.put("B-", new HashSet<String>());
        canRecieveFrom.get("B-").add("B-");
        canRecieveFrom.get("B-").add("O-");
        canRecieveFrom.put("AB+", new HashSet<String>());
        canRecieveFrom.get("AB+").add("A+");
        canRecieveFrom.get("AB+").add("B+");
        canRecieveFrom.get("AB+").add("O+");
        canRecieveFrom.get("AB+").add("A-");
        canRecieveFrom.get("AB+").add("B-");
        canRecieveFrom.get("AB+").add("O-");
        canRecieveFrom.get("AB+").add("AB+");
        canRecieveFrom.get("AB+").add("AB-");
        canRecieveFrom.put("AB-", new HashSet<String>());
        canRecieveFrom.get("AB-").add("A-");
        canRecieveFrom.get("AB-").add("B-");
        canRecieveFrom.get("AB-").add("O-");
        canRecieveFrom.get("AB-").add("AB-");
        canRecieveFrom.put("O+", new HashSet<String>());
        canRecieveFrom.get("O+").add("O+");
        canRecieveFrom.get("O+").add("O-");
        canRecieveFrom.put("O-", new HashSet<String>());
        canRecieveFrom.get("O-").add("O-");
    }

}