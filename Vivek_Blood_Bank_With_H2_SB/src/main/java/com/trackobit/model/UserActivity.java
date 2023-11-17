package com.trackobit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userEmail;


    private String activity;


    private String ipAddress;


    private LocalDateTime loginTime;


    private LocalDateTime logoutTime;


    private LocalDateTime lastLoginTime;



}
