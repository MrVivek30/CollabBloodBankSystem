package com.trackobit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
//@NoArgsConstructor
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private String firstName;

    private String lastName;

@Past(message = "date should be in past")
    private LocalDate dob;


    private String email;


    private String password;


    private String bloodGroup;


    private String role;


    private LocalDateTime accountCreationDateTime;

    private String address;

    private String status;

    private int loginCount;

    private String secretCode;

    public User() {
        this.loginCount = 3;
        this.status = "unblock";
    }
}
