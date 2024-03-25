package com.consult_app.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DoctorInformation {
    private String doctorId;
    private String userId;
    private String avatar;
    private String username;
    private String email;
    private String experience;
    private String servicesOffered;
    private int bioId;
    private String education;
    private String status;
    private Double rating;
    private String fullname;
    private int pages;
}
