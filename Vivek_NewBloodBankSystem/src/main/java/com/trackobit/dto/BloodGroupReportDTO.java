package com.trackobit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodGroupReportDTO {
    private String bloodGroup;
    private int units;
    private int donorCount;
    private  int donateReq;
    private  int receiveReq;
    private int pendingReq;
    private int acceptReq;
    private int rejectReq;

    public BloodGroupReportDTO(String bloodGroup, int units, int donorCount) {
        this.bloodGroup=bloodGroup;
        this.units=units;
        this.donorCount=donorCount;
    }
}
