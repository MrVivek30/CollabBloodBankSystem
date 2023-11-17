package com.trackobit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String password = "Test@123";
}
