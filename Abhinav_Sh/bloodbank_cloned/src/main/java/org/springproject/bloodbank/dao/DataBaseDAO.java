package org.springproject.bloodbank.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springproject.bloodbank.dto.UserDTO;
import org.springproject.bloodbank.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.springproject.bloodbank.utils.Utility.getHashFromString;

public class DataBaseDAO {
    private static final ArrayList<User> users = loadArrayListFromFile();
    private static final Logger logger = LogManager.getLogger("vehicles");

    public static void createAdmin() {
        if (!isEmailExist("admin@admin.com")) {
            logger.info("The admin is not present");
            UserDTO sign = new UserDTO();
            sign.setPassword("admin123");
            sign.setName("admin");
            sign.setBloodGroup("O-");
            sign.setDATE_OF_BIRTH(LocalDate.now().minusYears(100).toString());
            sign.setEmail("admin@admin.com");
            sign.setRole("ADMIN");
            addUser(sign);
        }else {
            logger.info("");
        }
    }

    public static void saveArrayListToFile(ArrayList<User> arrayList) {
        String filePath = "users.db";
        // Overwrites any existing file by default
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
        } catch (Exception e) {
            // do nothing
            System.err.println("Could not save the file on the filesystem");
        }
    }

    public static ArrayList<User> loadArrayListFromFile() {
        String filePath = "users.db";
        ArrayList<User> arrayList;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayList = (ArrayList<User>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            // If the file is not found, return an empty ArrayList by default
            System.out.println("Could not retrieve the file from the filesystem");
            arrayList = new ArrayList<>();
        }
        return arrayList;
    }
    public static void addUser(UserDTO u) {
        logger.info("Checking if the passed in userDTO object is null or not");
        if (u != null && u.getPassword() != null) {
            logger.info("Calculating the SHA512 hash of the password of userDTO and setting it to it");
            u.setPassword(getHashFromString(u.getPassword()));
            logger.info("Creating the Empty USer object and setting the UserDTO objects info to that User object ");
            User user = new User(u);
            users.add(user);
            logger.info("Saving it to the database");
            logger.info(user);
            saveArrayListToFile(getUsers());
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void delUser(User u) {
        users.remove(u);
    }
    public static User getUser(String username) {
        boolean isEmail = username.contains("@");
        return DataBaseDAO.getUsers().stream()
                .filter(a ->a.getEmail().equalsIgnoreCase(username))
                .findFirst().get();
    }
    public static boolean isEmailExist(String email) {
        return users.
                // check only those emails which correspond to the given role
                        stream().
                anyMatch(a -> a.getEmail().equalsIgnoreCase(email));
    }
    public static boolean isUserCredUnique(UserDTO u) {
        boolean emailUniqueCheck = DataBaseDAO.isEmailExist(u.getEmail());
        return (!emailUniqueCheck);
    }
}
