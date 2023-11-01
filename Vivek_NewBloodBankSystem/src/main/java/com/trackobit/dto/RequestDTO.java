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
    private String name;
    private int age;
    private String email;
    private int unit;
    private String bloodGroup;
    private String address;
    private LocalDateTime localDateTime;
    private String role;
    private String type;
    private String status = "Pending";
    private int uniqueId = new Random().nextInt();
    private String remark;
    private int iterationCount;

}
