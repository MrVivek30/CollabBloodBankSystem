package com.trackobit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityDTO {



        private Long id;


        private String userEmail;


        private String activity;


        private String ipAddress;


        private LocalDateTime loginTime;


        private LocalDateTime logoutTime;


        private LocalDateTime lastLoginTime;


    }
