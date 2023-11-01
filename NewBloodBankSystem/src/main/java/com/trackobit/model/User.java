package com.trackobit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class User {

    private static int idCounter = 9;
    private int id;


    private String firstName;

    private String lastName;


    private String dob;


    private String email;


    private String password;


    private String bloodGroup;


    private String role;


    private LocalDateTime accountCreationDateTime;


    private String address;

    public User() {
        this.id = idCounter++;
    }
}
