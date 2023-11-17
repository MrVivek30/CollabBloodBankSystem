package org.springproject.bloodbank.dao;

import org.springproject.bloodbank.model.Request;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class RequestDAO {
    private static final ArrayList<Request> requests = loadArrayListFromFile();
    private static final HashMap<String, Integer> bloodKeeper = bloodReload();
    public static HashMap<String, Integer> getBloodKeeper() {
        return bloodKeeper;
    }
    public static void saveArrayListToFile(ArrayList<Request> arrayList) {
        String filePath = "requests.db";
        // Overwrites any existing file by default
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
            System.err.println(" The request ArrayList has been saved");
        } catch (Exception e) {
            // do nothing
            System.err.println("Could not save the arraylist file on the filesystem");
        }
    }
    public static void saveArrayListToFile() {
        String filePath = "requests.db";
        // Overwrites any existing file by default
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(requests);
            objectOutputStream.close();
            System.err.println(" The request ArrayList has been saved");
        } catch (Exception e) {
            // do nothing
            System.err.println("Could not save the arraylist file on the filesystem");
        }
    }
    public static ArrayList<Request> loadArrayListFromFile() {
        String filePath = "requests.db";
        ArrayList<Request> arrayList;
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayList = (ArrayList<Request>) objectInputStream.readObject();
            objectInputStream.close();
            System.err.println("The Request arraylist has been retrieved");
        } catch (Exception e) {
            // If the file is not found, return an empty ArrayList by default
            System.err.println("The Request arraylist could not be retrieved");
            arrayList = new ArrayList<>();
        }
        return arrayList;
    }
    public static HashMap<String, Integer> bloodReload() {
        HashMap<String, Integer> bb = new HashMap<>();
        try {
            ArrayList<Request> tmp = requests.stream().filter(a -> a.getStatus().equalsIgnoreCase("granted")).collect(Collectors.toCollection(ArrayList::new));
            if (tmp == null || tmp.isEmpty())
                return new HashMap<String, Integer>();
            for (Request request : tmp) {
                Integer units2 = request.getNoOfUnits();
                bb.put(request.getBloodGroup(), bb.getOrDefault(request.getBloodGroup(), 0) + units2);
            }
        } catch (Exception e) {
            // do nothing
        }
        return bb;
    }
    public static void addBlood(String bg, int units) {
        Integer units2 = units;
        bloodKeeper.put(bg, bloodKeeper.getOrDefault(bg, 0) + units2);
    }
    public static void removeBlood(String bg, int units) {
        Integer units2 = units;
        bloodKeeper.put(bg, bloodKeeper.getOrDefault(bg, 0) - units2);
    }
    public static ArrayList<Request> getRequests() {
        return requests;
    }
    public static void add(Request hist) {
        if (hist.getBloodGroup() != null || !hist.getBloodGroup().isEmpty()) {
            addBlood(hist.getBloodGroup(), 0);
            if (hist.getPlacedBy().getRole().equalsIgnoreCase("user")) {
                hist.setBloodGroup(hist.getPlacedBy().getBloodGroup());
                System.err.printf("From User side");
            }else {
                System.err.printf("Its Agent side");
                Request d = requests.stream().
                        filter(request -> request.getRoleOfPlacedBy().equalsIgnoreCase("agent")).
                        filter(request -> request.getEmail().equalsIgnoreCase(hist.getEmail())).
                        findFirst().
                        orElseGet(() -> null);
                System.err.println("Found that the user exists");
                if (d != null) {
                    System.err.println("Setting password of the use");
                    hist.setPassword(d.getPassword());
                    if (!d.isFirstTimePassResetFlag()) //if password of the d user has already been set
                        hist.changeFirstTimePassResetFlag();
                }
            }
            System.err.printf("Saved");
            requests.add(hist);
            saveArrayListToFile(requests);
        }
    }
}
