package com.trackobit.repository;

import com.trackobit.model.Request;
import com.trackobit.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class DataBase {

    public static List<User> userRepoList = new ArrayList<>();

    public static List<Request> requestRepoList = new ArrayList<>();


    public static User getUserByEmail(String email) {
        for (User user : userRepoList) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }
    public static Request getRequesterByEmail(String email) {
        for (Request request : requestRepoList) {
            if (request.getEmail().equals(email)) return request;
        }
        return null;
    }

    public static Request getRequestById(int id) {
        for (Request request : requestRepoList) {
            if (request.getUniqueId() == id){
                System.out.println("Request :::::::"+request);
                return request;
            }
        }

        return null;
    }


    static {

        userRepoList.add(new User(1, "raj", "kumar", "sjj", "raj@gmail.com", "raj123", "A+", "user", LocalDateTime.of(2023, 9, 29, 3, 3, 45), "kolkata"));
        userRepoList.add(new User(1002, "Admin", "", "1990-09-02", "admin@gmail.com", "admin123", "O+", "Admin", LocalDateTime.of(2018, 9, 29, 3, 3, 45), "Admin-Pur"));
        userRepoList.add(new User(2, "kunal", "singh", "2000-04-03", "kunal@gmail.com", "123456", "B+", "user", LocalDateTime.of(2023, 8, 12, 5, 3, 56), "ujjain"));
        userRepoList.add(new User(3, "kamlesh", "yadav", "2001-02-01", "kamlesh@gmail.com", "123456", "O-", "user", LocalDateTime.of(2023, 9, 30, 3, 3, 45), "bulandsahar"));
        userRepoList.add(new User(4, "vivek", "chaudhary", "2002-05-05", "vivek@gmail.com", "vivek123", "A-", "agent", LocalDateTime.of(2023, 5, 2, 8, 6, 23), "Basti"));
        userRepoList.add(new User(5, "sonu", "patel", "2000-09-07", "sonu@gmail.com", "123456", "B-", "user", LocalDateTime.of(2023, 9, 6, 3, 3, 1), "mumbai"));
        userRepoList.add(new User(6, "priti", "chaudhary", "2005-04-05", "priti@gmail.com", "123456", "AB+", "user", LocalDateTime.of(2023, 8, 29, 3, 3, 45), "chennai"));
        userRepoList.add(new User(7, "ekta", "Kapoor", "2010-02-23", "ekta@gmail.com", "ekta123", "AB-", "agent", LocalDateTime.of(2023, 5, 29, 3, 3, 3), "pryagraj"));
        userRepoList.add(new User(8, "sneha", "chaudhary", "1999-02-05", "sneha@gmail.com", "sneha123", "O+", "user", LocalDateTime.of(2023, 9, 29, 3, 3, 45), "delhi"));


        requestRepoList.add(new Request(1, "raj", 25, "raj@gmail.com", 1, "A+", "kolkata", LocalDateTime.now(), "USER", "Donate", "Pending", 1,"",1));
        requestRepoList.add(new Request(1, "raj", 25, "raj@gmail.com", 2, "A+", "kolkata", LocalDateTime.now(), "USER", "Receive", "Pending", 2,"",1));
        requestRepoList.add(new Request(3, "kamlesh", 22, "kamlesh@gmail.com", 2, "O-", "bulandsahar", LocalDateTime.now(), "USER", "Donate", "Pending", 3,"",1));
        requestRepoList.add(new Request(3, "kamlesh", 22, "kamlesh@gmail.com", 2, "O-", "bulandsahar", LocalDateTime.now(), "USER", "Receive", "Pending", 4,"",1));
    }



}
