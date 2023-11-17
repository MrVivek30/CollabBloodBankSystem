package com.trackobit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BonusMaster {
    @Id
    @GeneratedValue
    private int id;

   private int bonusAmount;

   private String status;

    private LocalDateTime appliedOn;
    private LocalDateTime expiredOn;

private LocalDateTime createDate;

private LocalDateTime updateDate;

private String createdBy;

private String updatedBy;

}
