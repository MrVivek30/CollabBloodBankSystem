package com.trackobit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private int id;
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid first name. First name should contain only alphabets.")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid last name. Last name should contain only alphabets.")
    private String lastName;


    private String dob;

    @Email(message = "Invalid email address. Please enter a valid email address.")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Invalid blood group. Please enter a valid blood group.")
    private String bloodGroup;

    @NotBlank(message = "Role is required")
    private String role;


    private LocalDateTime accountCreationDateTime=LocalDateTime.now();

    @NotBlank(message = "Address is required")
    private String address;

    private String status;

    private int loginCount;

    private String secretCode;


}
