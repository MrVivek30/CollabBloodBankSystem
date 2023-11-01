package com.trackobit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

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

    public UserDTO() {
        this.id = idCounter++;
    }
}
