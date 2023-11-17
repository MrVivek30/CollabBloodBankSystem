package com.trackobit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BonusMasterDTO {
    private int id;

    @NotBlank
    private int bonusAmount;

    private String status;

    private LocalDateTime appliedOn;

    private LocalDateTime expiredOn;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String createdBy;

    private String updatedBy;

}
