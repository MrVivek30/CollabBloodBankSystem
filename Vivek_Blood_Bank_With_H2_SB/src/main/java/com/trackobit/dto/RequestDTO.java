package com.trackobit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private int id;
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid name. Name should not contain numbers or special characters.")
    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 18, message = "Age must be 18 or older")
    @Max(value = 90, message = "Age must be 90 or younger")
    private int age;

    @Email(message = "Invalid email address. Please enter a valid email address.")
    private String email;

    @Positive(message = "Unit must be greater than 0")
    private int unit;

    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Invalid blood group. Please enter a valid blood group.")
    private String bloodGroup;

    @NotBlank(message = "Address cannot be blank.")
    private String address;

    @NotBlank(message = "Role cannot be blank.")
    private String role;

    @NotBlank(message = "Type cannot be blank.")
    private String type;

    private LocalDateTime localDateTime;

    private String status = "Pending";
    private int uniqueId = new Random().nextInt();
    private String remark;
    private int iterationCount;
    private String password;

}
