package com.consult_app.demo.models;

import java.util.Date;

import com.consult_app.demo.enums.DoctorStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Doctor {
    private Long doctorId;
    private String experience;
    private String servicesOffered;
    private Long userId;
    private int bioId;
    private String education;
    private String status;
    private Double rating = 0.0;
	
}
