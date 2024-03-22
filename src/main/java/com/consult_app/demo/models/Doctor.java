package com.consult_app.demo.models;

import com.consult_app.demo.enums.DoctorStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Doctor {
    private String doctorId;
    private String userId;
    private String experience;
    private String servicesOffered;
    private int bioId;
    private String education;
    private String status;
    private Double rating;
}
